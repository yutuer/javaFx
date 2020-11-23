package behaviorTree.entity;

import behaviorTree.entity.component.AiComponent;
import behaviorTree.entity.component.HealthComponent;
import behaviorTree.entity.component.StaminaComponent;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author zhangfan
 * @Date 2020/11/23 10:37
 * @Version 1.0
 */
public class Game
{

    private Map<Integer, BehaviourEntity> entityMap = new HashMap<>();


    private EntityHandler entityHandler;

    public Bot newBot()
    {
        Bot bot = new Bot(IDGen.getCurId());
        bot.addComponent(new HealthComponent(bot))
                .addComponent(new StaminaComponent(bot))
                .addComponent(new AiComponent(bot));
        return bot;
    }

    public void addEntity(BehaviourEntity entity)
    {
        if (entity == null)
        {
            return;
        }

        if (entityMap.put(entity.getId(), entity) == null)
        {
            entityHandler.onEntityAdd(this, entity);
        }
    }

    public void removeEntity(BehaviourEntity entity)
    {
        if (entity == null)
        {
            return;
        }

        if (entityMap.remove(entity.getId()) != null)
        {
            entityHandler.onEntityRemove(this, entity);
        }
    }


}
