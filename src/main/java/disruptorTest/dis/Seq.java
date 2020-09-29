package disruptorTest.dis;

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

    private Unsafe unsafe = Unsafe.getUnsafe();

    public Seq()
    {
    }

    public Seq(long value)
    {
        this.value = value;
    }

    public long getValue()
    {
        return value;
    }


}
