package disruptorTest.dis;

import com.lmax.disruptor.InsufficientCapacityException;
import disruptorTest.dis.consume.My_WaitStrategy;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/29 8:16
 * @Version 1.0
 */
public class My_MultiThreadSequencer extends My_AbstractSequencer
{
    /**
     * 预分配的生产者序列号
     */
    protected LongForCacheLine nextValue;

    /**
     * 当前的序号
     */
    protected LongForCacheLine cachedValue;

    /**
     * 消费者序号
     */
    protected LongForCacheLine gatingValue;

    public My_MultiThreadSequencer(int buffSize, My_WaitStrategy myWaitStrategy)
    {
        super(buffSize, myWaitStrategy);
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
    public long tryNext(int n) throws InsufficientCapacityException
    {
        return 0;
    }

    @Override
    public long remainingCapacity()
    {
        return 0;
    }

    @Override
    public void publish(long sequence)
    {

    }

    @Override
    public void publish(long lo, long hi)
    {

    }

    @Override
    public boolean isAvailable(long sequence)
    {
        return false;
    }

    @Override
    public My_SequenceBarrier newBarrier(LongForCacheLine[] sequencesToTrack)
    {
        return null;
    }
}
