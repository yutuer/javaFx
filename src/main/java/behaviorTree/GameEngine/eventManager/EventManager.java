package behaviorTree.GameEngine.eventManager;

import behaviorTree.GameEngine.Engine;
import behaviorTree.GameEngine.eventManager.event.Event;
import behaviorTree.entity.BehaviourEntity;

import java.util.Set;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/12/9 10:59
 * @Version 1.0
 */
public class EventManager implements IEventManager
{

    /**
     * 引擎
     */
    private Engine engine;

    private EventHandlerContainer eventHandlerContainer = new EventHandlerContainer();

    public EventManager(Engine engine)
    {
        this.engine = engine;
    }

    @Override
    public void publishEvent(BehaviourEntity sender, Event event)
    {
        if (eventHandlerContainer != null)
        {
            // 可以使用 同步or异步
            Class<? extends Event> c = event.getClass();
            Set<IEventHandler> eventHandlers = eventHandlerContainer.getEventHandlers(c);
            if (eventHandlers != null && eventHandlers.size() > 0)
            {
                for (IEventHandler eventHandler : eventHandlers)
                {
                    eventHandler.onEvent(sender, event);
                }
            }
        }
    }

    @Override
    public <T extends Event> void subscribeEventHandler(IEventHandler<T> eventHandler)
    {
        if (eventHandlerContainer != null)
        {
            eventHandlerContainer.registerEventHandler(eventHandler);
        }
    }

    @Override
    public <T extends Event> void unSubscribeEventHandler(IEventHandler<T> eventHandler)
    {
        if (eventHandlerContainer != null)
        {
            eventHandlerContainer.unRegisterEventHandler(eventHandler);
        }
    }
}
