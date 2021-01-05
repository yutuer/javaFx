package disruptorTest.dis;

import com.lmax.disruptor.ExceptionHandler;
import disruptorTest.dis.produce.My_RingBuffer;

/**
 * @Description 消费者接口
 * 批事件处理器的事件处理回调接口
 * @Author zhangfan
 * @Date 2021/1/5 16:59
 * @Version 1.0
 */
public interface My_EventHandler<T>
{
    /**
     * {@link My_BatchEventProcessor}会批量的从RingBuffer中取出数据，然后逐个调用该方法进行处理
     * <p>
     * 警告：如果在处理事件时抛出异常，而没有指定{@link ExceptionHandler}时，会导致BatchEventProcessor停止工作，可能导致死锁！
     * -> 系统默认的异常处理{@link My_FatalExceptionHandler}会将异常包装为RuntimeException重新抛出，直接退出循环吗，会导致死锁。
     * <p>
     * 这样的好处是，你可以降低一些操作的消耗，可以攒到批量数据的结尾时进行一次操作。
     * 如IO操作，对写复制容器的操作(写入时尽量将多次写入合并为一次写入)。
     * Called when a publisher has published an event to the {@link My_RingBuffer}.  The {@link My_BatchEventProcessor} will
     * read messages from the {@link My_RingBuffer} in batches, where a batch is all of the events available to be
     * processed without having to wait for any new event to arrive.  This can be useful for event handlers that need
     * to do slower operations like I/O as they can group together the data from multiple events into a single
     * operation.  Implementations should ensure that the operation is always performed when endOfBatch is true as
     * the time between that message an the next one is inderminate.
     *
     * @param event      published to the {@link My_RingBuffer} 序号对应的事件数据
     * @param sequence   of the event being processed 序号
     * @param endOfBatch flag to indicate if this is the last event in a batch from the {@link My_RingBuffer}
     *                   是否是本次批处理的最后一个
     * @throws Exception if the EventHandler would like the exception handled further up the chain.
     */
    void onEvent(T event, long sequence, boolean endOfBatch) throws Exception;
}
