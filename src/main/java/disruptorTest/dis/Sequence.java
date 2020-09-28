package disruptorTest.dis;

import sun.misc.Contended;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/27 23:28
 * @Version 1.0
 */
public abstract class Sequence implements Seq
{
    @Contended
    protected long value;

    public Sequence()
    {
    }

    public Sequence(long value)
    {
        this.value = value;
    }

    @Override
    public long getCursor()
    {
        return value;
    }

}
