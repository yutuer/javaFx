package behaviorTree.impl;

import behaviorTree.context.IContext;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.single.ILeafNode;

public abstract class ActionNode<T extends IContext> extends BaseNode<T> implements ILeafNode<T>
{

    @Override
    public NodeStatusEnum tick(int interval)
    {
        NodeStatusEnum result = tick0(interval);
        if (result == NodeStatusEnum.Running)
        {
            getBehaviourTree().setRunningNode(this);
        }
        else
        {
            // 结束之后, 继续往下走
            getParentNode().tick(interval);
        }
        return result;
    }

    protected abstract NodeStatusEnum tick0(int interval);


}
