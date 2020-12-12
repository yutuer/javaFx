package behaviorTree.GameEngine;

import behaviorTree.GameEngine.entityManager.EntityManager;
import behaviorTree.GameEngine.eventManager.EventManager;
import behaviorTree.GameEngine.eventManager.GlobalId;
import behaviorTree.GameEngine.eventManager.event.Event;
import behaviorTree.GameEngine.familyManager.FamilyManager;
import behaviorTree.GameEngine.familyManager.node.HealthNode;
import behaviorTree.IClock;
import behaviorTree.entity.BehaviourEntity;
import simpleThreadProcessPool.service.AbstractService;

import java.util.List;

/**
 * @Description
 * @Author zhangfan
 * @Date 2020/11/23 10:37
 * @Version 1.0
 */
public class Engine extends AbstractService implements IClock
{

    private static final int ID = GlobalId.EngineId;

    private EntityManager entityManager;

    private FamilyManager familyManager;

    private EventManager eventManager;

    public Engine()
    {
        entityManager = new EntityManager(this);
        familyManager = new FamilyManager(entityManager);
        eventManager = new EventManager(this);
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

    public <TNodeType> List<TNodeType> getNodes()
    {
        return familyManager.getNodes();
    }

    public void publishEvent(BehaviourEntity sender, Event event)
    {
        eventManager.publishEvent(sender, event);
    }

    public static void main(String[] args)
    {
        Engine engine = new Engine();
        List<HealthNode> nodes = engine.getNodes();

    }
}
