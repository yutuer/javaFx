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
    protected NodeStatusEnum nodeStatusEnum = NodeStatusEnum.Running;

    /**
     * 父节点
     */
    private IBehaviourNode<T> parentNode;

    /**
     * 提示信息
     */
    private String tip;

    @Override
    public IBehaviourNode<T> getParentNode()
    {
        return parentNode;
    }

    public void setParentNode(IBehaviourNode<T> parentNode)
    {
        this.parentNode = parentNode;
    }

    @Override
    public NodeStatusEnum getStatus()
    {
        return nodeStatusEnum;
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

    @Override
    public String getTip()
    {
        return tip;
    }

    @Override
    public void setTip(String tip)
    {
        this.tip = tip;
    }
}
