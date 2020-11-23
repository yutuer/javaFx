package behaviorTree.ifs;

import behaviorTree.context.IContext;
import behaviorTree.core.NodeStatusEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 基础的行为节点
 * @Author zhangfan
 * @Date 2020/11/22 17:57
 * @Version 1.0
 */
public abstract class BaseBehaviourNode<T> implements IBehaviourNode<T>
{

    private String tip;

    private NodeStatusEnum status;

    private List<IBehaviourNode<T>> childs = new ArrayList<>();

    public BaseBehaviourNode(String tip)
    {
        this.tip = tip;
    }

    @Override
    public NodeStatusEnum getStatus()
    {
        return status;
    }

    @Override
    public NodeStatusEnum tick(IContext<T> context, int interval)
    {
        if (status == NodeStatusEnum.Ready)
        {
            onInit();
        }

        status = update(context, interval);

        // 执行完update, 如果还是Ready, 则报错
        if (status == NodeStatusEnum.Ready)
        {
            throw new RuntimeException("Ready status should not be returned by Behaviour Update Method");
        }

        // 如果节点update完, 不是Running, 说明一轮走完了. 回调终止方法
        if (status != NodeStatusEnum.Running)
        {
            onTerminate(status);
        }

        return status;
    }

    @Override
    public final void reset()
    {
        if (status == NodeStatusEnum.Ready)
        {
            return;
        }

        doReset(status);

        status = NodeStatusEnum.Ready;
    }

    protected void doReset(NodeStatusEnum status)
    {

    }

    protected void onTerminate(NodeStatusEnum status)
    {

    }

    protected NodeStatusEnum update(IContext<T> context, int interval)
    {
        // 给个默认, 不能返回null
        return NodeStatusEnum.Failed;
    }

    protected void onInit()
    {

    }

    @Override
    public String getTip()
    {
        return tip;
    }

    @Override
    public void setTip(String tip)
    {
        this.tip = tip;
    }

    @Override
    public void addChild(IBehaviourNode<T> node)
    {
        childs.add(node);
    }

    @Override
    public void onEnter()
    {

    }

    @Override
    public void onLeave()
    {

    }
}
