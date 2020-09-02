package behaviorTree.impl;

import behaviorTree.context.IContext;
import behaviorTree.ifs.single.IActionNode;

public abstract class ActionNode<T extends IContext> extends LeafNode<T> implements IActionNode<T>
{
}
