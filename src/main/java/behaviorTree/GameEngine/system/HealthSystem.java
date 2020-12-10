package behaviorTree.GameEngine.system;

import behaviorTree.GameEngine.Engine;
import behaviorTree.GameEngine.familyManager.FNode;
import behaviorTree.entity.BehaviourEntity;
import behaviorTree.entity.component.HealthComponent;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/12/10 11:58
 * @Version 1.0
 */
public class HealthSystem extends System<HealthComponent>
{
    public HealthSystem(Engine engine)
    {
        super(engine);
    }

    @Override
    public void update(int interval)
    {
        for (int i = 0; i < nodes.size(); i++)
        {
            FNode<HealthComponent> node = nodes.get(i);
            int entityId = node.getEntityId();

            BehaviourEntity entity = engine.getEntity(entityId);
            if (entity != null)
            {
                HealthComponent healthComponent = entity.getComponent(HealthComponent.class);

            }
        }
    }
}
