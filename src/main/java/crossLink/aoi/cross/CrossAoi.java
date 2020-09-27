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

            NormalIndexDoubleLinkNode xIndexDoubleLinkNode = crossLinkNode.xIndexDoubleLinkNode;
            if (xIndexDoubleLinkNode != null)
            {
                crossLinkNode.xIndexDoubleLinkNode = null;

                xIndexDoubleLinkNode.first = next;
                next.xIndexDoubleLinkNode = xIndexDoubleLinkNode;
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

            NormalIndexDoubleLinkNode yIndexDoubleLinkNode = crossLinkNode.yIndexDoubleLinkNode;
            if (yIndexDoubleLinkNode != null)
            {
                crossLinkNode.yIndexDoubleLinkNode = null;

                yIndexDoubleLinkNode.first = next;
                next.yIndexDoubleLinkNode = yIndexDoubleLinkNode;
            }
        }
    }

    /**
     * 插入node
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
    public void addNode(CrossLinkNode node, int left, int upper)
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

        crossQuickSearch.onAddNode(node);
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

        private NormalIndexDoubleLinkNode[] xQuickSearchNodes;
        private NormalIndexDoubleLinkNode[] yQuickSearchNodes;

        public CrossQuickSearch(int scale)
        {
            this.scale = scale;

            int xRange = getXRange();
            int yRange = getYRange();

            int xnum = xRange / scale + 1;
            int ynum = yRange / scale + 1;

            xQuickSearchNodes = new NormalIndexDoubleLinkNode[xnum];
            yQuickSearchNodes = new NormalIndexDoubleLinkNode[ynum];

            for (int i = 0; i < xnum; i++)
            {
                NormalIndexDoubleLinkNode normalIndexDoubleLinkNode = new NormalIndexDoubleLinkNode();
                normalIndexDoubleLinkNode.direction = NormalIndexDoubleLinkNode.X;
                normalIndexDoubleLinkNode.index = i;
                normalIndexDoubleLinkNode.pos = i * scale;

                xQuickSearchNodes[i] = normalIndexDoubleLinkNode;
            }
            for (int i = 0; i < ynum; i++)
            {
                NormalIndexDoubleLinkNode normalIndexDoubleLinkNode = new NormalIndexDoubleLinkNode();
                normalIndexDoubleLinkNode.direction = NormalIndexDoubleLinkNode.Y;
                normalIndexDoubleLinkNode.index = i;
                normalIndexDoubleLinkNode.pos = i * scale;

                yQuickSearchNodes[i] = normalIndexDoubleLinkNode;
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
                NormalIndexDoubleLinkNode xQuickSearchNodeLeft = xQuickSearchNodes[indexX];
                if (xQuickSearchNodeLeft.first == null)
                {
                    // 修改当前
                    xQuickSearchNodeLeft.first = node;
                    node.xIndexDoubleLinkNode = xQuickSearchNodeLeft;

                    //  插入真正的链表
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
                        if (x > cur.x && x <= xNext.x)
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

                        // 修改当前
                        xQuickSearchNodeLeft.first = node;
                        node.xIndexDoubleLinkNode = xQuickSearchNodeLeft;
                    }
                    else
                    {
                        for (CrossLinkNode cur = xQuickSearchNodeLeft.first; cur != xTail; cur = cur.xNext)
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
            }

            {
                NormalIndexDoubleLinkNode yQuickSearchNodeUpper = yQuickSearchNodes[indexY];
                if (yQuickSearchNodeUpper.first == null)
                {
                    yQuickSearchNodeUpper.first = node;
                    node.yIndexDoubleLinkNode = yQuickSearchNodeUpper;

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
                        if (y > cur.y && y <= yNext.y)
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

                        // 修改当前
                        yQuickSearchNodeUpper.first = node;
                        node.yIndexDoubleLinkNode = yQuickSearchNodeUpper;
                    }
                    else
                    {
                        for (CrossLinkNode cur = yQuickSearchNodeUpper.first; cur != yTail; cur = cur.yNext)
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

        public void onAddNode(CrossLinkNode node)
        {
            // 插入x,y
            int x = node.x;
            int y = node.y;

            int indexX = x / scale;
            int indexY = y / scale;

            {
                NormalIndexDoubleLinkNode xQuickSearchNodeLeft = xQuickSearchNodes[indexX];
                if (xQuickSearchNodeLeft.first == null)
                {
                    // 修改当前
                    xQuickSearchNodeLeft.first = node;
                    node.xIndexDoubleLinkNode = xQuickSearchNodeLeft;
                }
                else
                {
                    if (x < xQuickSearchNodeLeft.first.x)
                    {
                        xQuickSearchNodeLeft.first.xIndexDoubleLinkNode = null;

                        xQuickSearchNodeLeft.first = node;
                        node.xIndexDoubleLinkNode = xQuickSearchNodeLeft;
                    }
                }
            }

            {
                NormalIndexDoubleLinkNode yQuickSearchNodeUpper = yQuickSearchNodes[indexY];
                if (yQuickSearchNodeUpper.first == null)
                {
                    // 修改当前
                    yQuickSearchNodeUpper.first = node;
                    node.yIndexDoubleLinkNode = yQuickSearchNodeUpper;
                }
                else
                {
                    if(y < yQuickSearchNodeUpper.first.y)
                    {
                        yQuickSearchNodeUpper.first.yIndexDoubleLinkNode = null;

                        yQuickSearchNodeUpper.first = node;
                        node.yIndexDoubleLinkNode = yQuickSearchNodeUpper;
                    }
                }
            }
        }
    }
}
