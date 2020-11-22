package behaviorTree.ifs.single.decorator;

import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.BaseBehaviourNode;
import behaviorTree.ifs.IBehaviourNode;

/**
 * 修饰节点(改变节点的行为)
 */
public abstract class DecoratorNode<T> extends BaseBehaviourNode<T>
{

    protected IBehaviourNode<T> child;

    public DecoratorNode(String tip, IBehaviourNode<T> child)
    {
        super(tip == null ? "Decorator" : tip);
        this.child = child;
    }

    @Override
    protected void doReset(NodeStatusEnum status)
    {
        child.reset();
    }
}