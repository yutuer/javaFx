package behaviorTree.entity;

import behaviorTree.IComponent;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/11/24 16:15
 * @Version 1.0
 */
public interface IComponentManagerHandler
{
    void onComponentAdded(IComponent component);

    void onComponentRemove(IComponent component);
}
