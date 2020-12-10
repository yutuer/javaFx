package behaviorTree.GameEngine.eventManager;

import behaviorTree.GameEngine.eventManager.event.Event;
import behaviorTree.entity.BehaviourEntity;

/**
 * @Description 事件处理器
 * @Author zhangfan
 * @Date 2020/12/9 11:11
 * @Version 1.0
 */
public interface IEventHandler<T extends Event>
{

    void onEvent(BehaviourEntity sender, T event);
}
