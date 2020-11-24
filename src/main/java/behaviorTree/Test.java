package behaviorTree;

import behaviorTree.entity.Bot;
import behaviorTree.entity.Game;
import simpleThreadProcessPool.MainCircle;
import simpleThreadProcessPool.Process;
import simpleThreadProcessPool.SimpleProcessPool;

public class Test
{

    public static void main(String[] args)
    {
        Game game = new Game();
        Bot bot = game.newBot();

        game.addEntity(bot);

        SimpleProcessPool simpleProcessPool = new SimpleProcessPool(1);
        MainCircle mainCircle = new MainCircle(simpleProcessPool);
        mainCircle.start();

        simpleProcessPool.add(new Process(game, 1000));

    }
}
