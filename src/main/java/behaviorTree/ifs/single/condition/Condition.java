package behaviorTree.ifs.single.condition;

import behaviorTree.core.BTContext;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.BaseBehaviourNode;
import behaviorTree.ifs.single.ICondition;

public class Condition<T> extends BaseBehaviourNode<T>
{
    private ICondition condition;

    public Condition(String tip, ICondition condition)
    {
        super(tip == null ? "Condition" : tip);
        this.condition = condition;
    }

    @Override
    protected NodeStatusEnum update(BTContext<T> context, int interval)
    {
        return condition.testCondition(context, interval) ? NodeStatusEnum.Successed : NodeStatusEnum.Failed;
    }
}
