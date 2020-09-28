package crossLink.aoi.cross;

import crossLink.IAoi;
import crossLink.NodeFactory;
import crossLink.aoi.AoiListenerManager;
import crossLink.test.CrossNodeFactory;
import crossLink.test.INodeFactory;
import util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 十字链表Aoi
 * @Author zhangfan
 * @Date 2020/9/15 17:30
 * @Version 1.0
 */
public class CrossAoi extends AoiListenerManager<CrossLinkNode> implements IAoi<CrossLinkNode>
{

    /*
      暂时 使用监听器来模拟广播
     */
    public static CrossLinkNode xHead;
    public static CrossLinkNode xTail;

    public static CrossLinkNode yHead;
    public static CrossLinkNode yTail;

    /**
     * 用来快速查询的数据结构
     */
    private final CrossQuickSearch crossQuickSearch;

    private Map<Long, CrossLinkNode> nodes = new HashMap<>();

    // 可以假定 玩家在位置上对半分. TODO 循环方向优化
    private int maxX;
    private int maxY;

    private INodeFactory<CrossLinkNode> nodeFactory;

    /**
     * 初始化
     */
    public CrossAoi(int labelBegin, int maxX, int maxY, int scale)
    {
        this.maxX = maxX;
        this.maxY = maxY;

        nodeFactory = new CrossNodeFactory(labelBegin);

        xHead = NodeFactory.createInstance(0, -1, 0);
        xTail = NodeFactory.createInstance(0, maxX, 0);
        makeDoubleLink(null, xHead, xTail, true);

        yHead = NodeFactory.createInstance(0, 0, -1);
        yTail = NodeFactory.createInstance(0, 0, maxY);
        makeDoubleLink(null, yHead, yTail, false);

        CrossQuickSearch crossQuickSearch = new CrossQuickSearch(scale);
        this.crossQuickSearch = crossQuickSearch;
    }

    /**
     * 构造双向
     *
     * @param crossLinkNode
     * @param prev
     * @param next
     */
    public void makeDoubleLink(CrossLinkNode crossLinkNode, CrossLinkNode prev, CrossLinkNode next, boolean isX)
    {
        if (isX)
        {
            prev.xNext = next;
            next.xPrev = prev;

            if (crossLinkNode == null)
            {
                return;
            }

            NormalIndexSkipNode xIndexDoubleLinkNode = crossLinkNode.xIndexSkipNode;
            if (xIndexDoubleLinkNode != null)
            {
                crossLinkNode.xIndexSkipNode = null;

                xIndexDoubleLinkNode.first = next;
                next.xIndexSkipNode = xIndexDoubleLinkNode;
            }
        }
        else
        {
            prev.yNext = next;
            next.yPrev = prev;

            if (crossLinkNode == null)
            {
                return;
            }

            NormalIndexSkipNode yIndexDoubleLinkNode = crossLinkNode.yIndexSkipNode;
            if (yIndexDoubleLinkNode != null)
            {
                crossLinkNode.yIndexSkipNode = null;

                yIndexDoubleLinkNode.first = next;
                next.yIndexSkipNode = yIndexDoubleLinkNode;
            }
        }
    }

    /**
     * 插入node到prev之后
     *
     * @param prev
     * @param insert
     * @param isX
     */
    private void insertDoubleLink(CrossLinkNode prev, CrossLinkNode insert, boolean isX)
    {
        if (isX)
        {
            CrossLinkNode xNext = prev.xNext;

            insert.xNext = xNext;
            insert.xPrev = prev;

            prev.xNext = insert;
            xNext.xPrev = insert;
        }
        else
        {
            CrossLinkNode yNext = prev.yNext;

            insert.yNext = yNext;
            insert.yPrev = prev;

            prev.yNext = insert;
            yNext.yPrev = insert;
        }
    }

    /**
     * 新增节点(根据相对距离查找)  适合移动的节点
     *
     * @param left  向左的差值
     * @param upper 向上的差值
     * @param node
     */
    public void addNodeRelative(CrossLinkNode node, int left, int upper)
    {
        // 插入x,y
        int x = node.x;
        int y = node.y;

        if (left != 0)
        {
            if (left < 0)
            {
                for (CrossLinkNode cur = node.xNext; cur != xHead; cur = cur.xPrev)
                {
                    CrossLinkNode xPrev = cur.xPrev;
                    if (x < cur.x && x >= xPrev.x)
                    {
                        insertDoubleLink(cur, node, true);
                        break;
                    }
                }
            }
            else
            {
                for (CrossLinkNode cur = node.xPrev; cur != xTail; cur = cur.xNext)
                {
                    CrossLinkNode xNext = cur.xNext;
                    if (x > cur.x && x <= xNext.x)
                    {
                        insertDoubleLink(cur, node, true);
                        break;
                    }
                }
            }
        }

        if (upper != 0)
        {
            if (upper < 0)
            {
                for (CrossLinkNode cur = node.yNext; cur != yHead; cur = cur.yPrev)
                {
                    CrossLinkNode yPrev = cur.yPrev;
                    if (y < cur.y && y >= yPrev.y)
                    {
                        insertDoubleLink(cur, node, false);
                        break;
                    }
                }
            }
            else
            {
                //插入y
                for (CrossLinkNode cur = node.yPrev; cur != yTail; cur = cur.yNext)
                {
                    CrossLinkNode yNext = cur.yNext;
                    if (y > cur.y && y <= yNext.y)
                    {
                        insertDoubleLink(cur, node, false);
                        break;
                    }
                }
            }
        }

        nodes.put(node.label, node);

        crossQuickSearch.addNodeRelative(node);
    }

