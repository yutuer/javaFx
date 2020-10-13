package behaviorTree.impl;

import behaviorTree.context.IContext;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.IBehaviourNode;
import behaviorTree.ifs.composite.IDecoratorNode;

public abstract class DecoratorNode<T extends IContext> extends CompositeNode<T> implements IDecoratorNode<T>
{

    private IBehaviourNode<T> node;

    @Override
    public NodeStatusEnum tick(int interval)
    {
        // 处理之后返回状态
        NodeStatusEnum resultEnum = node.tick(interval);
        if (resultEnum != NodeStatusEnum.Running)
        {
            return decoratorNode(resultEnum);
        }
        return resultEnum;
    }

    protected abstract NodeStatusEnum decoratorNode(NodeStatusEnum resultEnum);

    @Override
    public void wrapNode(IBehaviourNode node)
    {
        this.node = node;
    }

    @Override
    public IBehaviourNode<T> next()
    {
        return null;
    }

    @Override
    public boolean hasNext()
    {
        return false;
    }
}
