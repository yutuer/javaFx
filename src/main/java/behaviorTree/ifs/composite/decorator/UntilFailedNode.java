package behaviorTree.ifs.composite.decorator;

import behaviorTree.core.BTContext;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.IBehaviourNode;

/**
 * @Description 一直running, 直到执行失败
 * @Author zhangfan
 * @Date 2020/11/22 20:44
 * @Version 1.0
 */
public class UntilFailedNode<T> extends DecoratorNode<T>
{
    public UntilFailedNode(String tip, IBehaviourNode<T> child)
    {
        super(tip, child);
    }

    @Override
    protected NodeStatusEnum update(BTContext<T> context, int interval)
    {
        NodeStatusEnum childStatus = child.tick(context, interval);
        return childStatus != NodeStatusEnum.Failed ? NodeStatusEnum.Running : NodeStatusEnum.Successed;
    }
}
