package disruptorTest.dis;

import com.lmax.disruptor.util.Util;
import sun.misc.Contended;
import sun.misc.Unsafe;

/**
 * @Description 包装了long, 替代了long值的作用. 多线程下使用
 * @Author zhangfan
 * @Date 2020/9/27 23:28
 * @Version 1.0
 */
public class Seq
{
    public static final long INITIAL_VALUE = -1L;

    @Contended
    protected long value;

    private static Unsafe UNSAFE;

    private static long VALUE_OFFSET;

    static
    {
        UNSAFE = Util.getUnsafe();
        try
        {
            VALUE_OFFSET = UNSAFE.objectFieldOffset(Seq.class.getDeclaredField("value"));
        }
        catch (final Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public Seq()
    {
    }

    public Seq(long value)
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
     * 后续的任意volatile变量读操作之间插入一个StoreLoad屏障，保证当前的volatile写操作
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
