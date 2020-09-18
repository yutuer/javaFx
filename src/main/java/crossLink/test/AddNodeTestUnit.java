package crossLink.test;

import crossLink.IAoi;
import crossLink.aoi.BaseNode;

import java.util.Random;

/**
 * @Description 测试单元
 * @Author zhangfan
 * @Date 2020/9/18 11:10
 * @Version 1.0
 */
public class AddNodeTestUnit implements ITestAoi
{
    private BaseNode[] baseNodes;

    private Random random = new Random();

    public AddNodeTestUnit(int num, INodeFactory nodeFactory, int xRange, int yRange)
    {
        baseNodes = new BaseNode[num];
        for (int i = 0; i < num; i++)
        {
            baseNodes[i] = nodeFactory.create((int) (random.nextDouble() * xRange), (int) (random.nextDouble() * yRange));
        }
    }

    public void accpetInput(IAoi iaoi)
    {
        for (int i = 0, size = baseNodes.length; i < size; i++)
        {
            iaoi.addNode(baseNodes[i]);
        }
    }

}
