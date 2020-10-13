package behaviorTree.ifs.composite;

import behaviorTree.context.IContext;
import behaviorTree.ifs.IBehaviourNode;

/**
 * 组合类型节点
 *
 * @param <T>
 */
public interface ICompositeNode<T extends IContext> extends IBehaviourNode<T>
{
    /**
     * 根据索引获得节点
     *
     * @param index
     * @return
     */
    IBehaviourNode<T> get(int index);

    /**
     * child总大小
     *
     * @return
     */
    int size();

    /**
     * 增减Node
     */
    void addChild(IBehaviourNode<T> node);

    void removeChild(IBehaviourNode<T> node);

    @Override
    default boolean isLeaf()
    {
        return false;
    }
}