    /**
     * 新增节点(从头开始查找), 适合新增加的节点
     *
     * @param node
     */
    @Override
    public void addNode(CrossLinkNode node)
    {
        crossQuickSearch.addNode(node);

        nodes.put(node.label, node);

        onTriggerAddListener(this, node);
    }

    @Override
    public void addNode(int x, int y)
    {
        addNode(nodeFactory.create(x, y));
    }

    /**
     * 删除节点
     */
    @Override
    public void removeNode(long label)
    {
        CrossLinkNode trueNode = nodes.get(label);
        if (trueNode == null)
        {
            Log.CrossAOI_Logger.error("label:{} 没有找到", label);
        }

        makeDoubleLink(trueNode, trueNode.xPrev, trueNode.xNext, true);

        makeDoubleLink(trueNode, trueNode.yPrev, trueNode.yNext, false);

        nodes.remove(label);

        onTriggerRemoveListener(this, trueNode);
    }

    /**
     * 节点移动
     *
     * @param node
     * @param x
     * @param y
     */
    @Override
    public void moveNode(CrossLinkNode node, int x, int y)
    {
        final int _x = node.x;
        final int _y = node.y;

        onTriggerBeforeMoveToListener(this, node, x, y);

        node.moveTo(this, x, y);

        onTriggerAfterMoveToListener(this, node, _x, _y);
    }

    @Override
    public CrossLinkNode getNode(long label)
    {
        return nodes.get(label);
    }

    @Override
    public int getXRange()
    {
        return maxX;
    }

    @Override
    public int getYRange()
    {
        return maxY;
    }

    public void acceptDatas(int[] nodePos)
    {
        for (int i = 0, size = nodePos.length; i < size; i += 2)
        {
            addNode(nodeFactory.create(nodePos[i], nodePos[i + 1]));
        }
    }


    /**
     * @Description 十字链表快查模块
     * 还是使用 双向链表.  这个里面的节点起着承上启下的作用. 用来连接左边阶段的最后一个节点和当前开始的最前面的一个节点
     * @Author zhangfan
     * @Date 2020/9/27 11:15
     * @Version 1.0
     */
    public class CrossQuickSearch
    {
        /**
         * 跨度(多长距离建立一个节点, 假定是正方形. 如果不是还需要加入xy区分)
         */
        private int scale;

        private NormalIndexSkipNode[] xQuickSearchNodes;
        private NormalIndexSkipNode[] yQuickSearchNodes;

        public CrossQuickSearch(int scale)
        {
            this.scale = scale;

            int xRange = getXRange();
            int yRange = getYRange();

            int xnum = xRange / scale + 1;
            int ynum = yRange / scale + 1;

            xQuickSearchNodes = new NormalIndexSkipNode[xnum];
            yQuickSearchNodes = new NormalIndexSkipNode[ynum];

            for (int i = 0; i < xnum; i++)
            {
                xQuickSearchNodes[i] = createNormalIndexSkipNode(NormalIndexSkipNode.X, i, i * scale);
            }
            for (int i = 0; i < ynum; i++)
            {
                yQuickSearchNodes[i] = createNormalIndexSkipNode(NormalIndexSkipNode.Y, i, i * scale);
            }
        }

        public NormalIndexSkipNode createNormalIndexSkipNode(int direction, int index, int pos)
        {
            NormalIndexSkipNode normalIndexDoubleLinkNode = new NormalIndexSkipNode();
            normalIndexDoubleLinkNode.direction = direction;
            normalIndexDoubleLinkNode.index = index;
            normalIndexDoubleLinkNode.pos = pos;
            return normalIndexDoubleLinkNode;
        }

        /**
         * 在 跳点和node之间建立关系
         *
         * @param direction
         * @param skipNode
         * @param node
         */
        private void createLink(int direction, NormalIndexSkipNode skipNode, CrossLinkNode node)
        {
            if (skipNode.first == null)
            {
                skipNode.first = node;
                if (direction == NormalIndexSkipNode.X)
                {
                    node.xIndexSkipNode = skipNode;
                }
                else
                {
                    node.yIndexSkipNode = skipNode;
                }
            }
            else
            {
                if (direction == NormalIndexSkipNode.X)
                {
                    if (node.x < skipNode.first.x)
                    {
                        // 注意移除掉原来跳点引用节点上面的跳点引用
                        skipNode.first.xIndexSkipNode = null;

                        // 对新引用建立关系
                        skipNode.first = node;
                        node.xIndexSkipNode = skipNode;
                    }
                }
                else
                {
                    if (node.y < skipNode.first.y)
                    {
                        // 注意移除掉原来跳点引用节点上面的跳点引用
                        skipNode.first.yIndexSkipNode = null;

                        skipNode.first = node;
                        node.yIndexSkipNode = skipNode;
                    }
                }
            }
        }

