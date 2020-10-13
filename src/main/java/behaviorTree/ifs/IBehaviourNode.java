package behaviorTree.ifs;

import behaviorTree.BehaviorTree;
import behaviorTree.context.IContext;
import behaviorTree.core.*;

/**
 * 行为树节点抽象
 * 为了复用逻辑, 不和行为树绑定. 而是和上下文绑定,让其能操作一类对象
 * BehaviourTree <-> BehaviourNode <-> IContext
 * 上下文应该之和节点之间有关系, 和行为树之间要通过behaviourNode来连接
 */
public interface IBehaviourNode<T extends IContext> extends IActive
{
    /**
     * 节点当前状态
     *
     * @return
     */
    NodeStatusEnum getStatus();

    /**
     * 执行节点逻辑
     *
     * @return
     */
    NodeStatusEnum tick(int interval);

    /**
     * 获取上下文对象
     *
     * @return
     */
    T getContext();

    /**
     * 获取节点所在的行为树
     *
     * @return
     */
    BehaviorTree<T> getBehaviourTree();

    /**
     * 是否是叶子节点
     *
     * @return
     */
    boolean isLeaf();
}
