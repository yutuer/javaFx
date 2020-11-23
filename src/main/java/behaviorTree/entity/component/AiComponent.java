package behaviorTree.entity.component;

import behaviorTree.BehaviorTree;
import behaviorTree.entity.BehaviourEntity;
import behaviorTree.entity.BotBehaviourTreeBuilder;
import behaviorTree.ifs.IBehaviourNode;

/**
 * @Description ai组件, 使用行为树来实现
 * @Author zhangfan
 * @Date 2020/11/23 11:03
 * @Version 1.0
 */
public class AiComponent<T extends BehaviourEntity> extends CommonComponent
{
    private BehaviorTree<T> tree;

    public AiComponent(T entity)
    {
        super(entity);

        IBehaviourNode<T> root = BotBehaviourTreeBuilder.createBotBehaviour();
        tree = new BehaviorTree<>(root);
    }



}
