package behaviorTree.impl;

import behaviorTree.BehaviorTree;
import behaviorTree.context.IContext;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.IBehaviourNode;

public abstract class BaseNode<T extends IContext> implements IBehaviourNode<T>
{
    /**
     * 节点当前状态
     */
    protected NodeStatusEnum nodeStatusEnum;

    /**
     * 父节点
     */
    private IBehaviourNode<T> parentNode;

    @Override
    public IBehaviourNode<T> getParentNode()
    {
        return parentNode;
    }

    @Override
    public NodeStatusEnum getStatus()
    {
        return null;
    }

    @Override
    public T getContext()
    {
        return null;
    }

    @Override
    public BehaviorTree<T> getBehaviourTree()
    {
        return null;
    }

    @Override
    public void onEnter()
    {

    }

    @Override
    public void onLeave()
    {

    }
}
