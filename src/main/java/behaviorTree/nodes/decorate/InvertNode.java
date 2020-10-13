package behaviorTree.nodes.decorate;

import behaviorTree.core.NodeStatusEnum;
import behaviorTree.impl.DecoratorNode;

public class InvertNode extends DecoratorNode
{
    @Override
    protected NodeStatusEnum decoratorNode(NodeStatusEnum resultEnum)
    {
        System.out.println("将结果反转后返回");
        if (resultEnum == NodeStatusEnum.Success)
        {
            return NodeStatusEnum.Failure;
        }
        else if (resultEnum == NodeStatusEnum.Failure)
        {
            return NodeStatusEnum.Success;
        }
        throw new RuntimeException("错误的状态: " + resultEnum);
    }
}
