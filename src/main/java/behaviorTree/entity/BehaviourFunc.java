package behaviorTree.entity;

import behaviorTree.GameEngine.Engine;
import behaviorTree.core.BTContext;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.entity.component.PositionComponent;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/11/24 10:38
 * @Version 1.0
 */
public class BehaviourFunc
{

    /**
     * 设置某物品类型为目标
     * @param entityContext
     * @param interval
     * @param itemType
     * @return
     */
    public static NodeStatusEnum SetItemAsTarget(BTContext<BehaviourEntity> entityContext, int interval, ItemType itemType)
    {
        BehaviourEntity entity = entityContext.getContext();
        PositionComponent positionComponent = entity.getComponent(PositionComponent.class);


        Engine engine = entityContext.getEngine();

        return NodeStatusEnum.Failed;
    }
}
