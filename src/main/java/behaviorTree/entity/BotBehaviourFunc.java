package behaviorTree.entity;

import behaviorTree.context.BTContext;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.entity.component.PositionComponent;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/11/24 10:38
 * @Version 1.0
 */
public class BotBehaviourFunc
{

    public static NodeStatusEnum SetItemAsTarget(BTContext<Bot> botContext, int interval, ItemType itemType)
    {
        Bot bot = botContext.getContext();
        PositionComponent positionComponent = bot.getComponent(PositionComponent.class);



        return NodeStatusEnum.Failed;
    }
}
