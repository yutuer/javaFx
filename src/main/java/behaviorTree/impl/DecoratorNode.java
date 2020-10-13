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
        return decoratorNode(node, interval);
    }

    protected abstract NodeStatusEnum decoratorNode(IBehaviourNode<T> node, int interval);

    @Override
    public void wrapNode(IBehaviourNode node)
    {
        this.node = node;
    }

}
