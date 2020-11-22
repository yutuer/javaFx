package behaviorTree.nodes.decorate;

import behaviorTree.core.NodeStatusEnum;
import behaviorTree.impl.DecoratorNode;

public class InvertNode extends DecoratorNode
{
    @Override
    protected NodeStatusEnum decoratorNode(NodeStatusEnum resultEnum)
    {
        System.out.println("将结果反转后返回");
        if (resultEnum == NodeStatusEnum.Successed)
        {
            return NodeStatusEnum.Failed;
        }
        else if (resultEnum == NodeStatusEnum.Failed)
        {
            return NodeStatusEnum.Successed;
        }
        throw new RuntimeException("错误的状态: " + resultEnum);
    }
}