        /**
         * @param node
         */
        public void addNode(CrossLinkNode node)
        {
            // 插入x,y
            int x = node.x;
            int y = node.y;

            int indexX = x / scale;
            int indexY = y / scale;

            {
                NormalIndexSkipNode xQuickSearchNodeLeft = xQuickSearchNodes[indexX];
                if (xQuickSearchNodeLeft.first == null)
                {
                    createLink(NormalIndexSkipNode.X, xQuickSearchNodeLeft, node);

                    //  插入链表, 需要向前找到之前的一个下面有节点的 有效跳点
                    CrossLinkNode crossLinkNode = xHead;
                    for (int i = indexX - 1; i >= 0; i--)
                    {
                        if (xQuickSearchNodes[i].first != null)
                        {
                            crossLinkNode = xQuickSearchNodes[i].first;
                            break;
                        }
                    }

                    for (CrossLinkNode cur = crossLinkNode; cur != xTail; cur = cur.xNext)
                    {
                        CrossLinkNode xNext = cur.xNext;
                        if (x >= cur.x && x <= xNext.x)
                        {
                            insertDoubleLink(cur, node, true);
                            break;
                        }
                    }
                }
                else
                {
                    if (x < xQuickSearchNodeLeft.first.x)
                    {
                        insertDoubleLink(xQuickSearchNodeLeft.first.xPrev, node, true);

                        createLink(NormalIndexSkipNode.X, xQuickSearchNodeLeft, node);
                    }
                    else
                    {
                        // 使用这个跳点快速查找插入位置
                        for (CrossLinkNode cur = xQuickSearchNodeLeft.first; cur != xTail; cur = cur.xNext)
                        {
                            CrossLinkNode xNext = cur.xNext;

                            // 此处要使用>=, 这样可以保证插入到第二个位置
                            if (x >= cur.x && x <= xNext.x)
                            {
                                insertDoubleLink(cur, node, true);
                                break;
                            }
                        }
                    }
                }
            }

            {
                NormalIndexSkipNode yQuickSearchNodeUpper = yQuickSearchNodes[indexY];
                if (yQuickSearchNodeUpper.first == null)
                {
                    createLink(NormalIndexSkipNode.Y, yQuickSearchNodeUpper, node);

                    //  插入真正的链表
                    CrossLinkNode crossLinkNode = yHead;
                    for (int i = indexY - 1; i >= 0; i--)
                    {
                        if (yQuickSearchNodes[i].first != null)
                        {
                            crossLinkNode = yQuickSearchNodes[i].first;
                            break;
                        }
                    }

                    for (CrossLinkNode cur = crossLinkNode; cur != yTail; cur = cur.yNext)
                    {
                        CrossLinkNode yNext = cur.yNext;

                        // 此处要使用>=, 这样可以保证插入到第二个位置
                        if (y >= cur.y && y <= yNext.y)
                        {
                            insertDoubleLink(cur, node, false);
                            break;
                        }
                    }
                }
                else
                {
                    if (y < yQuickSearchNodeUpper.first.y)
                    {
                        insertDoubleLink(yQuickSearchNodeUpper.first.yPrev, node, false);

                        createLink(NormalIndexSkipNode.Y, yQuickSearchNodeUpper, node);
                    }
                    else
                    {
                        // 使用这个跳点快速查找插入位置
                        for (CrossLinkNode cur = yQuickSearchNodeUpper.first; cur != yTail; cur = cur.yNext)
                        {
                            CrossLinkNode yNext = cur.yNext;
                            if (y >= cur.y && y <= yNext.y)
                            {
                                insertDoubleLink(cur, node, false);
                                break;
                            }
                        }
                    }
                }
            }

//            for (CrossLinkNode cur = xHead; cur != xTail; cur = cur.xNext)
//            {
//                CrossLinkNode xNext = cur.xNext;
//                if (x > cur.x && x <= xNext.x)
//                {
//                    insertDoubleLink(cur, node, true);
//                    break;
//                }
//            }

            //插入y
//            for (CrossLinkNode cur = yHead; cur != yTail; cur = cur.yNext)
//            {
//                CrossLinkNode yNext = cur.yNext;
//                if (y > cur.y && y <= yNext.y)
//                {
//                    insertDoubleLink(cur, node, false);
//                    break;
//                }
//            }
        }

        public void addNodeRelative(CrossLinkNode node)
        {
            // 插入x,y
            int x = node.x;
            int y = node.y;

            int indexX = x / scale;
            int indexY = y / scale;

            NormalIndexSkipNode xQuickSearchNodeLeft = xQuickSearchNodes[indexX];
            createLink(NormalIndexSkipNode.X, xQuickSearchNodeLeft, node);

            NormalIndexSkipNode yQuickSearchNodeUpper = yQuickSearchNodes[indexY];

            createLink(NormalIndexSkipNode.Y, yQuickSearchNodeUpper, node);
        }
    }
}
