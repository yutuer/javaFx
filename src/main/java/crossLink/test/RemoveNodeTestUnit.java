package crossLink.test;

import crossLink.IAoi;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/18 11:39
 * @Version 1.0
 */
public class RemoveNodeTestUnit implements ITestAoi
{

    private long[] labels;

    public RemoveNodeTestUnit(long min, long max)
    {
        labels = new long[(int) (max - min)];

        for (int i = 0; i < labels.length; i++)
        {
            labels[i] = min + i;
        }
    }

    @Override
    public void accpetInput(IAoi iaoi)
    {
        for (int i = 0; i < labels.length; i++)
        {
            iaoi.removeNode(labels[i]);
        }
    }
}
