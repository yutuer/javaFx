package behaviorTree.GameEngine.eventManager;

import behaviorTree.GameEngine.eventManager.event.Event;
import behaviorTree.entity.BehaviourEntity;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/12/9 10:59
 * @Version 1.0
 */
public interface IEventManager
{

    void publishEvent(BehaviourEntity sender, Event event);

    /**
     * 注册事件处理器
     */
    <T extends Event> void subscribeEventHandler(IEventHandler<T> eventHandler);

    /**
     * 取消注册事件处理器
     */
    <T extends Event> void unSubscribeEventHandler(IEventHandler<T> eventHandler);

}
