package behaviorTree.nodes.behavior;

import behaviorTree.core.NodeStatusEnum;
import behaviorTree.impl.ActionNode;

public class FuryAttack extends ActionNode
{

    @Override
    public NodeStatusEnum tick0(int interval)
    {
        System.out.println("疯狂攻击玩家, 瞬时完成");
        return NodeStatusEnum.Success;
    }

}
