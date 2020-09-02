package behaviorTree.Nodes.behavior;

import behaviorTree.core.NodeStatusEnum;
import behaviorTree.impl.ActionNode;

public class FuryAttack extends ActionNode
{

    @Override
    public NodeStatusEnum doLogic()
    {
        System.out.println("疯狂攻击玩家");
        return NodeStatusEnum.Success;
    }

}
