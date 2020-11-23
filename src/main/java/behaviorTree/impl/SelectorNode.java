package behaviorTree.impl;

import behaviorTree.context.IContext;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.IBehaviourNode;

public class SelectorNode<T extends IContext> extends CompositeNode<T>
{

    public NodeStatusEnum tick(int interval)
    {
        while (hasNext())
        {
            IBehaviourNode<T> node = next();
            NodeStatusEnum result = node.tick(null, interval);
            if (result == NodeStatusEnum.Running)
            {
                return result;
            }
            else if (result == NodeStatusEnum.Successed)
            {
                return result;
            }
            else
            {
                // 继续随机
            }
        }

        // 全部结束, 返回失败
        return NodeStatusEnum.Failed;
    }

    @Override
    public NodeStatusEnum tick(IContext<T> context, int interval)
    {
        return null;
    }

    @Override
    public void reset()
    {

    }
}
