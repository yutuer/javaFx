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
            // 拷贝字段A一份数据, 然后将里面的数据全部设置为最新值,  再用cas设置给字段A
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

    /**
     * 原子方式移除Sequence
     *
     * @param holder          域所属的对象
     * @param sequenceUpdater 域更新器
     * @param sequence        要移除的Sequence
     * @param <T>
     * @return
     */
    static <T> boolean removeSequence(
            final T holder,
            final AtomicReferenceFieldUpdater<T, LongForCacheLine[]> sequenceUpdater,
            final LongForCacheLine sequence)
    {
        int numToRemove;
        LongForCacheLine[] oldSequences;
        LongForCacheLine[] newSequences;

        do
        {
            oldSequences = sequenceUpdater.get(holder);

            numToRemove = countMatching(oldSequences, sequence);

            if (0 == numToRemove)
            {
                break;
            }

            final int oldSize = oldSequences.length;
            newSequences = new LongForCacheLine[oldSize - numToRemove];

            for (int i = 0, pos = 0; i < oldSize; i++)
            {
                final LongForCacheLine testSequence = oldSequences[i];
                if (sequence != testSequence)
                {
                    newSequences[pos++] = testSequence;
                }
            }
        }
        while (!sequenceUpdater.compareAndSet(holder, oldSequences, newSequences));

        return numToRemove != 0;
    }

    private static <T> int countMatching(T[] values, final T toMatch)
    {
        int numToRemove = 0;
        for (T value : values)
        {
            if (value == toMatch) // Specifically uses identity
            {
                numToRemove++;
            }
        }
        return numToRemove;
    }
}
