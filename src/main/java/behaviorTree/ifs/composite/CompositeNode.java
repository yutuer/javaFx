package behaviorTree.ifs.composite;

import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.BaseBehaviourNode;
import behaviorTree.ifs.IBehaviourNode;

import java.util.List;
import java.util.function.Function;

/**
 * @Description 组合节点
 * @Author zhangfan
 * @Date 2020/11/23 12:09
 * @Version 1.0
 */
public class CompositeNode<T> extends BaseBehaviourNode<T>
{
//    protected List<IBehaviourNode<T>> childs;

    /**
     * 终止条件
     */
    private Function<NodeStatusEnum, Boolean> func;

    public CompositeNode(String tip, List<IBehaviourNode<T>> childs)
    {
        super(tip);

        if (childs == null)
        {
            throw new RuntimeException("Children can not be null");
        }

//        if (childs.size() == 0)
//        {
//            throw new RuntimeException("Must have at least one child");
//        }

        if (childs.stream().anyMatch(c -> c == null))
        {
            throw new RuntimeException("Children cannot contain null elements");
        }

        this.childs = childs;
    }

    @Override
    protected void onTerminate(NodeStatusEnum status)
    {
        doReset(status);
    }

    @Override
    protected void doReset(NodeStatusEnum status)
    {
        resetChildren();
    }

    private void resetChildren()
    {
        for (IBehaviourNode<T> child : childs)
        {
            child.reset();
        }
    }
}
