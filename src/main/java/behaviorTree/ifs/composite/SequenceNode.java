package behaviorTree.ifs.composite;

import behaviorTree.core.BTContext;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.IBehaviourNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 顺序节点
 * @Author zhangfan
 * @Date 2020/11/23 14:14
 * @Version 1.0
 */
public class SequenceNode<T> extends CompositeNode<T>
{
    /**
     * 当前运行到的索引
     */
    private int curIndex;

    public SequenceNode(String tip)
    {
        this(tip, new ArrayList<>());
    }

    public SequenceNode(String tip, List<IBehaviourNode<T>> childs)
    {
        super(tip, childs);
    }

    @Override
    protected NodeStatusEnum update(BTContext<T> context, int interval)
    {
        int size = childs.size();

        for (; curIndex < size; curIndex++)
        {
            IBehaviourNode<T> child = childs.get(curIndex);
            NodeStatusEnum childStatus = child.tick(context, interval);
            if (childStatus != NodeStatusEnum.Successed)
            {
                return childStatus;
            }
        }
        return NodeStatusEnum.Successed;
    }

    @Override
    protected void doReset(NodeStatusEnum status)
    {
        curIndex = 0;
        super.doReset(status);
    }
}
