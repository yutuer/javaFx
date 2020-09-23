package crossLink.test;

import crossLink.IAoi;
import crossLink.MoveUnit;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Description
 * @Author zhangfan
 * @Date 2020/9/18 14:51
 * @Version 1.0
 */
public class MoveNodeTestUnit implements ITestAoi, Callable<Boolean>
{

    private Random random = new Random();

    /**
     * 要处理的label
     */
    private long label;

    /**
     * 需要测试的节点速度
     */
    private int v;

    private int targetX;
    private int targetY;

    private MoveUnit moveUnit;

    /**
     * 开始移动时间
     */
    private long lastMoveTime;

    /**
     * 移动间隔
     */
    private int moveInterval;

    public MoveNodeTestUnit(long label, int v, int targetX, int targetY, int moveInterval)
    {
        this.label = label;
        this.v = v;
        this.targetX = targetX;
        this.targetY = targetY;
        this.moveInterval = moveInterval;
    }

    @Override
    public Boolean call() throws Exception
    {
        if (moveUnit != null)
        {
            long now = System.currentTimeMillis();
            long interval = now - lastMoveTime;
            lastMoveTime = now;

            boolean isRunning = moveUnit.tickMove((int) interval);
            // 测试的话, 不等待, 直接传入时间间隔
//            boolean isRunning = moveUnit.tickMove(moveInterval);
            return isRunning;
        }
        return false;
    }

    public void startMoveTick(ScheduledExecutorService ses)
    {
        ScheduledFuture<Boolean> schedule = ses.schedule(this, moveInterval, TimeUnit.MILLISECONDS);
//        ScheduledFuture<Boolean> schedule = ses.schedule(this, 0, TimeUnit.MILLISECONDS);
        try
        {
            Boolean isRunning = schedule.get();
            if (isRunning)
            {
                startMoveTick(ses);
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void accpetInput(IAoi iaoi)
    {
        moveUnit = new MoveUnit(iaoi, iaoi.getNode(label), v);

        // 先指定一个随机的位置
        moveUnit.moveStart(targetX, targetY);

        lastMoveTime = System.currentTimeMillis();
    }
}
