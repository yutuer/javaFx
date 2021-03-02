package disruptorTest.dis;

import com.lmax.disruptor.RingBuffer;
import disruptorTest.dis.consume.My_EventProcessor;

/**
 * 此接口是面向消费者的(查询接口). 所以继承了之后, 就同时能使用生产者和消费者的接口了
 * 序号生成器。
 * <p>
 * 这个接口已经封装完了 消费者, 生产者的行为了. 获取了索引之后, 用什么数据结构存储并不重要. 我们可以用一个无限长的数组, 只不过为了复用, 才设计了
 * 环形数组. 但是那个结构已经是在这个接口操作成功之后的处理了.
 * 这个接口 真正做到了只利用接口, 就把逻辑表述清楚了. 我们可以利用这个接口得到插入的索引, 然后将数据放入底层实现. 然后利用接口publish发布出去
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

    /**
     * 为事件处理器创建一个序号屏障，追踪这些Sequence的信息，用于从RingBuffer中获取可用的数据。
     * 为啥放在Sequencer接口中？ Barrier需要知道序号生成器(Sequencer)的生产进度，需要持有Sequencer对象引用。
     * 如果不放在这个接口里面, 就需要用参数传入了(构造方法的方式不通用).
     * <p>
     * Create a new SequenceBarrier to be used by an EventProcessor to track which messages
     * are available to be read from the ring buffer given a list of sequences to track.
     *
     * @param sequencesToTrack All of the sequences that the newly constructed barrier will wait on.
     *                         所有需要追踪的序列，其实也是所有要追踪的前置消费者。
     *                         即消费者只能消费被这些Sequence代表的消费者们已经消费的序列
     * @return A sequence barrier that will track the specified sequences.
     * 一个新创建的用于追踪给定序列的屏障
     */
    My_SequenceBarrier newBarrier(LongForCacheLine[] sequencesToTrack);
}
