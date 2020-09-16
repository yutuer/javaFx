package crossLink;

import crossLink.listener.AoiListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 十字链表Aoi
 * @Author zhangfan
 * @Date 2020/9/15 17:30
 * @Version 1.0
 */
public class CrossAoi
{

    public static BaseNode xHead;
    public static BaseNode xTail;

    public static BaseNode yHead;
    public static BaseNode yTail;

    public List<AoiListener> aoiListeners = new ArrayList<>();

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

        xHead = NodeFactory.createInstance(0, 0);
        xTail = NodeFactory.createInstance(maxX, 0);
        makeDoubleLink(xHead, xTail, true);

        yHead = NodeFactory.createInstance(0, 0);
        yTail = NodeFactory.createInstance(0, maxY);
        makeDoubleLink(yHead, yTail, false);
    }

    /**
     * 移除监听器
     *
     * @param listener
     */
    public void addListener(AoiListener listener)
    {
        if (aoiListeners.contains(listener))
        {
            removeListener(listener);
        }
        aoiListeners.add(listener);
    }

    /**
     * 添加监听器
     *
     * @param listener
     */
    public void removeListener(AoiListener listener)
    {
        for (int i = 0, size = aoiListeners.size(); i < size; i++)
        {
            AoiListener aoiListener = aoiListeners.get(i);
            if (aoiListener == listener)
            {
                aoiListeners.remove(i);
                break;
            }
        }
    }

    /**
     * 构造双向
     *
     * @param prev
     * @param next
     */
    private void makeDoubleLink(BaseNode prev, BaseNode next, boolean isX)
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
    private void insertDoubleLink(BaseNode prev, BaseNode insert, boolean isX)
    {
        if (isX)
        {
            BaseNode xNext = prev.xNext;

            insert.xNext = xNext;
            insert.xPrev = prev;

            prev.xNext = insert;
            xNext.xPrev = insert;
        }
        else
        {
            BaseNode yNext = prev.yNext;

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
    public void addNode(BaseNode node, int left, int upper)
    {
        // 插入x
        int x = node.x;
        int y = node.y;

        if (left != 0)
        {
            if (left < 0)
            {
                for (BaseNode cur = node.xNext; cur != xHead; cur = cur.xPrev)
                {
                    BaseNode xPrev = cur.xPrev;
                    if (x < cur.x && x >= xPrev.x)
                    {
                        insertDoubleLink(cur, node, true);
                        break;
                    }
                }
            }
            else
            {
                for (BaseNode cur = node.xPrev; cur != xTail; cur = cur.xNext)
                {
                    BaseNode xNext = cur.xNext;
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
                for (BaseNode cur = node.yNext; cur != yHead; cur = cur.yPrev)
                {
                    BaseNode yPrev = cur.yPrev;
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
                for (BaseNode cur = node.yPrev; cur != yTail; cur = cur.yNext)
                {
                    BaseNode yNext = cur.yNext;
                    if (y > cur.y && y <= yNext.y)
                    {
                        insertDoubleLink(cur, node, false);
                        break;
                    }
                }
            }
        }

        for (int i = 0, size = aoiListeners.size(); i < size; i++)
        {
            AoiListener aoiListener = aoiListeners.get(i);
            aoiListener.onAddNode(node);
        }
    }

    /**
     * 新增节点(从头开始查找), 适合新增加的节点
     *
     * @param node
     */
    public void addNode(BaseNode node)
    {
        // 插入x
        int x = node.x;
        int y = node.y;

        for (BaseNode cur = xHead; cur != xTail; cur = cur.xNext)
        {
            BaseNode xNext = cur.xNext;
            if (x > cur.x && x <= xNext.x)
            {
                insertDoubleLink(cur, node, true);
                break;
            }
        }

        //插入y
        for (BaseNode cur = yHead; cur != yTail; cur = cur.yNext)
        {
            BaseNode yNext = cur.yNext;
            if (y > cur.y && y <= yNext.y)
            {
                insertDoubleLink(cur, node, false);
                break;
            }
        }

        for (int i = 0, size = aoiListeners.size(); i < size; i++)
        {
            AoiListener aoiListener = aoiListeners.get(i);
            aoiListener.onAddNode(node);
        }
    }

    /**
     * 删除节点
     *
     * @param node
     */
    public void removeNode(BaseNode node)
    {
        BaseNode trueNode = null;

        for (BaseNode cur = xHead; cur != xTail; cur = cur.xNext)
        {
            if (cur.equals(node))
            {
                trueNode = cur;

                makeDoubleLink(cur.xPrev, cur.xNext, true);
                break;
            }
        }

        for (BaseNode cur = yHead; cur != yTail; cur = cur.yNext)
        {
            if (cur.equals(node))
            {
                trueNode = cur;

                makeDoubleLink(cur.yPrev, cur.yNext, false);
                break;
            }
        }

        for (int i = 0, size = aoiListeners.size(); i < size; i++)
        {
            AoiListener aoiListener = aoiListeners.get(i);
            aoiListener.onRemoveNode(trueNode);
        }
    }

    /**
     * 节点瞬移
     *
     * @param node
     * @param x
     * @param y
     */
    public void teleportMoveNode(BaseNode node, int x, int y)
    {
        node.teleportMoveTo(this, x, y);

        for (int i = 0, size = aoiListeners.size(); i < size; i++)
        {
            AoiListener aoiListener = aoiListeners.get(i);
            aoiListener.onTeleportMoveTo(node, x, y);
        }
    }


}
