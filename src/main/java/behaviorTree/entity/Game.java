package behaviorTree.entity;

import behaviorTree.IClock;
import behaviorTree.context.BTContext;
import behaviorTree.entity.component.BotAiComponent;
import behaviorTree.entity.component.HealthComponent;
import behaviorTree.entity.component.StaminaComponent;
import simpleThreadProcessPool.service.AbstractService;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author zhangfan
 * @Date 2020/11/23 10:37
 * @Version 1.0
 */
public class Game extends AbstractService implements IClock
{

    private Map<Integer, BehaviourEntity> entityMap = new HashMap<>();


    private EntityHandler entityHandler;

    public Bot newBot()
    {
        Bot bot = new Bot(IDGen.getCurId());
        bot.addComponent(new HealthComponent(bot))
                .addComponent(new StaminaComponent(bot))
                .addComponent(new BotAiComponent(bot));
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
            if (entityHandler != null)
            {
                entityHandler.onEntityAdd(this, entity);
            }
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
            if (entityHandler != null)
            {
                entityHandler.onEntityRemove(this, entity);
            }
        }
    }

    @Override
    public void tick(int interval)
    {
        entityMap.forEach((k, v) ->
        {
            HealthComponent healthComponent = v.getComponent(HealthComponent.class);
            healthComponent.subHealth(10);


            BotAiComponent aiComponent = v.getComponent(BotAiComponent.class);
            if (aiComponent != null)
            {
                BTContext btContext = new BTContext(game, v);
                aiComponent.tree.tick(btContext, interval);
            }
        });
    }
}
