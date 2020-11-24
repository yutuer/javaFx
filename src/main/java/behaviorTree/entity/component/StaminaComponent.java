package behaviorTree.entity.component;

import behaviorTree.entity.BehaviourEntity;

/**
 * @Description 精力条组件
 * @Author zhangfan
 * @Date 2020/11/23 10:57
 * @Version 1.0
 */
public class StaminaComponent extends BaseComponent
{
    private int stamina;

    public StaminaComponent(BehaviourEntity entity)
    {
        super(entity);
    }

    public int getStamina()
    {
        return stamina;
    }

    public void setStamina(int stamina)
    {
        this.stamina = stamina;
    }
}
