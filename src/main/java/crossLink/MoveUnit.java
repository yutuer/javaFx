package crossLink;

import crossLink.aoi.BaseNode;

/**
 * @Description 一次移动目标的抽象
 * @Author zhangfan
 * @Date 2020/9/18 10:34
 * @Version 1.0
 */
public class MoveUnit
{
    // 当前点
    private int curX;
    private int curY;

    //开始点
    private int startX;
    private int startY;

    // 目标点
    private int targetX;
    private int targetY;

    // 缓存的速度
    private double vx;
    private double vy;

    // 预期这次移动时间
    private int expireEndTime;

    /**
     * 移动速度
     */
    private int v;

    /**
     * 当前经过的总时间
     */
    private int moveTime;

    /**
     * 是否运行中
     */
    private boolean isRunning;

    public MoveUnit(int startX, int startY, int v)
    {
        this.startX = startX;
        this.startY = startY;

        this.curX = startX;
        this.curY = startY;

        this.v = v;
    }

    /**
     * 开始移动处理
     *
     * @param targetX
     * @param targetY
     */
    public void moveStart(int targetX, int targetY)
    {
        this.targetX = targetX;
        this.targetY = targetY;

        isRunning = true;

        expireEndTime = calExpireEndTime();
    }

    private int calExpireEndTime()
    {
        int diffY = targetY - startY;
        int diffX = targetX - startX;

        int diffSqr = diffX * diffX + diffY * diffY;
        double diff = Math.sqrt(diffSqr);

        vx = diff / v * diffX;
        vy = diff / v * diffY;

        return (int) ((diff + v - 1) / v);
    }

    public void tickMove(int interval)
    {
        if (!isRunning)
        {
            return;
        }

        moveTime += interval;
        if (moveTime > expireEndTime)
        {
            isRunning = false;
        }

        curX = (int) (startX + vx * interval);
        curY = (int) (startY + vy * interval);
    }

    /**
     * 同步位置
     *
     * @param baseNode
     */
    public void syncPos(BaseNode baseNode)
    {
        baseNode.x = curX;
        baseNode.y = curY;
    }

    public int getCurX()
    {
        return curX;
    }

    public int getCurY()
    {
        return curY;
    }

}
