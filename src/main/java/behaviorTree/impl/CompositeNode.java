package behaviorTree.impl;

import behaviorTree.context.IContext;
import behaviorTree.ifs.IBehaviourNode;
import behaviorTree.ifs.composite.ICompositeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 组合节点
 * @Author zhangfan
 * @Date 2020/8/28 14:59
 * @Version 1.0
 */
public abstract class CompositeNode<T extends IContext> extends BaseNode<T> implements ICompositeNode<T>
{

    private List<IBehaviourNode<T>> childrens = new ArrayList<>();

    /**
     * 当前索引
     */
    private int currentIndex = 0;

    @Override
    public IBehaviourNode<T> get(int index)
    {
        return childrens.get(index);
    }

    @Override
    public int size()
    {
        return childrens.size();
    }

    @Override
    public void addChild(IBehaviourNode<T> node)
    {
        childrens.add(node);
    }

    @Override
    public void removeChild(IBehaviourNode<T> node)
    {
        childrens.remove(node);
    }

    /**
     * 重置 迭代索引
     */
    public final void resetIndex()
    {
        currentIndex = 0;
    }

    @Override
    public IBehaviourNode<T> next()
    {
        return get(currentIndex++);
    }

    @Override
    public boolean hasNext()
    {
        return currentIndex < size();
    }

    @Override
    public void onEnter()
    {
        super.onEnter();

        // 重置循环索引
        resetIndex();
    }

    @Override
    public void onLeave()
    {
        super.onLeave();
    }
}
