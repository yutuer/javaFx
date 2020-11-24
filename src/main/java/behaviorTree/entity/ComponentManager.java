package behaviorTree.entity;

import behaviorTree.IComponent;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 组件管理器, 可以用做组件管理的代理
 * @Author zhangfan
 * @Date 2020/11/24 11:08
 * @Version 1.0
 */
public class ComponentManager
{
    private Map<Class, IComponent> components = new HashMap<>();

    private IComponentManagerHandler handler;

    public ComponentManager(IComponentManagerHandler handler)
    {
        this.handler = handler;
    }

    public void addComponent(IComponent component)
    {
        components.put(component.getClass(), component);
        onComponentAdded(component);
    }

    private void onComponentAdded(IComponent component)
    {
        if (handler != null)
        {
            handler.onComponentAdded(component);
        }
    }

    public <T> T getComponent(Class<T> c)
    {
        return (T) components.get(c);
    }

    public <T extends IComponent> boolean HasComponent(Class<T> c)
    {
        return components.containsKey(c);
    }

    public <T extends IComponent> void removeComponent(Class<T> c)
    {
        IComponent component = components.remove(c);
        onComponentRemove(component);
    }

    private void onComponentRemove(IComponent component)
    {
        if (handler != null)
        {
            handler.onComponentRemove(component);
        }
    }

}
