package behaviorTree.GameEngine;

import behaviorTree.entity.BehaviourEntity;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/11/23 16:51
 * @Version 1.0
 */
public interface EntityHandler
{

    void handler(EntityManager game, BehaviourEntity entity);

}
