package behaviorTree.context;

import behaviorTree.entity.Game;

/**
 * @Description 上下文对象
 * @Author zhangfan
 * @Date 2020/8/28 15:53
 * @Version 1.0
 */
public class BTContext<T>
{
    private Game game;

    private T entity;

    public BTContext(Game game, T entity)
    {
        this.game = game;
        this.entity = entity;
    }

    public T getContext()
    {
        return entity;
    }

    public Game getGame()
    {
        return game;
    }
}
