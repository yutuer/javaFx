package crossLink.aoi.cross;

import crossLink.IAoi;
import crossLink.NodeFactory;
import crossLink.aoi.AoiListenerManager;

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

    private Map<Long, CrossLinkNode> nodes = new HashMap<>();


    // 可以假定 玩家在位置上对半分. TODO 循环方向优化
    private int halfX;
    private int halfY;

    /**
     * 初始化
     */
    public CrossAoi(int maxX, int maxY)
    {
        this.halfX = maxX;
        this.halfY = maxY;

        xHead = NodeFactory.createInstance(0, 0, 0);
        xTail = NodeFactory.createInstance(0, maxX, 0);
        makeDoubleLink(xHead, xTail, true);

        yHead = NodeFactory.createInstance(0, 0, 0);
        yTail = NodeFactory.createInstance(0, 0, maxY);
        makeDoubleLink(yHead, yTail, false);
    }

    /**
     * 构造双向
     *
     * @param prev
     * @param next
     */
    private void makeDoubleLink(CrossLinkNode prev, CrossLinkNode next, boolean isX)
    {
        if (isX)
        {
            prev.xNext = next;
            next.xPrev = prev;
        }
        else
        {
            prev.yNext = next;
            next.yPrev = prev;
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
        // 插入x
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
                        insertDoubleLink(cur, node, true);
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

    }

    /**
     * 新增节点(从头开始查找), 适合新增加的节点
     *
     * @param node
     */
    @Override
    public void addNode(CrossLinkNode node)
    {
        // 插入x
        int x = node.x;
        int y = node.y;

        for (CrossLinkNode cur = xHead; cur != xTail; cur = cur.xNext)
        {
            CrossLinkNode xNext = cur.xNext;
            if (x > cur.x && x <= xNext.x)
            {
                insertDoubleLink(cur, node, true);
                break;
            }
        }

        //插入y
        for (CrossLinkNode cur = yHead; cur != yTail; cur = cur.yNext)
        {
            CrossLinkNode yNext = cur.yNext;
            if (y > cur.y && y <= yNext.y)
            {
                insertDoubleLink(cur, node, false);
                break;
            }
        }

        nodes.put(node.label, node);

        onTriggerAddListener(this, node);
    }

    /**
     * 删除节点
     */
    @Override
    public void removeNode(long label)
    {
        CrossLinkNode trueNode = nodes.get(label);

        makeDoubleLink(trueNode.xPrev, trueNode.xNext, true);

        makeDoubleLink(trueNode.yPrev, trueNode.yNext, false);

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

}
