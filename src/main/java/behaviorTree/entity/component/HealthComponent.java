package behaviorTree.entity.component;

import behaviorTree.entity.BehaviourEntity;

/**
 * @Description 生命值组件
 * @Author zhangfan
 * @Date 2020/11/23 10:45
 * @Version 1.0
 */
public class HealthComponent extends CommonComponent
{
    private int health;

    public HealthComponent(BehaviourEntity entity)
    {
        super(entity);
    }

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }
}
