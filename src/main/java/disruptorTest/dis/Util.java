package disruptorTest.dis;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/10/20 10:07
 * @Version 1.0
 */
public class Util
{

    /**
     * 找出Sequence中序号最小的Sequence的序号。
     *
     * @param sequences
     * @param minimum
     * @return
     */
    public static long getMinimumSequence(final Seq[] sequences, long minimum)
    {
        for (int i = 0, n = sequences.length; i < n; i++)
        {
            long value = sequences[i].get();
            minimum = Math.min(minimum, value);
        }

        return minimum;
    }
}
