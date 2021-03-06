package behaviorTree.ifs.composite.decorator;

import behaviorTree.core.BTContext;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.IBehaviourNode;

/**
 * @Description 时间限制修饰器
 * @Author zhangfan
 * @Date 2020/11/22 20:49
 * @Version 1.0
 */
public class TimeLimitNode<T> extends DecoratorNode<T>
{
    private int total;

    private int targetLimit;

    public TimeLimitNode(String tip, IBehaviourNode<T> child, int targetLimit)
    {
        super(tip, child);
        this.targetLimit = targetLimit;
    }

    @Override
    protected NodeStatusEnum update(BTContext<T> context, int interval)
    {
        NodeStatusEnum childStatus = child.tick(context, interval);
        total += interval;
        if (total < targetLimit)
        {
            return NodeStatusEnum.Running;
        }
        return childStatus;
    }

    @Override
    protected void onTerminate(NodeStatusEnum status)
    {
        total = 0;
    }
}
