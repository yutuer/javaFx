package behaviorTree.Nodes.condition;

import behaviorTree.core.NodeStatusEnum;
import behaviorTree.impl.ConditionNode;
import org.apache.commons.lang.math.RandomUtils;

public class IsInFury extends ConditionNode
{

    @Override
    public NodeStatusEnum isOk()
    {
        boolean nextBoolean = RandomUtils.nextBoolean();
        if (nextBoolean)
        {
            System.out.println("怪物发狂");
        }
        else
        {
            System.out.println("怪物没有发狂");
        }
        return nextBoolean ? NodeStatusEnum.Success : NodeStatusEnum.Failure;
    }

}
