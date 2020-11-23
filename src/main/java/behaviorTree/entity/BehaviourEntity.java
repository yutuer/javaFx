package behaviorTree.entity;

import behaviorTree.IComponent;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 实体对象
 * @Author zhangfan
 * @Date 2020/11/22 16:57
 * @Version 1.0
 */
public class BehaviourEntity
{

    private int id;
    /**
     * 组件模式
     */
    private Map<Class, Object> components = new HashMap<>();

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
        components.put(component.getClass(), component);
        onComponentAdded(component);
        return this;
    }

    protected void onComponentAdded(IComponent component)
    {

    }

    public <T extends IComponent> boolean HasComponent(Class<T> c)
    {
        return components.containsKey(c);
    }

    public <T> T getComponent(Class<T> c)
    {
        return (T) components.get(c);
    }

    public <T extends IComponent> BehaviourEntity removeComponent(Class<T> c)
    {
        components.remove(c);
        return this;
    }


}
