package behaviorTree.Nodes.decorate;

import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.IBehaviourNode;
import behaviorTree.impl.DecoratorNode;

public class InvertNode extends DecoratorNode
{
    @Override
    protected NodeStatusEnum decoratorNode(IBehaviourNode node)
    {
        NodeStatusEnum resultEnum = node.doLogic();
        System.out.println("将结果反转后返回");
        return resultEnum;
    }
}
