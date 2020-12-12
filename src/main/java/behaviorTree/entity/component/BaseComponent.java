package behaviorTree.entity.component;

import behaviorTree.IComponent;
import behaviorTree.entity.BehaviourEntity;

/**
 * @Description 组件基类,  应该还有个队列, 能存储其他发送过来的事件
 * @Author zhangfan
 * @Date 2020/11/23 10:56
 * @Version 1.0
 */
public class BaseComponent implements IComponent
{

    private BehaviourEntity entity;

    public BaseComponent(BehaviourEntity entity)
    {
        this.entity = entity;
    }

}
