package behaviorTree.impl;

import behaviorTree.context.IContext;
import behaviorTree.core.IEdge;
import behaviorTree.ifs.IBehaviourNode;
import behaviorTree.ifs.composite.ICompositeNode;

/**
 * @Description 组合节点
 * @Author zhangfan
 * @Date 2020/8/28 14:59
 * @Version 1.0
 */
public abstract class CompositeNode<T extends IContext> extends BaseNode<T> implements ICompositeNode<T>
{



    @Override
    public IBehaviourNode<T> get(int index)
    {
        return null;
    }

    @Override
    public int size()
    {
        return 0;
    }

    @Override
    public void addOuterLinkLine(IEdge linkLine)
    {

    }

    @Override
    public void removeOuterLinkLine(IEdge linkLine)
    {

    }

}
