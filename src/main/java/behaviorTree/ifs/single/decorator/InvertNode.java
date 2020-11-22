package behaviorTree.ifs.single.decorator;

import behaviorTree.context.IContext;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.IBehaviourNode;

/**
 * @Description 翻转器
 * @Author zhangfan
 * @Date 2020/11/22 19:45
 * @Version 1.0
 */
public class InvertNode<T> extends DecoratorNode<T>
{

    public InvertNode(String tip, IBehaviourNode<T> child)
    {
        super(tip, child);
    }

    @Override
    protected NodeStatusEnum update(IContext<T> context, int interval)
    {
        NodeStatusEnum childStatus = child.tick(context, interval);
        if (childStatus == NodeStatusEnum.Failed)
        {
            return NodeStatusEnum.Successed;
        }

        if (childStatus == NodeStatusEnum.Successed)
        {
            return NodeStatusEnum.Failed;
        }
        // 肯能是 ready 和 Running
        return childStatus;
    }
}
