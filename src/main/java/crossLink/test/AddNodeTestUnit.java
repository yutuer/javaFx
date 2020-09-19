package crossLink.test;

import crossLink.IAoi;

/**
 * @Description 测试单元
 * @Author zhangfan
 * @Date 2020/9/18 11:10
 * @Version 1.0
 */
public class AddNodeTestUnit implements ITestAoi
{
    private int[] baseNodes;

    public AddNodeTestUnit(int[] baseNodes)
    {
        this.baseNodes = baseNodes;
    }

    public void accpetInput(IAoi iaoi)
    {
        for (int i = 0, size = baseNodes.length; i < size; i += 2)
        {
            iaoi.addNode(baseNodes[i], baseNodes[i + 1]);
        }
    }

}
