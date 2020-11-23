package behaviorTree;

import behaviorTree.builder.BehaviourNodeBuilder;
import behaviorTree.ifs.IBehaviourNode;

import java.util.Stack;
import java.util.function.Function;

/**
 * @Description 行为树构造器, 但是使用的是builder模式
 * @Author zhangfan
 * @Date 2020/11/22 17:08
 * @Version 1.0
 */
public class BehaviourTreeBuilder2<T>
{
    private Stack<BehaviourNodeBuilder<T>> stack = new Stack<>();

    public static <T> BehaviourTreeBuilder2<T> create()
    {
        return new BehaviourTreeBuilder2<>();
    }

    public BehaviourTreeBuilder2<T> end()
    {
        stack.pop();
        return this;
    }

    /**
     * 压入一个子树
     *
     * @param nodeBuilder
     * @return
     */
    public BehaviourTreeBuilder2<T> pushSubTree(BehaviourNodeBuilder<T> nodeBuilder)
    {
        this.pushLeafNode(nodeBuilder);
        return this;
    }

    /**
     * 不需要将子节点压栈
     *
     * @param nodeBuilder
     * @return
     */
    public BehaviourTreeBuilder2<T> pushLeafNode(BehaviourNodeBuilder<T> nodeBuilder)
    {
        BehaviourNodeBuilder<T> cur = stack.peek();
        if (cur == null)
        {
            stack.push(nodeBuilder);
        }
        else
        {
            cur.addChild(nodeBuilder);
        }
        return this;
    }

    /**
     * 需要将子节点压栈, 对应的以后结束后用end方法退出
     *
     * @param nodeBuilder
     * @return
     */
    public BehaviourTreeBuilder2<T> pushComposite(BehaviourNodeBuilder<T> nodeBuilder)
    {
        BehaviourNodeBuilder<T> cur = stack.peek();
        if (cur != null)
        {
            cur.addChild(nodeBuilder);
        }

        stack.push(nodeBuilder);

        return this;
    }

    public IBehaviourNode<T> build()
    {
        BehaviourNodeBuilder<T> nodeBuilder = stack.pop();
        return nodeBuilder.build();
    }

    /**
     * 添加Invert节点
     *
     * @param tip
     * @param func
     * @param <P>
     * @param <R>
     * @return
     */
    public <P, R> BehaviourTreeBuilder2<T> Invert(String tip, Function<P, R> func)
    {

        return this;
    }

}
