package behaviorTree.nodes.decorate;

import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.IBehaviourNode;
import behaviorTree.impl.DecoratorNode;

public class InvertNode extends DecoratorNode
{
    @Override
    protected NodeStatusEnum decoratorNode(IBehaviourNode node, int interval)
    {
        NodeStatusEnum resultEnum = node.tick(interval);
        System.out.println("将结果反转后返回");
        return resultEnum;
    }
}
