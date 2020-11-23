package behaviorTree.entity;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/11/23 16:51
 * @Version 1.0
 */
public interface EntityHandler
{

    void onEntityAdd(Game game, BehaviourEntity entity);

    void onEntityRemove(Game game, BehaviourEntity entity);
}
