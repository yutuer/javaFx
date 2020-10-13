package behaviorTree.impl;

import behaviorTree.context.IContext;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.IBehaviourNode;
import behaviorTree.ifs.composite.ISequenceNode;

/**
 * 相当于and操作
 *
 * @author Administrator
 */
public class SequenceNode<T extends IContext> extends CompositeNode<T> implements ISequenceNode<T>
{

    @Override
    public NodeStatusEnum tick(int interval)
    {
        while (hasNext())
        {
            IBehaviourNode<T> next = next();
            NodeStatusEnum result = next.tick(interval);
            if (result == NodeStatusEnum.Running)
            {
                return result;
            }
            else if (result == NodeStatusEnum.Failure)
            {
                return result;
            }
            else
            {
                // 否则进行下一个
            }
        }

        // 全部成功后, 返回成功
        return NodeStatusEnum.Success;
    }

}
