package behaviorTree.entity;

import behaviorTree.BehaviourTreeBuilder;
import behaviorTree.BehaviourTreeBuilder2;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.IBehaviourNode;

/**
 * @Description 机器人行为树构造器
 * @Author zhangfan
 * @Date 2020/11/23 11:10
 * @Version 1.0
 */
public class BotBehaviourTreeBuilder
{

    public static <T> IBehaviourNode<T> createBotBehaviour()
    {
        BehaviourTreeBuilder<T> botBehaviourTreeBuilder = BehaviourTreeBuilder.create();
        return botBehaviourTreeBuilder
                .Invert("Test Invert")
                    .Do("Speak Action", (bot, interval) ->
                    {
                        System.out.println("speak");
                        return NodeStatusEnum.Successed;
                    })
                .end()
                .Sequence("Sequence test")
                    .Condition("Cond1 Test", (bot, interval) ->
                    {
                        System.out.println("Cond1 cond");
                        return true;
                    })
                    .Condition("Cond2 Test", (bot, interval) ->
                    {
                        System.out.println("Cond2 cond");
                        return false;
                    })
                    .Condition("Cond3 Test", (bot, interval) ->
                    {
                        System.out.println("Cond3 cond");
                        return true;
                    })
                .end()
                .build();
    }

    public static IBehaviourNode<Bot> createBotBehaviour1()
    {
        BehaviourTreeBuilder2<Bot> botBehaviourTreeBuilder = BehaviourTreeBuilder2.create();
        return botBehaviourTreeBuilder
//                .pushComposite(BehaviourNodeBuilder.invertNodeBuilder())
//                .pushLeafNode()
//                .pushLeafNode()
//                .end()
                .build();
    }

}
