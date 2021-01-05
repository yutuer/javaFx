package disruptorTest.dis.consume;

import java.util.concurrent.TimeUnit;

/**
 * 面向消费者的接口
 * EventProcessor等待特定的序号可用(可以被消费)--- 序号不可用的时候，实现自己的等待策略。
 * 重点：我等待的序号什么时候可以被消费？
 * ☆当我依赖的序列的序号大于我需要消费的序号时，就可以消费了。
 * <p>
 * 1.对于直接与生产者相连的消费者来讲，依赖的序列就是生产者的序列。
 * 2.对于其它消费者来讲，依赖的序列就是它的所有直接前驱消费者的序列。
 *
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

    /**
     * 如果在等待生产者生产数据时，采用了等待通知协议(wait/notify)，那么当生产者进度更新时，需要通知这些被阻塞的事件处理器
     * <p>
     * Implementations should signal the waiting {@link My_EventProcessor}s that the cursor has advanced.
     */
    void signalAllWhenBlocking();
}
