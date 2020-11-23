package behaviorTree.context;

import behaviorTree.entity.BehaviourEntity;

/**
 * @Description 上下文对象
 * @Author zhangfan
 * @Date 2020/8/28 15:53
 * @Version 1.0
 */
public class BTContext
{
    private BehaviourEntity entity;

    public BTContext(BehaviourEntity entity)
    {
        this.entity = entity;
    }

    public BehaviourEntity getEntity()
    {
        return entity;
    }
}
