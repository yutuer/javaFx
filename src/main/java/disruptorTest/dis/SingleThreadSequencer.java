package disruptorTest.dis;

import disruptorTest.dis.consume.WaitStrategy;

import java.util.concurrent.locks.LockSupport;

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
        // cachedGatingSequence > nextValue 表示消费者的进度大于生产者进度，nextValue无效，建议忽略，正常情况下不会出现
        // 调用claim(long)方法可能产生该情况，claim可能导致bug，只用在测试
        if (wrapPoint > cachedGatingSequence || cachedGatingSequence > nextValue)
        {
            // 同步下. volatile语义: 后续的读能读取到之前写入的值(cursor), 所以这里的意思是, 将本地线程的缓存值写入共享内存(比如 cursor, 之前一直没有同步.)
            // 插入StoreLoad内存屏障/栅栏，保证可见性。
            // 因为publish使用的是set()/putOrderedLong，并不保证其他消费者能及时看见发布的数据
            // 当我再次申请更多的空间时，必须保证消费者能消费发布的数据
            cursor.putVolatile(nextValue);

            // 同步下的最后序列
            long minSequence;

            // 如果末端的消费者们仍然没让出该插槽则等待，直到消费者们让出该插槽
            // 注意：这是导致死锁的重要原因！
            // 死锁分析：如果消费者挂掉了，而它的sequence没有从gatingSequences中删除的话，则生产者会死锁，它永远等不到消费者更新。
            while (wrapPoint > (minSequence = Util.getMinimumSequence(gatingSequences, nextValue)))
            {
                LockSupport.parkNanos(1L); // TODO: Use waitStrategy to spin?
            }

            // 缓存生产者们最新的消费进度。
            // (该值可能是大于wrapPoint的，那么如果下一次的 wrapPoint小于等于cachedValue则可以直接进行分配)
            // 比如：我可能需要一个插槽位置，结果突然直接消费者们让出来3个插槽位置
            this.cachedValue = minSequence;
        }

        // 这里只写了缓存，并未写volatile变量，因为只是预分配了空间但是并未被发布数据，不需要让其他消费者感知到。
        // 消费者只会感知到真正被发布的序号
        this.nextValue = nextSequence;

        return 0;
    }
}
