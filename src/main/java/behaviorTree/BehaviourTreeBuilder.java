package behaviorTree;

import behaviorTree.ifs.IBehaviourNode;
import behaviorTree.nodeBuilder.BehaviourNodeBuilder;

import java.util.Stack;

/**
 * @Description 行为树构造器
 * @Author zhangfan
 * @Date 2020/11/22 17:08
 * @Version 1.0
 */
public class BehaviourTreeBuilder<T>
{
    private Stack<IBehaviourNode> stack = new Stack<>();

    private BehaviourNodeBuilder<T> rootBuilder;

    public static <T> BehaviourTreeBuilder<T> create()
    {
        return new BehaviourTreeBuilder<>();
    }

    public BehaviourTreeBuilder end()
    {
        stack.pop();
        return this;
    }

    public BehaviourTreeBuilder pushSubTree()
    {
        return this;
    }

    public BehaviourTreeBuilder pushLeafNode()
    {
        return this;
    }

    public BehaviourTreeBuilder pushComposite()
    {
        IBehaviourNode cur = stack.peek();


        return this;
    }

    public IBehaviourNode<T> build()
    {
        return rootBuilder.build();
    }


}
