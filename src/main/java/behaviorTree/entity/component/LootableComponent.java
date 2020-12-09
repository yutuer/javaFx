package behaviorTree.entity.component;

import behaviorTree.entity.BehaviourEntity;

/**
 * @Description 添加血量组件
 * @Author zhangfan
 * @Date 2020/11/25 10:56
 * @Version 1.0
 */
public class LootableComponent extends BaseComponent
{
    public int total;

    public LootableComponent(BehaviourEntity entity)
    {
        super(entity);
    }

}
