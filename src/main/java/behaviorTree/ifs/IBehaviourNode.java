package behaviorTree.ifs;

import behaviorTree.BehaviorTree;
import behaviorTree.context.IContext;
import behaviorTree.core.*;

/**
 * 行为树节点抽象
 * <p>
 * 为了复用逻辑, 不和行为树绑定. 而是和上下文绑定,让其能操作一类对象
 * <p>
 * BehaviourTree <-> BehaviourNode <-> IContext
 * <p>
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
    NodeStatusEnum doLogic();

    /**
     * 返回自己节点状态(不需要知道父子关系, 由树来控制)
     *
     * @param nodeEnum
     */
    void returnStatus(NodeStatusEnum nodeEnum);

    /**
     * 合并其他节点的返回结果状态
     *
     * @param statusEnum
     * @return
     */
    void mergeNodeStatuEnum(NodeStatusEnum statusEnum);

    /**
     * 获取上下文对象
     *
     * @return
     */
    T getContext();

    /**
     * 行为树
     *
     * @return
     */
    BehaviorTree<T> getBehaviourTree();

    /**
     * 设置进入的linkline
     *
     * @param linkLine
     */
    void setEnterLinkLine(ILinkLine linkLine);

    /**
     * 是否是叶子节点
     *
     * @return
     */
    boolean isLeaf();
}
