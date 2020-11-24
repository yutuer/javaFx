package behaviorTree.GameEngine;

import behaviorTree.core.BTContext;
import behaviorTree.entity.BehaviourEntity;
import behaviorTree.entity.IDGen;
import behaviorTree.entity.component.AiComponent;
import behaviorTree.entity.component.HealthComponent;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 实体管理
 * @Author zhangfan
 * @Date 2020/11/24 17:31
 * @Version 1.0
 */
public class EntityManager
{

    private Engine engine;

    private Map<Integer, BehaviourEntity> entityMap = new HashMap<>();

    private EntityHandler addEntityHandler;
    private EntityHandler removeEntityHandler;

    public EntityManager(Engine engine)
    {
        this.engine = engine;
    }

    public BehaviourEntity newEntity()
    {
        BehaviourEntity entity = new BehaviourEntity(IDGen.getCurId());

        addEntity(entity);

        return entity;
    }

    public void addEntity(BehaviourEntity entity)
    {
        if (entity == null)
        {
            return;
        }

        if (entityMap.put(entity.getId(), entity) == null)
        {
            if (addEntityHandler != null)
            {
                addEntityHandler.handler(this, entity);
            }
        }
    }

    public void removeEntity(int id)
    {
        BehaviourEntity entity;
        if ((entity = entityMap.remove(id)) != null)
        {
            if (removeEntityHandler != null)
            {
                removeEntityHandler.handler(this, entity);
            }
        }
    }

    public void tick(int interval)
    {
        entityMap.forEach((k, v) ->
        {
            HealthComponent healthComponent = v.getComponent(HealthComponent.class);
            if (healthComponent != null)
            {
                healthComponent.subHealth(10);
            }

            AiComponent aiComponent = v.getComponent(AiComponent.class);
            if (aiComponent != null)
            {
                BTContext btContext = new BTContext(engine, v);
                aiComponent.tree.tick(btContext, interval);
            }
        });
    }
}
