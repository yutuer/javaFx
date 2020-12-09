package behaviorTree.entity;

import behaviorTree.GameEngine.Engine;
import behaviorTree.entity.component.*;

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
        newFood();
        newBot();
    }

    /**
     * 构造食物
     */
    private void newFood()
    {
        BehaviourEntity food = engine.newEntity();
        food.addComponent(new PositionComponent(food, 100, 200))
                .addComponent(new LootableComponent(food, 1));
        ;

    }

    /**
     * 构造ai机器人
     */
    private void newBot()
    {
        BehaviourEntity bot = engine.newEntity();
        bot.addComponent(new HealthComponent(bot))
                .addComponent(new StaminaComponent(bot))
                .addComponent(new AiComponent(bot))
                .addComponent(new InventoryComponent(bot))
                .addComponent(new PositionComponent(bot, 0, 0));
    }

}
