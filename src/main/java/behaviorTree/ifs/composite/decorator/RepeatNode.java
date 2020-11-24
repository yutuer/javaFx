package behaviorTree.ifs.composite.decorator;

import behaviorTree.context.BTContext;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.IBehaviourNode;

/**
 * @Description 重复执行
 * @Author zhangfan
 * @Date 2020/11/22 20:06
 * @Version 1.0
 */
public class RepeatNode<T> extends DecoratorNode<T>
{
    private int repeatCount;

    private int curCount;

    public RepeatNode(String tip, IBehaviourNode<T> child, int repeatCount)
    {
        super(tip, child);
        if (repeatCount < 1)
        {
            throw new RuntimeException("repeatCount:" + repeatCount + " must be at least one");
        }
        this.repeatCount = repeatCount;
    }

    @Override
    protected NodeStatusEnum update(BTContext<T> context, int interval)
    {
        NodeStatusEnum childStatus = child.tick(context, interval);
        if (childStatus == NodeStatusEnum.Successed)
        {
            curCount++;
            if (curCount < repeatCount)
            {
                return NodeStatusEnum.Running;
            }
        }

        // 失败或者running, 直接返回
        return childStatus;
    }

    @Override
    protected void onTerminate(NodeStatusEnum status)
    {
        curCount = 0;
    }
}
