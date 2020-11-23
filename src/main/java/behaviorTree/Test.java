package behaviorTree;

import behaviorTree.entity.Bot;
import behaviorTree.entity.Game;

public class Test
{

	public static void main(String[] args)
	{
		Game game = new Game();
		Bot bot = game.newBot();

		game.addEntity(bot);


		
	}
}
