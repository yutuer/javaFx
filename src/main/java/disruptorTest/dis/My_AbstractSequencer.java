package disruptorTest.dis;

import disruptorTest.dis.consume.My_WaitStrategy;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 序号产生器
 * 计数调度器超类, 底层实现可以使用环形数组, 也可以使用普通数组
 * 数组的约束用此类实现
 *
 * @Author zhangfan
 * @Date 2020/9/29 7:40
 * @Version 1.0
 */
public abstract class My_AbstractSequencer implements My_Sequencer
{
    /**
     * 原子方式更新 追踪的Sequences
     */
    private static final AtomicReferenceFieldUpdater<My_AbstractSequencer, LongForCacheLine[]> SEQUENCE_UPDATER =
            AtomicReferenceFieldUpdater.newUpdater(My_AbstractSequencer.class, LongForCacheLine[].class, "gatingSequences");

    /**
     * 限制容量的,  如果底层不是一个环形数组, 那么这个值可以用来约束生产者和消费者的索引 差
     */
    protected int bufferSize;

    /**
     * 生产者的序列，表示生产者的进度。
     * PS: 代码里面的带cursor的都表示生产者们的Sequence。
     * <p>
     * 消费者与生产者之间的交互（可见性保证）是通过volatile变量的读写来保证的。
     * 当前的序号
     */
    protected final LongForCacheLine cursor = new LongForCacheLine(LongForCacheLine.INITIAL_VALUE);

    /**
     * 网关Sequences，序号生成器必须和这些Sequence满足约束:
     * cursor-bufferSize <= Min(gatingSequence)
     * 即：所有的gatingSequences让出下一个插槽后，生产者才能获取该插槽。
     * <p>
     * 这里的值都是经过同步的真实的值, 不是缓存
     */
    protected volatile LongForCacheLine[] gatingSequences = new LongForCacheLine[0];

    /**
     * 一个生产者和消费者 都会使用的 等待策略
     */
    protected My_WaitStrategy myWaitStrategy;

    public My_AbstractSequencer(int bufferSize, My_WaitStrategy myWaitStrategy)
    {
        this.bufferSize = bufferSize;
        this.myWaitStrategy = myWaitStrategy;
    }

    @Override
    public int getBufferSize()
    {
        return bufferSize;
    }

    @Override
    public long getCursor()
    {
        return cursor.get();
    }

    /**
     * 添加网关Sequence(末端消费者的消费进度)，这些消费者的进度需要被关心
     *
     * @see My_SequenceGroups#addGatingSequences(LongForCacheLine...)
     */
    @Override
    public final void addGatingSequences(LongForCacheLine... gatingSequences)
    {
        My_SequenceGroups.addSequences(this, SEQUENCE_UPDATER, this, gatingSequences);
    }


}
