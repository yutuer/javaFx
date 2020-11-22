package behaviorTree.context;

import behaviorTree.entity.Entity;

/**
 * @Description 上下文对象
 * @Author zhangfan
 * @Date 2020/8/28 15:53
 * @Version 1.0
 */
public class BTContext
{
    private Entity entity;

    public BTContext(Entity entity)
    {
        this.entity = entity;
    }

    public Entity getEntity()
    {
        return entity;
    }
}
