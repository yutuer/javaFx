package behaviorTree.GameEngine.eventManager;

import behaviorTree.GameEngine.eventManager.event.Event;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description 事件处理器容器
 * @Author zhangfan
 * @Date 2020/12/9 11:15
 * @Version 1.0
 */
public class EventHandlerContainer
{
    private Map<Class, Set<IEventHandler>> eventHandlerMap = new HashMap<>();

    public Set<IEventHandler> getEventHandlers(Class c)
    {
        return eventHandlerMap.get(c);
    }

    public <T extends Event> void registerEventHandler(IEventHandler<T> eventHandler)
    {
        Class<T> c = getParamClass(eventHandler);

        Set<IEventHandler> eventHandlers = eventHandlerMap.get(c);
        if (eventHandlers == null)
        {
            eventHandlers = new HashSet<>();
            eventHandlerMap.put(c, eventHandlers);
        }
        eventHandlers.add(eventHandler);
    }

    /**
     * 获取方法参数中的泛型的class
     *
     * @param eventHandler
     * @param <T>
     * @return
     */
    private <T extends Event> Class<T> getParamClass(IEventHandler<T> eventHandler)
    {
        Type[] genericInterfaces = eventHandler.getClass().getGenericInterfaces();
        ParameterizedType pt = (ParameterizedType) genericInterfaces[0];
        return (Class<T>) pt.getActualTypeArguments()[0];
    }

    public <T extends Event> void unRegisterEventHandler(IEventHandler<T> eventHandler)
    {
        Class<T> c = getParamClass(eventHandler);

        Set<IEventHandler> eventHandlers = eventHandlerMap.get(c);
        if (eventHandlers != null)
        {
            eventHandlers.remove(eventHandler);
        }
    }

}
