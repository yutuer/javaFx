package behaviorTree.ifs.single.condition;

import behaviorTree.context.IContext;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.BaseBehaviourNode;
import behaviorTree.ifs.single.ICondition;

public class ConditionNode<T> extends BaseBehaviourNode<T>
{

    private ICondition condition;

    public ConditionNode(String tip, ICondition condition)
    {
        super(tip == null ? "Condition" : tip);
        this.condition = condition;
    }

    @Override
    protected NodeStatusEnum update(IContext<T> context, int interval)
    {
        return condition.testCondition(context, interval) ? NodeStatusEnum.Successed : NodeStatusEnum.Failed;
    }
}
