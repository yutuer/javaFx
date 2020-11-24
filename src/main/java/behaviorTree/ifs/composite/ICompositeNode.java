package behaviorTree.ifs.composite;

import behaviorTree.ifs.IBehaviourNode;

/**
 * 组合类型节点
 *
 * @param <T>
 */
public interface ICompositeNode<T> extends IBehaviourNode<T>
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

    /**
     * 下一个要执行的节点
     *
     * @return
     */
    IBehaviourNode<T> next();

    /**
     * 是否还有下一个
     *
     * @return
     */
    boolean hasNext();

}
