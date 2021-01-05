package disruptorTest.dis;

/**
 * 该接口是面向消费者的，消费者用于感知生产者的进度。
 * <p>
 * Implementors of this interface must provide a single long value
 * that represents their current cursor value.  Used during dynamic
 * add/remove of Sequences from a
 * {@link My_SequenceGroups#addSequences(Object, java.util.concurrent.atomic.AtomicReferenceFieldUpdater, My_Cursor, LongForCacheLine...)}.
 */
public interface My_Cursor
{
    /**
     * 得到当前值
     *
     * @return
     */
    long getCursor();
}
