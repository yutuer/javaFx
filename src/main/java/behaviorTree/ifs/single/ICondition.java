package behaviorTree.ifs.single;

import behaviorTree.context.BTContext;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/11/22 19:01
 * @Version 1.0
 */
public interface ICondition<T>
{
    boolean testCondition(BTContext<T> context, int interval);
}
