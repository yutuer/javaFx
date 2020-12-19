package disruptorTest.dis;

import sun.misc.Contended;
import sun.misc.Unsafe;

/**
 * @Description 包装了long, 使用了缓存行, 替代了long值的作用. 多线程下使用
 * @Author zhangfan
 * @Date 2020/9/27 23:28
 * @Version 1.0
 */
public class MultiThreadLongSeq
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
            VALUE_OFFSET = UNSAFE.objectFieldOffset(MultiThreadLongSeq.class.getDeclaredField("value"));
        }
        catch (final Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public MultiThreadLongSeq()
    {
    }

    public MultiThreadLongSeq(long value)
    {
        this.value = value;
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
