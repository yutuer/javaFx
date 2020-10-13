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
    public NodeStatusEnum tick(int inteval)
    {
        return null;
    }

    @Override
    public boolean isLeaf()
    {
        return false;
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
