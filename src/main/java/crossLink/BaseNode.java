package crossLink;

import crossLink.listener.IBaseNodeListener;
import util.Log;

import java.util.Objects;

/**
 * @Description 基类 , 为了逻辑能动态增减, 使用监听器
 * @Author zhangfan
 * @Date 2020/9/15 17:28
 * @Version 1.0
 */
public class BaseNode implements IBaseNodeListener
{
    public BaseNode xPrev;
    public BaseNode xNext;

    public BaseNode yPrev;
    public BaseNode yNext;

    /**
     * 标识
     */
    public String label;

    //  位置
    public int x;
    public int y;

    public BaseNode(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * 以速度v移动
     *
     * @param v
     * @param isLeft
     * @param isUpper
     */
    public void moveTo(int v, boolean isLeft, boolean isUpper)
    {

    }

    /**
     * 瞬移(先删除, 后添加)
     *
     * @param aoi
     * @param newX
     * @param newY
     */
    public void teleportMoveTo(CrossAoi aoi, int newX, int newY)
    {
        final int _x = x;
        final int _y = y;

        // 先不考虑效率

        // 移除
        aoi.removeNode(this);

        x = newX;
        y = newY;

        int left = newX - x;
        int upper = newY - y;

        // 添加
        aoi.addNode(this, left, upper);

        onMoveTo(_x, _y, newX, newY);
    }

    @Override
    public void onMoveTo(int x, int y, int newX, int newY)
    {
        Log.CrossAOI_Logger.info("onMoveTo: From [{}, {}] To [{} {}]", x, y, newX, newY);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseNode baseNode = (BaseNode) o;
        return x == baseNode.x &&
                y == baseNode.y;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }
}
