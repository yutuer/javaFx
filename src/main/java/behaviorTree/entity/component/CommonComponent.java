package behaviorTree.entity.component;

import behaviorTree.IComponent;
import behaviorTree.entity.BehaviourEntity;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/11/23 10:56
 * @Version 1.0
 */
public class CommonComponent implements IComponent
{

    private BehaviourEntity entity;

    public CommonComponent(BehaviourEntity entity)
    {
        this.entity = entity;
    }

}
