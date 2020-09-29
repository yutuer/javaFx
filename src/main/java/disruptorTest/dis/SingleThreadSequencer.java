package disruptorTest.dis;

/**
 * @Description 单线程计数器
 * @Author zhangfan
 * @Date 2020/9/28 23:38
 * @Version 1.0
 */
public class SingleThreadSequencer extends Sequencer
{

    /**
     * 预分配的生产者序列号
     */
    protected long nextValue;

    /**
     * 当前的序号
     */
    protected long cachedValue;

    /**
     * 消费者序号
     */
    protected long gatingValue;

    public SingleThreadSequencer(int buffSize)
    {
        super(buffSize);
    }

    @Override
    public long next()
    {
        return next(1);
    }

    private long next(int i)
    {
        final long nextValue = this.nextValue;
        long judgeValue = nextValue - buffSize;


        return 0;
    }
}
