package disruptorTest.dis;

import com.lmax.disruptor.InsufficientCapacityException;
import disruptorTest.dis.consume.My_WaitStrategy;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/29 8:16
 * @Version 1.0
 */
public class MultiThreadMySequencer extends My_Sequencer
{
    /**
     * 预分配的生产者序列号
     */
    protected MultiThreadLongSeq nextValue;

    /**
     * 当前的序号
     */
    protected MultiThreadLongSeq cachedValue;

    /**
     * 消费者序号
     */
    protected MultiThreadLongSeq gatingValue;

    public MultiThreadMySequencer(int buffSize, My_WaitStrategy myWaitStrategy)
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
