package behaviorTree.context;

/**
 * @Description 上下文对象
 * @Author zhangfan
 * @Date 2020/11/22 18:01
 * @Version 1.0
 */
public interface IContext<T>
{
    T getContext();
}
