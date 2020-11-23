package behaviorTree.ifs.composite.decorator;

import behaviorTree.context.IContext;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.IBehaviourNode;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/11/22 20:47
 * @Version 1.0
 */
public class UntilSuccessNode<T> extends DecoratorNode<T>
{
    public UntilSuccessNode(String tip, IBehaviourNode<T> child)
    {
        super(tip, child);
    }

    @Override
    protected NodeStatusEnum update(IContext<T> context, int interval)
    {
        NodeStatusEnum childStatus = child.tick(context, interval);
        return childStatus != NodeStatusEnum.Successed ? NodeStatusEnum.Running : NodeStatusEnum.Successed;
    }
}
