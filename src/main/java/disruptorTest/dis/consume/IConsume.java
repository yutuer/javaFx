package disruptorTest.dis.consume;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/28 8:21
 * @Version 1.0
 */
public interface IConsume extends WaitStrategy
{
    /**
     * 请求一个位置的数据
     *
     * @param value
     * @return
     */
    boolean acquire(long value);
}
