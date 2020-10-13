package behaviorTree.nodes.behavior;

import behaviorTree.core.NodeStatusEnum;
import behaviorTree.impl.ActionNode;

public class NormalAttack extends ActionNode
{

    @Override
    public NodeStatusEnum tick(int interval)
    {
        System.out.println("普通攻击玩家");
        return NodeStatusEnum.Success;
    }

}
