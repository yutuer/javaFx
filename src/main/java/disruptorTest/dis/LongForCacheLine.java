package disruptorTest.dis;

import sun.misc.Contended;
import sun.misc.Unsafe;

/**
 * @Description 包装了long, 使用了缓存行, 替代了long值的作用. 多线程下使用
 * @Author zhangfan
 * @Date 2020/9/27 23:28
 * @Version 1.0
 */
public class LongForCacheLine
{
    public static final long INITIAL_VALUE = -1L;

    // 注意, 此处必须是volatile的
    @Contended
    protected volatile long value;

    private static Unsafe UNSAFE;

    private static long VALUE_OFFSET;

    static
    {
        UNSAFE = My_Util.getUnsafe();
        try
        {
            VALUE_OFFSET = UNSAFE.objectFieldOffset(LongForCacheLine.class.getDeclaredField("value"));
        }
        catch (final Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public LongForCacheLine()
    {
        this(INITIAL_VALUE);
    }

    public LongForCacheLine(long initialValue)
    {
        // 这里使用Ordered模式写入就可以保证：对象的发布操作不会重排序到对象构造完成前（其它线程不会看见构造未完成的对象）。
        // 会比volatile开销低一些
        UNSAFE.putOrderedLong(this, VALUE_OFFSET, initialValue);
    }

    /**
     * value字段volatile读操作
     *
     * @return
     */
    public long get()
    {
        return value;
    }

    /**
     * 对sequence执行一个Ordered模式写操作,而不是volatile模式写操作。
     * 目的是在当前写操作和之前的写操作直接插入一个StoreStore屏障,保证屏障之前的写操作先于当前写操作
     * 对其他CPU可见。(减少内存屏障的消耗，StoreLoad屏障消耗较大)
     * <p>
     * 在J9中，对内存屏障的使用进行了规范，用{@code setRelease}代替了LazySet/putOrdered叫法。
     * （对应C++ 11中的Release Acquire模式）
     * <p>
     * 更多内存屏障/内存栅栏信息请查阅资料。
     * （建议看一下J9的VarHandle类）
     * <p>
     * Perform an ordered write of this sequence.  The intent is
     * a Store/Store barrier between this write and any previous
     * store.
     *
     * @param value The new value for the sequence.
     */
    public void set(long value)
    {
        UNSAFE.putOrderedLong(this, VALUE_OFFSET, value);
    }

    /**
     * 执行一个volatile写操作。
     * 目的是在当前写操作与之前的写操作之间插入一个StoreStore屏障，在当前写操作和
     * 后续的任意volatile变量读操作之间插入一个StoreLoad屏障(消费大)，保证当前的volatile写操作
     * 对后续的volatile读操作立即可见。
     * <p>
     * 更多内存屏障/内存栅栏信息请查阅资料。
     *
     * @param value
     */
    public void putVolatile(final long value)
    {
        UNSAFE.putLongVolatile(this, VALUE_OFFSET, value);
    }

}
