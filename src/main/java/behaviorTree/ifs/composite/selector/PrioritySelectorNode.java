package behaviorTree.ifs.composite.selector;

import behaviorTree.core.BTContext;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.IBehaviourNode;

import java.util.List;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/11/23 19:29
 * @Version 1.0
 */
public class PrioritySelectorNode<T> extends SelectorNode<T>
{
    public PrioritySelectorNode()
    {
        this("PrioritySelector");
    }

    public PrioritySelectorNode(String tip)
    {
        super(tip);
    }

    public PrioritySelectorNode(String tip, List<IBehaviourNode<T>> childs)
    {
        super(tip, childs);
    }

    @Override
    protected NodeStatusEnum update(BTContext<T> context, int interval)
    {
        int size = childs.size();
        for (int i = 0; i < size; i++)
        {
            IBehaviourNode<T> child = childs.get(i);
            NodeStatusEnum childStatus = child.tick(context, interval);
            if (childStatus != NodeStatusEnum.Failed)
            {
                for (int j = i + 1; j < size; j++)
                {
                    childs.get(j).reset();
                }
                return childStatus;
            }
        }
        return NodeStatusEnum.Failed;
    }
}
