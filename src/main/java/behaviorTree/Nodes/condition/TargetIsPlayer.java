package behaviorTree.Nodes.condition;

import behaviorTree.core.NodeStatusEnum;
import behaviorTree.impl.ConditionNode;
import org.apache.commons.lang.math.RandomUtils;

public class TargetIsPlayer extends ConditionNode
{

    @Override
    public NodeStatusEnum isOk()
    {
        boolean nextBoolean = RandomUtils.nextBoolean();
        if (nextBoolean)
        {
            System.out.println("目标是玩家");
        }
        else
        {
            System.out.println("目标不是玩家");
        }
        return nextBoolean ? NodeStatusEnum.Success : NodeStatusEnum.Failure;
    }

}
