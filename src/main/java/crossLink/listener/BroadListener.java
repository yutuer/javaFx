package crossLink.listener;

import crossLink.BaseNode;
import crossLink.Binder;
import crossLink.CrossAoi;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description 广播监听器.  让受到影响的其他的点变色
 * @Author zhangfan
 * @Date 2020/9/15 21:13
 * @Version 1.0
 */
public class BroadListener implements AoiListener
{
    /**
     * 影响范围
     */
    private int xRange;
    private int yRange;

    private Color color = Color.ORANGERED;

    private Set<BaseNode> set = new HashSet<>();

    public BroadListener(int xRange, int yRange)
    {
        this.xRange = xRange;
        this.yRange = yRange;
    }

    @Override
    public void onAddNode(BaseNode node)
    {
        // 自己变色
        changeColor(node, Color.GOLD);

        broadCast(node);
    }

    private void broadCast(BaseNode node)
    {
        //x 轴 往前
        for (BaseNode cur = node.xPrev; cur != CrossAoi.xHead; cur = cur.xPrev)
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
        for (BaseNode cur = node.xNext; cur != CrossAoi.xTail; cur = cur.xNext)
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
        for (BaseNode cur = node.yNext; cur != CrossAoi.yTail; cur = cur.yNext)
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
        for (BaseNode cur = node.yPrev; cur != CrossAoi.yHead; cur = cur.yPrev)
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

        if (!set.isEmpty())
        {
            for (Iterator<BaseNode> iterator = set.iterator(); iterator.hasNext(); )
            {
                BaseNode baseNode = iterator.next();
                changeColor(baseNode, color);
            }
        }

        set.clear();
    }

    private void changeColor(BaseNode node, Color color)
    {
        Shape shape = Binder.get(node);
        if (shape != null)
        {
            shape.setFill(color);
        }
    }

    @Override
    public void onRemoveNode(BaseNode node)
    {
        broadCast(node);
    }

    @Override
    public void onTeleportMoveTo(BaseNode node, int x, int y)
    {

    }
}
