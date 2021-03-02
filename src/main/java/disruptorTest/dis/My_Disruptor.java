package disruptorTest.dis;

import com.lmax.disruptor.WorkerPool;
import com.lmax.disruptor.dsl.BasicExecutor;
import disruptorTest.dis.consume.My_WaitStrategy;
import disruptorTest.dis.produce.My_EventFactory;
import disruptorTest.dis.produce.My_RingBuffer;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 1.该类其实是个门面，用于帮助用户组织消费者。
 * <p>
 * 2.{@link #handleEventsWith(My_EventHandler[])}
 * {@link #handleEventsWith(My_EventProcessor...)}
 * {@link #handleEventsWith(My_EventProcessorFactory[])}
 * 所有的 {@code handleEventsWith} 都是添加消费者，每一个{@link My_EventHandler}、
 * {@link My_EventProcessor}、{@link My_EventProcessorFactory}都会被包装为一个独立的消费者{@link My_BatchEventProcessor}
 * 数组长度就代表了添加的消费者个数。
 * <p>
 * 3.{@link #handleEventsWithWorkerPool(My_WorkHandler[])} 方法为添加一个多线程的消费者，这些handler共同构成一个消费者.
 * （WorkHandler[]会被包装为 {@link WorkerPool}，一个WorkPool是一个消费者，WorkPool中的Handler们
 * 协作共同完成消费。一个事件只会被WokerPool中的某一个WorkHandler消费。(负载均衡)
 * 数组的长度决定了线程池内的线程数量。
 *
 * <p>A DSL-style API for setting up the disruptor pattern around a ring buffer
 * (aka the Builder pattern).</p>
 *
 * <p>A simple example of setting up the disruptor with two event handlers that
 * must process events in order:</p>
 * <pre>
 * <code>Disruptor&lt;MyEvent&gt; disruptor = new Disruptor&lt;MyEvent&gt;(MyEvent.FACTORY, 32, Executors.newCachedThreadPool());
 * EventHandler&lt;MyEvent&gt; handler1 = new EventHandler&lt;MyEvent&gt;() { ... };
 * EventHandler&lt;MyEvent&gt; handler2 = new EventHandler&lt;MyEvent&gt;() { ... };
 * disruptor.handleEventsWith(handler1);
 * disruptor.after(handler1).handleEventsWith(handler2);
 *
 * RingBuffer ringBuffer = disruptor.start();</code>
 * </pre>
 *
 * @param <T> the type of event used.
 * @Description
 * @Author zhangfan
 * @Date 2021/1/5 17:06
 * @Version 1.0
 */
public class My_Disruptor<T>
{
    private final My_RingBuffer<T> ringBuffer;

    /**
     * 为消费者创建线程用。
     * 查看{@link BasicExecutor}以避免死锁问题。
     */
    private final Executor executor;

    /**
     * 运行状态标记
     */
    private final AtomicBoolean started = new AtomicBoolean(false);

    /**
     * 创建一个Disruptor 可以自定义所有参数
     * Create a new Disruptor.
     *
     * @param eventFactory   the factory to create events in the ring buffer.
     *                       事件工厂，为环形缓冲区填充数据使用
     * @param ringBufferSize the size of the ring buffer, must be power of 2.
     *                       环形缓冲区大小，必须是2的n次方
     * @param threadFactory  a {@link ThreadFactory} to create threads for processors.
     *                       事件处理器的线程工厂(每一个EventProcessor需要一个独立的线程)
     * @param producerType   the claim strategy to use for the ring buffer.
     *                       生产者模型
     * @param waitStrategy   the wait strategy to use for the ring buffer.
     *                       等待策略，事件处理器在等待可用数据时的策略。
     */
    public My_Disruptor(
            final My_EventFactory<T> eventFactory,
            final int ringBufferSize,
            final ThreadFactory threadFactory,
            final My_ProducerType producerType,
            final My_WaitStrategy waitStrategy)
    {
        this(
                My_RingBuffer.create(producerType, eventFactory, ringBufferSize, waitStrategy),
                new My_BasicExecutor(threadFactory));
    }

    /**
     * Private constructor helper
     */
    private My_Disruptor(final My_RingBuffer<T> ringBuffer, final Executor executor)
    {
        this.ringBuffer = ringBuffer;
        this.executor = executor;
    }

    /**
     * 这里为什么要用 <? super T>呢?
     *
     * @param handlers
     * @return
     */
    public final My_EventHandlerGroup<T> handleEventsWith(final My_EventHandler<? super T>... handlers)
    {
        return createEventProcessors(new LongForCacheLine[0], handlers);
    }

    /**
     * 确保disruptor还未启动
     */
    private void checkNotStarted()
    {
        if (started.get())
        {
            throw new IllegalStateException("All event handlers must be added before calling starts.");
        }
    }

    /**
     * 确保disruptor只启动一次
     */
    private void checkOnlyStartedOnce()
    {
        if (!started.compareAndSet(false, true))
        {
            throw new IllegalStateException("Disruptor.start() must only be called once.");
        }
    }

    /**
     * 创建事件处理器(创建BatchEventProcessor消费者)
     *
     * @param barrierSequences
     * @param eventHandlers
     * @return
     */
    My_EventHandlerGroup<T> createEventProcessors(
            final LongForCacheLine[] barrierSequences,
            final My_EventHandler<? super T>[] eventHandlers)
    {
        // 组织消费者之间的关系只能在启动之前
        checkNotStarted();

        // 收集添加的消费者的序号
        final LongForCacheLine[] processorSequences = new LongForCacheLine[eventHandlers.length];
        // 本批次消费由于添加在同一个节点之后，因此共享该屏障
        final My_SequenceBarrier barrier = ringBuffer.newBarrier(barrierSequences);

        // 创建单线程消费者(BatchEventProcessor)


        return null;
    }
}
