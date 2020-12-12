package behaviorTree.GameEngine.system;

import behaviorTree.GameEngine.Engine;
import behaviorTree.GameEngine.eventManager.event.HealthHpZeroEvent;
import behaviorTree.GameEngine.familyManager.node.HealthNode;
import behaviorTree.entity.component.HealthComponent;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/12/10 11:58
 * @Version 1.0
 */
public class HealthSystem extends IterativeSystem<HealthNode>
{
    private int time;

    private final int Interval = 300;

    public HealthSystem(Engine engine)
    {
        super(engine);
    }

    @Override
    protected void updateNode(HealthNode node, int interval)
    {
        HealthComponent healthComponent = node.healthComponent;
        double health = healthComponent.getHealth();

        healthComponent.subHealth(1.0 * interval / Interval);

        // 派发事件
        if (health > 0 && healthComponent.getHealth() <= 0)
        {
            engine.publishEvent(node.getEntity(), new HealthHpZeroEvent());
        }
    }

    @Override
    public void update(int interval)
    {
        time += interval;
        if (time >= Interval)
        {
            time -= Interval;

            super.update(interval);
        }
    }
}
