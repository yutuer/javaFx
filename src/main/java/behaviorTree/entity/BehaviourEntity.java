package behaviorTree.entity;

import behaviorTree.IComponent;

/**
 * @Description 实体对象, 使用组件模式
 * 组件模式的精髓就是只有一个GameObject对象, 其他的功能都是通过组件加入的. 并通过组件来组合出不同的GameObject子类效果
 * @Author zhangfan
 * @Date 2020/11/22 16:57
 * @Version 1.0
 */
public class BehaviourEntity
{

    private int id;

    private ComponentManager componentManager = new ComponentManager(new ComponentManagerHandler());

    public BehaviourEntity(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public BehaviourEntity addComponent(IComponent component)
    {
        componentManager.addComponent(component);
        return this;
    }

    public <T extends IComponent> boolean HasComponent(Class<T> c)
    {
        return componentManager.HasComponent(c);
    }

    public <T> T getComponent(Class<T> c)
    {
        return (T) componentManager.getComponent(c);
    }

    public <T extends IComponent> BehaviourEntity removeComponent(Class<T> c)
    {
        componentManager.removeComponent(c);
        return this;
    }

    private static class ComponentManagerHandler implements IComponentManagerHandler
    {

        @Override
        public void onComponentAdded(IComponent component)
        {

        }

        @Override
        public void onComponentRemove(IComponent component)
        {

        }
    }
}
