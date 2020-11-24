package behaviorTree;

import behaviorTree.GameEngine.Engine;
import behaviorTree.entity.Game;
import simpleThreadProcessPool.MainCircle;
import simpleThreadProcessPool.Process;
import simpleThreadProcessPool.SimpleProcessPool;

public class Test
{

    public static void main(String[] args)
    {
        Engine engine = new Engine();
        engine.init();

        Game game = new Game(engine);
        game.create();

        SimpleProcessPool simpleProcessPool = new SimpleProcessPool(1);
        MainCircle mainCircle = new MainCircle(simpleProcessPool);
        mainCircle.start();

        simpleProcessPool.add(new Process(engine, 1000));

    }
}
