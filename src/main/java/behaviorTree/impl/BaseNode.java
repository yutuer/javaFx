package behaviorTree.impl;

import behaviorTree.BehaviorTree;
import behaviorTree.context.IContext;
import behaviorTree.core.ILinkLine;
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
    public NodeStatusEnum doLogic()
    {
        return null;
    }

    @Override
    public void returnStatus(NodeStatusEnum nodeEnum)
    {

    }

    @Override
    public void mergeNodeStatuEnum(NodeStatusEnum statusEnum)
    {

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
    public void setEnterLinkLine(ILinkLine linkLine)
    {

    }

    @Override
    public void enter()
    {

    }

    @Override
    public void leave()
    {

    }

}
