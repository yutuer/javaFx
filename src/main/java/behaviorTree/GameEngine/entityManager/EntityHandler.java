package behaviorTree.GameEngine.entityManager;

import behaviorTree.entity.BehaviourEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/12/10 11:04
 * @Version 1.0
 */
public class EntityHandler
{

    private List<IEntityHandler> list;

    /**
     * 添加handler
     *
     * @param entityHandler
     */
    public void addHandler(IEntityHandler entityHandler)
    {
        if (list == null)
        {
            newList();
        }

        list.add(entityHandler);
    }

    private void newList()
    {
        list = new ArrayList<>();
    }

    /**
     * 移除handler
     *
     * @param entityHandler
     */
    public void removeHandler(IEntityHandler entityHandler)
    {
        if (list == null)
        {
            return;
        }

        list.remove(entityHandler);
    }

    public void handler(EntityManager game, BehaviourEntity entity)
    {
        if (list == null)
        {
            return;
        }

        for (int i = 0; i < list.size(); i++)
        {
            IEntityHandler iEntityHandler = list.get(i);
            iEntityHandler.handler(game, entity);
        }
    }
}
