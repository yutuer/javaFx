package crossLink;

import crossLink.aoi.BaseNode;
import crossLink.aoi.IPos;
import util.Log;

/**
 * @Description 一次移动目标的抽象
 * @Author zhangfan
 * @Date 2020/9/18 10:34
 * @Version 1.0
 */
public class MoveUnit
{
    private IAoi iaoi;
    private IPos iPos;

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
    private int expectEndTime;

    /**
     * 超过时间
     */
    private int expireTime;

    /**
     * 移动速度
     */
    private double v;

    /**
     * 当前经过的总时间
     */
    private int moveTime;

    /**
     * 是否运行中
     */
    private boolean isRunning;

    public MoveUnit(IAoi iaoi, IPos iPos, int v)
    {
        this.iaoi = iaoi;
        this.iPos = iPos;

        this.startX = iPos.getX();
        this.startY = iPos.getY();

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

        expectEndTime = calExpireEndTime();

        expectEndTime = calExpireEndTime();

        Log.CrossAOI_Logger.warn("moveStart, startX:{}, startY:{}, targetX:{},  targetY:{}, expectEndTime:{} ",
                startX, startY, targetX, targetY, expectEndTime);
    }

    private int calExpireEndTime()
    {
        long diffY = targetY - startY;
        long diffX = targetX - startX;

        long diffSqr = diffX * diffX + diffY * diffY;
        double diff = Math.sqrt(diffSqr);

        vx = diffX / diff * v;
        vy = diffY / diff * v;

        return (int) (Math.ceil(diff / v * 1000));
    }

    public boolean tickMove(int interval)
    {
        if (!isRunning)
        {
            return isRunning;
        }

        moveTime += interval;
        if (moveTime > expectEndTime)
        {
            expireTime = moveTime - expectEndTime;
            moveTime = expectEndTime;

            isRunning = false;
        }

        curX = (int) Math.round(startX + vx * moveTime / 1000);
        curY = (int) Math.round(startY + vy * moveTime / 1000);

        Log.CrossAOI_Logger.info("curX:{}, curY:{}, vx:{}, vy:{}, moveTime:{}, expireTime:{}",
                curX, curY, vx, vy, moveTime, expireTime);

        if (curX <= 0 || curX >= iaoi.getXRange())
        {
            isRunning = false;

            if (curX < 0) curX = 0;
            if (curX > iaoi.getXRange()) curX = iaoi.getXRange();
        }
        if (curY <= 0 || curY >= iaoi.getYRange())
        {
            isRunning = false;

            if (curY < 0) curY = 0;
            if (curY > iaoi.getYRange()) curY = iaoi.getYRange();
        }

        syncPos();

        return isRunning;
    }

    /**
     * 同步位置
     */
    private void syncPos()
    {
        if (iPos instanceof BaseNode)
        {
            iaoi.moveNode(BaseNode.class.cast(iPos), curX, curY);
        }
    }

    public int getExpireTime()
    {
        return expireTime;
    }
}
