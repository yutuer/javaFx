package disruptorTest.dis;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/10/20 10:07
 * @Version 1.0
 */
public class My_Util
{
    private static final Unsafe THE_UNSAFE;

    static
    {
        try
        {
            final PrivilegedExceptionAction<Unsafe> action = new PrivilegedExceptionAction<Unsafe>()
            {
                public Unsafe run() throws Exception
                {
                    Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
                    theUnsafe.setAccessible(true);
                    return (Unsafe) theUnsafe.get(null);
                }
            };
            THE_UNSAFE = AccessController.doPrivileged(action);
        }
        catch (Exception e)
        {
            throw new RuntimeException("Unable to load unsafe", e);
        }
    }

    /**
     * Get a handle on the Unsafe instance, used for accessing low-level concurrency
     * and memory constructs.
     *
     * @return The Unsafe
     */
    public static Unsafe getUnsafe()
    {
        return THE_UNSAFE;
    }

    /**
     * 找出Sequence中序号最小的Sequence的序号。
     *
     * @param sequences
     * @param minimum
     * @return
     */
    public static long getMinimumSequence(final LongForCacheLine[] sequences, long minimum)
    {
        for (int i = 0, n = sequences.length; i < n; i++)
        {
            long value = sequences[i].get();
            minimum = Math.min(minimum, value);
        }
        return minimum;
    }
}
