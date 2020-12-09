package disruptorTest.dis;

import com.lmax.disruptor.InsufficientCapacityException;

/**
 * @Description 序号产生器接口, 生产者
 * @Author zhangfan
 * @Date 2020/9/27 23:26
 * @Version 1.0
 */
public interface Seqer
{
    /**
     * 下一个空格
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
