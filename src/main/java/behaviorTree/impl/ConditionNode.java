package behaviorTree.impl;

import behaviorTree.context.IContext;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.single.IConditionNode;

public abstract class ConditionNode<T extends IContext> extends LeafNode<T> implements IConditionNode<T>
{

    @Override
    public NodeStatusEnum doLogic()
    {
        return isOk();
    }

}
