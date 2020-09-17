package crossLink.aoi;

import crossLink.IAoi;
import crossLink.aoi.cell.CellNode;
import javafx.scene.paint.Color;
import util.Log;

import java.util.Objects;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/17 10:59
 * @Version 1.0
 */
public class BaseNode
{
    // 添加时通知的颜色
    public static Color addColor = Color.ORANGERED;

    // 删除时通知的颜色
    public static Color removeColor = Color.DARKRED;

    /**
     * 标识
     */
    public long label;

    //  位置
    public int x;
    public int y;

    public BaseNode(long label, int x, int y)
    {
        this.label = label;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseNode baseNode = (BaseNode) o;
        return label == baseNode.label;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(label);
    }

    /**
     * 移动
     *
     * @param aoi
     * @param newX
     * @param newY
     */
    public void moveTo(IAoi aoi, int newX, int newY)
    {
        final int _x = x;
        final int _y = y;

        x = newX;
        y = newY;

        onMoveTo(aoi, _x, _y, newX, newY);
    }

    public void onMoveTo(IAoi aoi, int x, int y, int newX, int newY)
    {
        Log.CrossAOI_Logger.info("onMoveTo: From [{}, {}] To [{} {}]", x, y, newX, newY);
    }

    /**
     * 建立联系
     *
     * @param otherNode
     */
    public void addRelation(BaseNode otherNode)
    {
        if (otherNode == null)
        {
            return;
        }

        Log.CrossAOI_Logger.info("{} 和 {} 建立联系!", this.label, otherNode.label);
    }

    /**
     * 移除联系
     *
     * @param
     */
    public void removeRelation(CellNode otherNode)
    {
        if (otherNode == null)
        {
            return;
        }

        Log.CrossAOI_Logger.info("{} 解除和 {} 的联系!", this.label, otherNode.label);
    }

    public void onMoveBroad(BaseNode otherNode)
    {
        if (otherNode == null)
        {
            return;
        }

        Log.CrossAOI_Logger.info("{} 移动广播给 {} ", this.label, otherNode.label);
    }
}
