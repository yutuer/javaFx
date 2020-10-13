package behaviorTree.impl;

import behaviorTree.context.IContext;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.IBehaviourNode;
import behaviorTree.ifs.composite.ISequenceNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 相当于and操作
 *
 * @author Administrator
 */
public abstract class SequenceNode<T extends IContext> extends CompositeNode<T> implements ISequenceNode<T>
{

    private List<IBehaviourNode> list = new ArrayList<>();
    private String tip;

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
//                if (!iCommonNode.doLogic())
//                {
//                    return false;
//                }
//            }
//        }
//        finally
//        {
//            setStatus(NodeStatusEnum.Default);
//        }

        return NodeStatusEnum.Success;
    }

    public void setTip(String tip)
    {
        this.tip = tip;
    }
}
