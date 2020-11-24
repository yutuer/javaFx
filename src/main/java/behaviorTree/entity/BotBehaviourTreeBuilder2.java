package behaviorTree.entity;

import behaviorTree.BehaviourTreeBuilder2;
import behaviorTree.ifs.IBehaviourNode;

/**
 * @Description 机器人行为树构造器
 * @Author zhangfan
 * @Date 2020/11/23 11:10
 * @Version 1.0
 */
public class BotBehaviourTreeBuilder2
{

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
