package behaviorTree.ifs.composite;

import behaviorTree.context.IContext;
import behaviorTree.core.IEdge;
import behaviorTree.ifs.IBehaviourNode;

public interface ICompositeNode<T extends IContext> extends IBehaviourNode<T>
{
    /**
     * 根据索引获得
     *
     * @param index
     * @return
     */
    IBehaviourNode<T> get(int index);

    /**
     * 总大小
     *
     * @return
     */
    int size();

    /**
     * 增减出去的edge
     *
     * @param edge
     */
    void addOuterEdge(IEdge edge);

    void removeOuterEdge(IEdge edge);

    @Override
    default boolean isLeaf()
    {
        return false;
    }
}
