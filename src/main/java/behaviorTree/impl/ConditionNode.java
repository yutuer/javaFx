package behaviorTree.impl;

import behaviorTree.context.IContext;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.single.IConditionNode;

/**
 * 条件节点
 *
 * @param <T>
 */
public abstract class ConditionNode<T extends IContext> extends LeafNode<T> implements IConditionNode<T>
{

    public NodeStatusEnum tick(int interval)
    {
        return isOk(interval);
    }

}
