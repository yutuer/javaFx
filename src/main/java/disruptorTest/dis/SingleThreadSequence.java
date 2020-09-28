package disruptorTest.dis;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/28 23:38
 * @Version 1.0
 */
public class SingleThreadSequence extends Sequence
{
    @Override
    public long next()
    {
        return ++value;
    }
}
