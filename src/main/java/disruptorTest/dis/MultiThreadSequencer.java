package disruptorTest.dis;

import com.lmax.disruptor.InsufficientCapacityException;
import disruptorTest.dis.consume.WaitStrategy;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/29 8:16
 * @Version 1.0
 */
public class MultiThreadSequencer extends Sequencer
{
    /**
     * 预分配的生产者序列号
     */
    protected Seq nextValue;

    /**
     * 当前的序号
     */
    protected Seq cachedValue;

    /**
     * 消费者序号
     */
    protected Seq gatingValue;

    public MultiThreadSequencer(int buffSize, WaitStrategy waitStrategy)
    {
        super(buffSize, waitStrategy);
    }

    @Override
    public long next()
    {
        return next();
    }

    @Override
    public long tryNext() throws InsufficientCapacityException
    {
        return 0;
    }

    @Override
    public long remainingCapacity()
    {
        return 0;
    }

    @Override
    public int bufferSize()
    {
        return 0;
    }
}
