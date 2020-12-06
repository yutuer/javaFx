package behaviorTree.GameEngine;

import behaviorTree.GameEngine.entityManager.EntityManager;
import behaviorTree.IClock;
import behaviorTree.entity.BehaviourEntity;
import simpleThreadProcessPool.service.AbstractService;

/**
 * @Description
 * @Author zhangfan
 * @Date 2020/11/23 10:37
 * @Version 1.0
 */
public class Engine extends AbstractService implements IClock
{
    private EntityManager entityManager;


    public Engine()
    {
        entityManager = new EntityManager(this);
    }

    /**
     * 创建一个新的entity. 里面会加到entity管理器里面
     *
     * @return
     */
    public BehaviourEntity newEntity()
    {
        return entityManager.newEntity();
    }

    /**
     * 从entity管理器里面移除
     *
     * @param entity
     */
    public void removeEntity(BehaviourEntity entity)
    {
        if (entity == null)
        {
            return;
        }

        entityManager.removeEntity(entity.getId());
    }

    public BehaviourEntity getEntity(int id)
    {
        return entityManager.getEntity(id);
    }

    @Override
    public void tick(int interval)
    {
        entityManager.tick(interval);
    }
}
