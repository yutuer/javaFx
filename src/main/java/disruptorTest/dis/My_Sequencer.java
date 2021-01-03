package disruptorTest.dis;

import com.lmax.disruptor.RingBuffer;
import disruptorTest.dis.consume.My_EventProcessor;

/**
 * 此接口是面向消费者的(查询接口). 所以继承了之后, 就同时能使用生产者和消费者的接口了
 * 序号生成器。
 *
 * @Author zhangfan
 * @Date 2021/1/3 15:11
 * @Version 1.0
 */
public interface My_Sequencer extends My_Cursor, My_Sequenced
{
    /**
     * 将-1作为默认序号
     * Set to -1 as sequence starting point
     */
    long INITIAL_CURSOR_VALUE = -1L;

    /**
     * 指定序号的数据是否可用(是否已发布)。
     * <p>
     * Confirms if a sequence is published and the event is available for use; non-blocking.
     *
     * @param sequence of the buffer to check
     * @return true if the sequence is available for use, false if not
     */
    boolean isAvailable(long sequence);

    /**
     * 添加序号生成器需要追踪的网关Sequence（新增的末端消费者消费序列/进度），
     * Sequencer（生产者）会持续跟踪它们的进度信息，以协调生产者和消费者之间的速度。
     * 即生产者想使用一个序号时必须等待所有的网关Sequence处理完该序号。
     * <p>
     * Add the specified gating sequences to this instance of the Disruptor.  They will
     * safely and atomically added to the list of gating sequences.
     *
     * @param gatingSequences The sequences to add.
     */
    void addGatingSequences(LongForCacheLine... gatingSequences);

    /**
     * 移除这些网关Sequence(消费者消费序列/进度)，不再跟踪它们的进度信息；
     * 特殊用法：如果移除了所有的消费者，那么生产者便不会被阻塞，也就能{@link RingBuffer#next()} 死循环中醒来！
     * 但是比较坑爹的是，你只有自己去实现{@link My_EventProcessor}时，才能在线程退出时移除自己的sequence。
     * <p>
     * Remove the specified sequence from this sequencer.
     *
     * @param sequence to be removed.
     * @return <tt>true</tt> if this sequence was found, <tt>false</tt> otherwise.
     */
    boolean removeGatingSequence(LongForCacheLine sequence);

}
