package behaviorTree.ifs.single.action;

import behaviorTree.context.IContext;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.BaseBehaviourNode;

import java.util.function.BiFunction;

/**
 * @Description 基本action节点
 * @Author zhangfan
 * @Date 2020/11/23 15:01
 * @Version 1.0
 */
public class ActionNode<T> extends BaseBehaviourNode<T>
{

    private BiFunction<IContext<T>, Integer, NodeStatusEnum> func;

    public ActionNode(String tip, BiFunction<IContext<T>, Integer, NodeStatusEnum> func)
    {
        super(tip);
        this.func = func;
    }

    @Override
    protected NodeStatusEnum update(IContext<T> context, int interval)
    {
        return func.apply(context, interval);
    }
}
