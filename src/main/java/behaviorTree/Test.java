package behaviorTree;

import behaviorTree.nodes.NodeUtil;

public class Test
{

	public static void main(String[] args)
	{
		BehaviorTree behaviorTree = new BehaviorTree();
		behaviorTree.setRootNode(NodeUtil.AttackPlayer);

		behaviorTree.tick(0);
	}
}
