package behaviorTree.entity.component;

import behaviorTree.BehaviorTree;
import behaviorTree.entity.Bot;
import behaviorTree.entity.BotBehaviourTreeBuilder;
import behaviorTree.ifs.IBehaviourNode;

/**
 * @Description ai组件, 使用行为树来实现
 * @Author zhangfan
 * @Date 2020/11/23 11:03
 * @Version 1.0
 */
public class BotAiComponent extends BaseComponent
{
    public BehaviorTree<Bot> tree;

    public BotAiComponent(Bot entity)
    {
        super(entity);

        IBehaviourNode<Bot> root = BotBehaviourTreeBuilder.BotBehaviour();
        tree = new BehaviorTree<>(root);
    }

}
