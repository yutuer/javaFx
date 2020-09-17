package crossLink.listener;

import com.google.common.collect.Sets;
import crossLink.IAoi;
import crossLink.aoi.cross.CrossLinkNode;
import crossLink.Binder;
import crossLink.aoi.cross.CrossAoi;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description 十字链表 广播监听器.  让受到影响的其他的点变色
 * @Author zhangfan
 * @Date 2020/9/15 21:13
 * @Version 1.0
 */
public class CrossLinkBroadListener implements AoiListener<CrossLinkNode>
{
    /**
     * 影响范围
     */
    private int xRange;
    private int yRange;

    // 添加时通知的颜色
    private Color addColor = Color.ORANGERED;

    // 删除时通知的颜色
    private Color removeColor = Color.DARKRED;

    private Set<CrossLinkNode> set = new HashSet<>();

    private Set<CrossLinkNode> setBack = new HashSet<>();

    public CrossLinkBroadListener(int xRange, int yRange)
    {
        this.xRange = xRange;
        this.yRange = yRange;
    }

    @Override
    public void onAddNode(IAoi aoi, CrossLinkNode node)
    {
        // 自己变色
        changeColor(node, Color.GOLD);

        final Set<CrossLinkNode> set = this.set;

        chooseBroadSet(node, set);

        if (!set.isEmpty())
        {
            for (Iterator<CrossLinkNode> iterator = set.iterator(); iterator.hasNext(); )
            {
                CrossLinkNode baseNode = iterator.next();
                changeColor(baseNode, addColor);
            }
        }

        set.clear();
    }

    /**
     * 挑选出满足范围的unit
     *
     * @param node
     * @param set
     */
    private void chooseBroadSet(CrossLinkNode node, Set<CrossLinkNode> set)
    {
        //x 轴 往前
        for (CrossLinkNode cur = node.xPrev; cur != CrossAoi.xHead; cur = cur.xPrev)
        {
            if (Math.abs(node.x - cur.x) <= xRange)
            {
                if (Math.abs(node.y - cur.y) <= yRange)
                {
                    set.add(cur);
//                    changeColor(cur, color);
                }
            }
            else
            {
                break;
            }
        }

        // x 轴 往后
        for (CrossLinkNode cur = node.xNext; cur != CrossAoi.xTail; cur = cur.xNext)
        {
            if (Math.abs(node.x - cur.x) <= xRange)
            {
                if (Math.abs(node.y - cur.y) <= yRange)
                {
                    set.add(cur);
//                    changeColor(cur, color);
                }
            }
        }

        // y 轴 往上
        for (CrossLinkNode cur = node.yNext; cur != CrossAoi.yTail; cur = cur.yNext)
        {
            if (Math.abs(node.y - cur.y) <= yRange)
            {
                if (Math.abs(node.x - cur.x) <= xRange)
                {
                    set.add(cur);
//                    changeColor(cur, color);
                }
            }
        }

        //y 轴 往下
        for (CrossLinkNode cur = node.yPrev; cur != CrossAoi.yHead; cur = cur.yPrev)
        {
            if (Math.abs(node.y - cur.y) <= yRange)
            {
                if (Math.abs(node.x - cur.x) <= xRange)
                {
                    set.add(cur);
//                    changeColor(cur, color);
                }
            }
        }
    }

    private void changeColor(CrossLinkNode node, Color color)
    {
        Shape shape = Binder.get(node);
        if (shape != null)
        {
            shape.setFill(color);
        }
    }

    @Override
    public void onRemoveNode(IAoi aoi, CrossLinkNode node)
    {
        chooseBroadSet(node, set);

        if (!set.isEmpty())
        {
            for (Iterator<CrossLinkNode> iterator = set.iterator(); iterator.hasNext(); )
            {
                CrossLinkNode baseNode = iterator.next();
                changeColor(baseNode, removeColor);
            }
        }

        set.clear();
    }

    @Override
    public void beforeMoveTo(IAoi aoi, CrossLinkNode node, int x, int y)
    {
        chooseBroadSet(node, set);
    }

    @Override
    public void afterMoveTo(IAoi aoi, CrossLinkNode node, int fromX, int fromY)
    {
        chooseBroadSet(node, setBack);

        // setBack - set = add
        Sets.SetView<CrossLinkNode> add = Sets.difference(setBack, set);
        for (CrossLinkNode baseNode : add)
        {
            changeColor(baseNode, addColor);
        }

        // set - setBack = remove
        Sets.SetView<CrossLinkNode> remove = Sets.difference(set, setBack);
        for (CrossLinkNode baseNode : remove)
        {
            changeColor(baseNode, removeColor);
        }

        set.clear();
        setBack.clear();
    }
}
