package behaviorTree.ifs.single;

import behaviorTree.context.IContext;
import behaviorTree.ifs.IBehaviourNode;
import behaviorTree.treeEvent.ITreeEvent;

/**
 * @Description 叶子节点
 * @Author zhangfan
 * @Date 2020/8/28 14:24
 * @Version 1.0
 */
public interface ILeafNode<T extends IContext> extends IBehaviourNode<T>
{

    @Override
    default boolean isLeaf()
    {
        return true;
    }

    /**
     * 叶子节点能接受事件
     *
     * @param treeEvent
     */
    void accept(ITreeEvent treeEvent);
}
