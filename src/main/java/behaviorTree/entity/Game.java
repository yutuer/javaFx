package behaviorTree.entity;

import behaviorTree.GameEngine.Engine;
import behaviorTree.entity.component.AiComponent;
import behaviorTree.entity.component.HealthComponent;
import behaviorTree.entity.component.InventoryComponent;
import behaviorTree.entity.component.StaminaComponent;

/**
 * @Description 游戏
 * @Author zhangfan
 * @Date 2020/11/24 10:59
 * @Version 1.0
 */
public class Game
{

    private Engine engine;

    public Game(Engine engine)
    {
        this.engine = engine;
    }

    public void create()
    {
        newBot();
    }

    private void newBot()
    {
        BehaviourEntity bot = engine.newEntity();
        bot.addComponent(new HealthComponent(bot))
                .addComponent(new StaminaComponent(bot))
                .addComponent(new AiComponent(bot))
                .addComponent(new InventoryComponent(bot));
    }

}
