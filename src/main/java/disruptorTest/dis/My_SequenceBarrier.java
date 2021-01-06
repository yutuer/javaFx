package disruptorTest.dis;

import com.lmax.disruptor.AlertException;
import com.lmax.disruptor.TimeoutException;

/**
 * 序号屏障（协调屏障）：
 *
 *
 *
 * @Description
 * @Author zhangfan
 * @Date 2021/1/6 21:41
 * @Version 1.0
 */
public interface My_SequenceBarrier
{
    /**
     * 在该屏障上等待，直到该序号的数据可以被消费。
     * 是否可消费取决于生产者的cursor 和 当前事件处理器依赖的的sequence。
     * Wait for the given sequence to be available for consumption.
     *
     * @param sequence to wait for 事件处理器期望消费的下一个序号
     * @return the sequence up to which is available 看见的最大进度(不一定可消费)
     * @throws AlertException       if a status change has occurred for the Disruptor
     * @throws InterruptedException if the thread needs awaking on a condition variable.
     * @throws TimeoutException     if a timeout occurs while waiting for the supplied sequence.
     */
    long waitFor(long sequence) throws AlertException, InterruptedException, TimeoutException;
}
