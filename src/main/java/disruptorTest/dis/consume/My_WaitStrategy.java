package disruptorTest.dis.consume;

import java.util.concurrent.TimeUnit;

/**
 * @Description 等待策略
 * @Author zhangfan
 * @Date 2020/9/28 8:19
 * @Version 1.0
 */
public interface My_WaitStrategy
{

    /**
     * 等待策略
     *
     * @param expect
     * @param timeout
     * @param timeUnit
     */
    void waitFor(long expect, long timeout, TimeUnit timeUnit);

}
