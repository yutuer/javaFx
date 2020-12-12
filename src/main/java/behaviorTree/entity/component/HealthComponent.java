package behaviorTree.entity.component;

import behaviorTree.entity.BehaviourEntity;

/**
 * @Description 生命值组件
 * @Author zhangfan
 * @Date 2020/11/23 10:45
 * @Version 1.0
 */
public class HealthComponent extends BaseComponent
{
    private double health;

    private final double MaxHealth = 100.0;

    public HealthComponent(BehaviourEntity entity)
    {
        super(entity);
    }

    public double getHealth()
    {
        return health;
    }

    /**
     * 扣除血量
     *
     * @param health
     * @return
     */
    public boolean subHealth(double health)
    {
        final double h = this.health;

        this.health -= health;
        if (this.health < 0)
        {
            this.health = 0;
        }

        if (h < health)
        {
            return false;
        }
        return true;
    }

    /**
     * 添加血量
     *
     * @param health
     */
    public boolean addHealth(double health)
    {
        if (this.health >= MaxHealth)
        {
            return false;
        }

        this.health += health;
        if (this.health > MaxHealth)
        {
            this.health = MaxHealth;
        }

        return true;
    }
}
