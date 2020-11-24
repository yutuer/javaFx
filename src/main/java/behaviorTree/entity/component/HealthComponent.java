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
    private int health;

    private final int MaxHealth = 100;

    public HealthComponent(BehaviourEntity entity)
    {
        super(entity);
    }

    public int getHealth()
    {
        return health;
    }

    /**
     * 扣除血量
     *
     * @param health
     * @return
     */
    public boolean subHealth(int health)
    {
        final int h = this.health;

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
    public boolean addHealth(int health)
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
