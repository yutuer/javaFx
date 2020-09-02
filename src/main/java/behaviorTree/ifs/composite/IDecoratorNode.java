package behaviorTree.ifs.composite;

import behaviorTree.context.IContext;
import behaviorTree.ifs.IBehaviourNode;

/**
 * 修饰节点
 */
public interface IDecoratorNode<T extends IContext> extends ICompositeNode<T>
{

    /**
     * 设置修饰的节点
     *
     * @param node
     */
    void setDecoratorNode(IBehaviourNode<T> node);

}
