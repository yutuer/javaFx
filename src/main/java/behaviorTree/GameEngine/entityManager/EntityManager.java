package behaviorTree.GameEngine.entityManager;

import behaviorTree.GameEngine.Engine;
import behaviorTree.core.BTContext;
import behaviorTree.entity.BehaviourEntity;
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
public class EntityManager implements IEntityManager
{

    private int _maxId;

    private Engine engine;

    private Map<Integer, BehaviourEntity> entityMap = new HashMap<>();

    private EntityHandler addEntityHandler = new EntityHandler();

    private EntityHandler removeEntityHandler = new EntityHandler();

    public EntityManager(Engine engine)
    {
        this.engine = engine;
    }

    @Override
    public BehaviourEntity newEntity()
    {
        BehaviourEntity entity = new BehaviourEntity(++_maxId);

        addEntity(entity);

        return entity;
    }

    private void addEntity(BehaviourEntity entity)
    {
        if (entity == null)
        {
            return;
        }

        if (entityMap.put(entity.getId(), entity) == null)
        {
            addEntityHandler.handler(this, entity);
        }
    }

    public void removeEntity(int id)
    {
        BehaviourEntity entity;
        if ((entity = entityMap.remove(id)) != null)
        {
            removeEntityHandler.handler(this, entity);
        }
    }

    @Override
    public BehaviourEntity getEntity(int id)
    {
        return entityMap.get(id);
    }

    public void addAddHandler(IEntityHandler entityHandler)
    {
        this.addEntityHandler.addHandler(entityHandler);
    }

    public void addRemoveHandler(IEntityHandler entityHandler)
    {
        this.removeEntityHandler.addHandler(entityHandler);
    }

    public void removeAddHandler(IEntityHandler entityHandler)
    {
        this.addEntityHandler.removeHandler(entityHandler);
    }

    public void removeRemoveHandler(IEntityHandler entityHandler)
    {
        this.removeEntityHandler.removeHandler(entityHandler);
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
