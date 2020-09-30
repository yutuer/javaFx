package disruptorTest.dis;

import disruptorTest.dis.consume.WaitStrategy;

/**
 * @Description 单线程计数器
 * @Author zhangfan
 * @Date 2020/9/28 23:38
 * @Version 1.0
 */
public class SingleThreadSequencer extends Sequencer
{

    /**
     * 已预分配的缓存，因为是单线程的生产者，不存在竞争，因此采用普通的long变量
     * 表示 {@link #cursor} +1 ~  nextValue 这段空间被预分配出去了，但是可能还未填充数据。
     * 会在真正分配空间时更新
     */
    long nextValue;

    /**
     * 网关序列的最小序号缓存。
     * 因为是单线程的生产者，数据无竞争，因此使用普通的long变量即可。(为什么是单线程的?  应该是多线程的才对啊, 因为消费者是多个)
     *
     * Q: 该缓存值的作用？
     * A: 除了直观上的减少对{@link #gatingSequences}的遍历产生的volatile读以外，还可以提高缓存命中率。
     *
     * PS: 使用一个变化频率较低的值代替一个变化频率较高的值，提高读效率。
     */
    long cachedValue;

    public SingleThreadSequencer(int buffSize, WaitStrategy waitStrategy)
    {
        super(buffSize, waitStrategy);
    }

    @Override
    public long next()
    {
        return next(1);
    }

    private long next(int n)
    {
        if (n < 1)
        {
            throw new IllegalArgumentException("n must be > 0");
        }

        // 已分配的序号的缓存(已分配到这里)，初始-1
        long nextValue = this.nextValue;

        long judgeValue = nextValue - buffSize;


        return 0;
    }
}
