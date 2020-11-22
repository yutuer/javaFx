package behaviorTree.ifs.single;

import behaviorTree.context.IContext;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/11/22 19:01
 * @Version 1.0
 */
public interface ICondition<T>
{
    boolean testCondition(IContext<T> context, int interval);
}
