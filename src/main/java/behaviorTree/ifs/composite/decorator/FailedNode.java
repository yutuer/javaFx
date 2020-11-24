package behaviorTree.ifs.composite.decorator;

import behaviorTree.core.BTContext;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.IBehaviourNode;

/**
 * @Description 不管子节点返回, 都设置为失败
 * @Author zhangfan
 * @Date 2020/11/22 20:04
 * @Version 1.0
 */
public class FailedNode<T> extends DecoratorNode<T>
{
    public FailedNode(String tip, IBehaviourNode<T> child)
    {
        super(tip, child);
    }

    @Override
    protected NodeStatusEnum update(BTContext<T> context, int interval)
    {
        NodeStatusEnum childStatus = child.tick(context, interval);
        if (childStatus == NodeStatusEnum.Failed || childStatus == NodeStatusEnum.Successed)
        {
            return NodeStatusEnum.Failed;
        }
        return childStatus;
    }
}
