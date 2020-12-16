package disruptorTest.dis;

import com.lmax.disruptor.InsufficientCapacityException;

/**
 * 该接口是面向生产者的，生产者通过该接口获取队列容量，和可用空间，并在填充数据之后，发布对应的sequence（这些sequence对应的数据已可消费）。
 * <p>
 * 申请空间之后，必须使用对应的发布方法，发布数据(表示这些空间的数据已经可用)
 * {@link #next()} {@link #tryNext()} ---> {@link #publish(long)}
 * {@link #next(int)} {@link #tryNext(int)} ---> {@link #publish(long, long)}
 * 否则会因为数据结构出现断层，从而导致整个数据结构不可用。
 *
 * @Description
 * @Author zhangfan
 * @Date 2020/9/27 23:26
 * @Version 1.0
 */
public interface Seqer
{
    /**
     * 获取下一个数据的索引，空间不足是会阻塞(等待)
     * 申请完空间之后,必须使用 {@link #publish(long)} 发布，否则会导致整个数据结构不可用
     * <p>
     * 警告：一旦进入该方法，除非有空间，否则无法退出，连中断都没有检查 -> 即使消费者已经停止运行了，生产者也无法退出，可能导致死锁。
     *
     * @return 下一个索引位置(缓存的)
     */
    long next();

    /**
     * 尝试获取下一个数据的索引位置。空间不足时抛出异常。
     * 申请完空间之后,必须使用 {@link #publish(long)} 发布，否则会导致整个数据结构不可用。
     * <b>使用该方法可以避免死锁</b>
     * <p>
     * Attempt to claim the next event in sequence for publishing.  Will return the
     * number of the slot if there is at least <code>requiredCapacity</code> slots
     * available.
     *
     * @return the claimed sequence value
     * @throws InsufficientCapacityException thrown if there is no space available in the ring buffer.
     */
    long tryNext() throws InsufficientCapacityException;

    /**
     * 当前剩余容量，并不一定具有价值，因为多线程模型下查询容器的当前大小，它反映的总是一个旧值。
     * Get the remaining capacity for this sequencer.
     *
     * @return The number of slots remaining.
     */
    long remainingCapacity();

    /**
     * @return
     */
    int bufferSize();
}
