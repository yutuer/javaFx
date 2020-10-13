package behaviorTree.impl;

import behaviorTree.context.IContext;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.IBehaviourNode;
import behaviorTree.ifs.composite.ISelectNode;

import java.util.ArrayList;
import java.util.List;

public abstract class SelectorNode<T extends IContext> extends CompositeNode<T> implements ISelectNode<T>
{

    private List<IBehaviourNode> list = new ArrayList<>();

    /**
     * 当前状态
     */
    private NodeStatusEnum nodeStatusEnum = NodeStatusEnum.Failure;

    public void addNode(IBehaviourNode node)
    {
        list.add(node);
    }

    @Override
    public NodeStatusEnum tick(int interval)
    {
        if (getStatus() == NodeStatusEnum.Running)
        {
            return NodeStatusEnum.Running;
        }

//        setStatus(NodeStatusEnum.Running);
//
//        System.out.println("开始进行:" + tip);
//
//        try
//        {
//            List<IBehaviourNode> list = getChildren();
//            int size = list.size();
//            for (int i = 0; i < size; i++)
//            {
//                IBehaviourNode iCommonNode = list.get(i);
//                if (iCommonNode.doLogic() == NodeStatusEnum.Success)
//                {
//                    return NodeStatusEnum.Success;
//                }
//            }
//        }
//        finally
//        {
//            setStatus(NodeStatusEnum.Default);
//        }

        return NodeStatusEnum.Failure;
    }

}
