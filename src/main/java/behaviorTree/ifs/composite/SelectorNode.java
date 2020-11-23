package behaviorTree.ifs.composite;

import behaviorTree.context.IContext;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.IBehaviourNode;

import java.util.List;

/**
 * @Description 选择节点
 * @Author zhangfan
 * @Date 2020/11/23 14:27
 * @Version 1.0
 */
public class SelectorNode<T> extends CompositeNode<T>
{
    /**
     * 当前运行到的索引
     */
    private int curIndex;

    public SelectorNode(String tip, List<IBehaviourNode<T>> childs)
    {
        super(tip, childs);
    }

    @Override
    protected NodeStatusEnum update(IContext<T> context, int interval)
    {
        int size = childs.size();
        for (; curIndex < size; curIndex++)
        {
            IBehaviourNode<T> child = childs.get(curIndex);
            NodeStatusEnum childStatus = child.tick(context, interval);
            if (childStatus != NodeStatusEnum.Failed)
            {
                return childStatus;
            }
        }
        return NodeStatusEnum.Failed;
    }

    @Override
    protected void doReset(NodeStatusEnum status)
    {
        curIndex = 0;
        super.doReset(status);
    }
}
