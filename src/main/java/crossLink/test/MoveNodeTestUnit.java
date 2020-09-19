package crossLink.test;

import crossLink.IAoi;
import crossLink.MoveUnit;

import java.util.Random;

/**
 * @Description
 * @Author zhangfan
 * @Date 2020/9/18 14:51
 * @Version 1.0
 */
public class MoveNodeTestUnit implements ITestAoi, Runnable
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

    private MoveUnit moveUnit;

    /**
     * 开始移动时间
     */
    private long lastMoveTime;

    public MoveNodeTestUnit(long label, int v)
    {
        this.label = label;
        this.v = v;
    }

    @Override
    public void run()
    {
        if (moveUnit != null)
        {
            long now = System.currentTimeMillis();
            long interval = now - lastMoveTime;
            lastMoveTime = now;

            moveUnit.tickMove((int) interval);

        }
    }

    @Override
    public void accpetInput(IAoi iaoi)
    {
        moveUnit = new MoveUnit(iaoi, iaoi.getNode(label), v);

        // 先指定一个随机的位置
        moveUnit.moveStart((int) (random.nextDouble() * iaoi.getXRange()), (int) (random.nextDouble() * iaoi.getYRange()));

        lastMoveTime = System.currentTimeMillis();
    }
}
