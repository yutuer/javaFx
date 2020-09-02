package behaviorTree;

import behaviorTree.Nodes.NodeUtil;

public class Test
{

	public static void main(String[] args)
	{
		BehaviorTree behaviorTree = new BehaviorTree();
		behaviorTree.setRootNode(NodeUtil.AttackPlayer);

		behaviorTree.tick();
	}
}
