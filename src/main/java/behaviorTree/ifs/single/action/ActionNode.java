package behaviorTree.ifs.single.action;

import behaviorTree.context.BTContext;
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

    private BiFunction<BTContext<T>, Integer, NodeStatusEnum> func;

    public ActionNode(String tip, BiFunction<BTContext<T>, Integer, NodeStatusEnum> func)
    {
        super(tip);
        this.func = func;
    }

    @Override
    protected NodeStatusEnum update(BTContext<T> context, int interval)
    {
        return func.apply(context, interval);
    }
}
