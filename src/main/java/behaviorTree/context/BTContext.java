package behaviorTree.context;

import behaviorTree.GameEngine.Engine;

/**
 * @Description 上下文对象
 * @Author zhangfan
 * @Date 2020/8/28 15:53
 * @Version 1.0
 */
public class BTContext<T>
{
    private Engine engine;

    private T entity;

    public BTContext(Engine engine, T entity)
    {
        this.engine = engine;
        this.entity = entity;
    }

    public T getContext()
    {
        return entity;
    }

    public Engine getEngine()
    {
        return engine;
    }
}
