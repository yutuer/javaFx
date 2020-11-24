package behaviorTree.ifs;

import behaviorTree.core.BTContext;
import behaviorTree.core.IActive;
import behaviorTree.core.NodeStatusEnum;

/**
 * 行为树节点抽象
 * 为了复用逻辑, 不和行为树绑定. 而是和上下文绑定,让其能操作一类对象
 * BehaviourTree <-> BehaviourNode <-> IContext
 * 上下文应该之和节点之间有关系, 和行为树之间要通过behaviourNode来连接
 */
public interface IBehaviourNode<T> extends IActive, IContainer<IBehaviourNode<T>>
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
    NodeStatusEnum tick(BTContext<T> context, int interval);


    void reset();

    /**
     * 获得节点提示说明
     *
     * @return
     */
    String getTip();

    /**
     * 设置节点提示说明
     *
     * @param tip
     */
    void setTip(String tip);
}
