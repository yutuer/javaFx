package behaviorTree.ifs.single;

import behaviorTree.treeEvent.ITreeEvent;

/**
 * 行为节点接口
 *
 * @author Administrator
 */
public interface IActionNode<T>
{
    /**
     * 叶子节点能接受事件
     *
     * @param treeEvent
     */
    void accept(ITreeEvent treeEvent);
}
