package behaviorTree.Nodes.condition;

import behaviorTree.core.NodeStatusEnum;
import behaviorTree.impl.ConditionNode;
import org.apache.commons.lang.math.RandomUtils;

public class RangeFindPlayer extends ConditionNode
{

    @Override
    public NodeStatusEnum isOk()
    {
        boolean nextBoolean = RandomUtils.nextBoolean();
        if (nextBoolean)
        {
            System.out.println("視野內發現玩家");
        }
        else
        {
            System.out.println("視野內没有發現玩家");
        }
        return nextBoolean ? NodeStatusEnum.Success : NodeStatusEnum.Failure;
    }

}
