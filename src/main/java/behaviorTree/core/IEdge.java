package behaviorTree.core;

import behaviorTree.ifs.IBehaviourNode;

/**
 * @Description 连接线
 * @Author zhangfan
 * @Date 2020/8/28 10:29
 * @Version 1.0
 */
public interface IEdge extends IActive
{
    /**
     * 连接线起点
     *
     * @return
     */
    IBehaviourNode getStart();

    /**
     * 连接线终点
     *
     * @return
     */
    IBehaviourNode getEnd();


}
