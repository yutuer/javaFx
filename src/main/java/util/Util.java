package util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/2 17:29
 * @Version 1.0
 */
public class Util
{

    // 因为直接调用Unsafe.getUnsafe()会报错, 所以这里只能这么获取
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
     * 首字母小写
     *
     * @param name
     * @return
     */
    public static String firstLetterLower(String name, int index)
    {
        final int LowerIndex = index;
        return name.substring(0, LowerIndex).toLowerCase() + name.substring(LowerIndex);
    }

    /**
     * 首字母大写
     *
     * @param name
     * @return
     */
    public static String firstLetterUpper(String name, int index)
    {
        final int upperIndex = index;
        return name.substring(0, upperIndex).toUpperCase() + name.substring(upperIndex);
    }

    /**
     * 得到>= x的2次幂
     *
     * @param x
     * @return
     */
    public static int intToMaxTowPower(int x)
    {
        if ((x & (x - 1)) == 0)
        {
            return x;
        }

        // 左边的0的个数
        int leftZeroCount = Integer.numberOfLeadingZeros(x);
        // 无符号右移
        return Integer.MIN_VALUE >>> (leftZeroCount - 1);
    }

    public static void main(String[] args)
    {
        System.out.println(intToMaxTowPower(0b10011));

        Unsafe unsafe1 = Util.getUnsafe();
        unsafe1.allocateMemory(4 * 1024);

    }

}
