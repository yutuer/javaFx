package behaviorTree.ifs.single;

import behaviorTree.context.IContext;
import behaviorTree.core.NodeStatusEnum;

public interface IConditionNode<T extends IContext> extends ILeafNode<T>
{

    NodeStatusEnum isOk();
}
