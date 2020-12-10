package behaviorTree.GameEngine.eventManager;

import behaviorTree.GameEngine.eventManager.event.TestEvent;
import behaviorTree.entity.BehaviourEntity;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/12/9 11:18
 * @Version 1.0
 */
public class AEventHandler implements IEventHandler<TestEvent>
{
    @Override
    public void onEvent(BehaviourEntity sender, TestEvent event)
    {
        System.out.println(event.getName());
    }
}
