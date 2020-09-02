package behaviorTree.impl;

import behaviorTree.context.IContext;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.IBehaviourNode;
import behaviorTree.ifs.composite.IDecoratorNode;

public abstract class DecoratorNode<T extends IContext> extends CompositeNode<T> implements IDecoratorNode<T>
{

    private IBehaviourNode<T> node;

    @Override
    public NodeStatusEnum doLogic()
    {
        // 处理之后返回状态
        return decoratorNode(node);
    }

    protected abstract NodeStatusEnum decoratorNode(IBehaviourNode<T> node);

    @Override
    public void setDecoratorNode(IBehaviourNode node)
    {
        this.node = node;
    }

}
