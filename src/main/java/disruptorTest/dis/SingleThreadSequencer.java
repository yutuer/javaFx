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
     * <p>
     * 这个名字其实有点坑爹，其实不是下一个value。
     */
    long nextValue;

    /**
     * 网关序列的最小序号缓存。
     * 因为是单线程的生产者，数据无竞争，因此使用普通的long变量即可。(为什么是单线程的?  应该是多线程的才对啊, 因为消费者是多个)
     * <p>
     * Q: 该缓存值的作用？
     * A: 除了直观上的减少对{@link #gatingSequences}的遍历产生的volatile读以外，还可以提高缓存命中率。
     * <p>
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

        //条件为 cachedValue <= nextValue && nextValue - buffSize <= cachedValue

        // 已分配的序号的缓存(已分配到这里)，初始值为 -1
        long nextValue = this.nextValue;

        // 本次申请分配的序号
        long nextSequence = nextValue + n;

        // 构成环路的点：环形缓冲区可能追尾的点 = 等于本次申请的序号-环形缓冲区大小
        // 如果该序号大于最慢消费者的进度，那么表示追尾了，需要等待
        long wrapPoint = nextValue - buffSize;

        // 上次缓存的最小网关序号(消费最慢的消费者的进度)
        long cachedGatingSequence = this.cachedValue;


        // wrapPoint > cachedGatingSequence 表示生产者追上消费者产生环路(追尾)，即缓冲区已满，此时需要获取消费者们最新的进度，以确定是否队列满

        if (wrapPoint > cachedGatingSequence || cachedGatingSequence > nextValue) // ? 这里为什么是nextValue, 而不是nextSequence
        {

        }

        return 0;
    }
}
