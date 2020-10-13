package behaviorTree.impl;

import behaviorTree.context.IContext;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.single.IConditionNode;

public abstract class ConditionNode<T extends IContext> extends BaseNode<T> implements IConditionNode<T>
{

    public NodeStatusEnum tick(int interval)
    {
        return isOk(interval);
    }

}
