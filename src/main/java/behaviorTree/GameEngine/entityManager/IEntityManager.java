package behaviorTree.GameEngine.entityManager;

import behaviorTree.entity.BehaviourEntity;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/12/6 20:24
 * @Version 1.0
 */
public interface IEntityManager
{

    BehaviourEntity newEntity();

    void removeEntity(int id);

    BehaviourEntity getEntity(int id);

}
