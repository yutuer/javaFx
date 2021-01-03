package disruptorTest.dis;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2021/1/3 17:14
 * @Version 1.0
 */
public class My_SequenceGroups
{

    /**
     * 原子方式添加Sequence,并将要添加的Sequence进度设置为生产者最新的值
     *
     * @param holder         域所属的对象
     * @param updater        域更新器
     * @param cursor         生产者光标(进度)
     * @param sequencesToAdd 要添加的Sequences
     * @param <T>
     */
    static <T> void addSequences(
            final T holder,
            final AtomicReferenceFieldUpdater<T, LongForCacheLine[]> updater,
            final My_Cursor cursor,
            final LongForCacheLine... sequencesToAdd)
    {
        long cursorSequence;
        LongForCacheLine[] updatedSequences;
        LongForCacheLine[] currentSequences;

        do
        {
            currentSequences = updater.get(holder);
            updatedSequences = Arrays.copyOf(currentSequences, currentSequences.length + sequencesToAdd.length);
            cursorSequence = cursor.getCursor();

            int index = currentSequences.length;
            for (LongForCacheLine sequence : sequencesToAdd)
            {
                sequence.set(cursorSequence);
                updatedSequences[index++] = sequence;
            }
        }
        while (!updater.compareAndSet(holder, currentSequences, updatedSequences));

        cursorSequence = cursor.getCursor();
        for (LongForCacheLine sequence : sequencesToAdd)
        {
            sequence.set(cursorSequence);
        }
    }
}
