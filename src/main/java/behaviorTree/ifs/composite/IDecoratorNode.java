package behaviorTree.ifs.composite;

import behaviorTree.context.IContext;
import behaviorTree.ifs.IBehaviourNode;

/**
 * 修饰节点(改变节点的行为)
 */
public interface IDecoratorNode<T extends IContext> extends IBehaviourNode<T>
{

    /**
     * 包装修饰的节点
     *
     * @param node
     */
    void wrapNode(IBehaviourNode<T> node);

}
