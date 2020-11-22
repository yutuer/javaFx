package behaviorTree.nodeBuilder;

import behaviorTree.ifs.IBehaviourNode;

/**
 * @Description 节点工厂
 * @Author zhangfan
 * @Date 2020/11/22 19:50
 * @Version 1.0
 */
public interface BehaviourNodeBuilder<T>
{
    IBehaviourNode<T> build();

}
