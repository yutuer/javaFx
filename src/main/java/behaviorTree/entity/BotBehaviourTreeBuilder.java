package behaviorTree.entity;

import behaviorTree.BehaviourTreeBuilder;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.entity.component.HealthComponent;
import behaviorTree.entity.component.InventoryComponent;
import behaviorTree.ifs.IBehaviourNode;

/**
 * @Description 机器人行为树构造器
 * @Author zhangfan
 * @Date 2020/11/23 11:10
 * @Version 1.0
 */
public class BotBehaviourTreeBuilder
{

    public static IBehaviourNode<BehaviourEntity> BotBehaviour()
    {
        BehaviourTreeBuilder<BehaviourEntity> botBehaviourTreeBuilder = BehaviourTreeBuilder.create();
        return botBehaviourTreeBuilder
                .PrioritySelector("Root")
//                .Invert("Test Invert")
//                    .Do("Speak Action", (bot, interval) ->
//                    {
//                        System.out.println("speak");
//                        return NodeStatusEnum.Successed;
//                    })
//                .end()
                .pushSubTree(TestBehaviour())
                .build();
    }

    public static IBehaviourNode<BehaviourEntity> TestBehaviour()
    {
        BehaviourTreeBuilder<BehaviourEntity> botBehaviourTreeBuilder = BehaviourTreeBuilder.create();
        return botBehaviourTreeBuilder
                .Sequence("Sequence test")
                    .Condition("Cond1 Test", (entityContext, interval) ->
                    {
                        BehaviourEntity bot = entityContext.getContext();
                        System.out.println(bot.getId() + ": Cond1 cond");
                        return true;
                    })
                    .Condition("Cond2 Test", (entityContext, interval) ->
                    {
                        BehaviourEntity bot = entityContext.getContext();
                        System.out.println(bot.getId() + ": Cond2 cond");
                        return true;
                    })
                    .Condition("Cond3 Test", (entityContext, interval) ->
                    {
                        BehaviourEntity bot = entityContext.getContext();
                        System.out.println(bot.getId() + ": Cond3 cond");
                        return true;
                    })
                    .Do("Do some Action", (entityContext, integer) ->
                    {
                        BehaviourEntity bot = entityContext.getContext();
                        System.out.println(bot.getId() + ": action");
                        return NodeStatusEnum.Successed;
                    })
                .end()
                .build();
    }

    public static IBehaviourNode<BehaviourEntity> LowHealthBehaviour()
    {
        BehaviourTreeBuilder<BehaviourEntity> botBehaviourTreeBuilder = BehaviourTreeBuilder.create();
        return botBehaviourTreeBuilder
                .Sequence("Low health")
                    .Condition("Is health low?", (entityContext, interval) ->
                    {
                        BehaviourEntity bot = entityContext.getContext();
                        HealthComponent component = bot.getComponent(HealthComponent.class);
                        if (component.getHealth() < 50)
                        {
                            return true;
                        }
                        return false;
                    })
                    .Selector("Find and eat food")
                        .Do("Eat food from inventory", ((entityContext, interval) ->
                        {
                            BehaviourEntity bot = entityContext.getContext();
                            InventoryComponent inventoryComponent = bot.getComponent(InventoryComponent.class);
                            if (!inventoryComponent.deleteItemNum(ItemType.Food, 1))
                            {
                                return NodeStatusEnum.Failed;
                            }

                            HealthComponent healthComponent = bot.getComponent(HealthComponent.class);
                            healthComponent.addHealth(30);
                            return NodeStatusEnum.Successed;
                        }))
                        .pushSubTree(FindAndPickupItem(ItemType.Food))
                    .end()
                .end()
                .build();
    }

    public static IBehaviourNode<BehaviourEntity> FindAndPickupItem(ItemType itemType)
    {
        BehaviourTreeBuilder<BehaviourEntity> botBehaviourTreeBuilder = BehaviourTreeBuilder.create();
        return botBehaviourTreeBuilder
                .Sequence("Find and pick up item")
                    .Do(String.format("Set closest %s as target", itemType.name()),
                            (entityContext, interval) -> BehaviourFunc.SetItemAsTarget(entityContext, interval, itemType))
                    .Do("Move to target", (entityContext, interval) ->
                    {
                        return NodeStatusEnum.Failed;
                    })
                    .Do("Pickup target", (entityContext, interval) ->
                    {
                        return NodeStatusEnum.Failed;
                    })
                .end()
                .build();
    }

}
