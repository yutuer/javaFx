package behaviorTree.ifs.single.decorator;

import behaviorTree.context.IContext;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.IBehaviourNode;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/11/22 20:43
 * @Version 1.0
 */
public class SucceederNode<T> extends DecoratorNode<T>
{
    public SucceederNode(String tip, IBehaviourNode<T> child)
    {
        super(tip, child);
    }

    @Override
    protected NodeStatusEnum update(IContext<T> context, int interval)
    {
        NodeStatusEnum childStatus = child.tick(context, interval);
        if (childStatus == NodeStatusEnum.Failed || childStatus == NodeStatusEnum.Successed)
        {
            return NodeStatusEnum.Successed;
        }
        return childStatus;
    }
}