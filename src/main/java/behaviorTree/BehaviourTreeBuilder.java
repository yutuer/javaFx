package behaviorTree;

import behaviorTree.core.BTContext;
import behaviorTree.core.NodeStatusEnum;
import behaviorTree.ifs.IBehaviourNode;
import behaviorTree.ifs.composite.SequenceNode;
import behaviorTree.ifs.composite.decorator.InvertNode;
import behaviorTree.ifs.composite.selector.PrioritySelectorNode;
import behaviorTree.ifs.composite.selector.SelectorNode;
import behaviorTree.ifs.single.ICondition;
import behaviorTree.ifs.single.action.ActionNode;
import behaviorTree.ifs.single.condition.Condition;

import java.util.Stack;
import java.util.function.BiFunction;

/**
 * @Description 行为树构造器, 使用节点构造
 * @Author zhangfan
 * @Date 2020/11/22 17:08
 * @Version 1.0
 */
public class BehaviourTreeBuilder<T>
{
    private Stack<IBehaviourNode<T>> stack = new Stack<>();

    private IBehaviourNode<T> rootNode;

    public static <T> BehaviourTreeBuilder<T> create()
    {
        return new BehaviourTreeBuilder<>();
    }

    public BehaviourTreeBuilder<T> end()
    {
        stack.pop();
        return this;
    }

    /**
     * 压入一个子树
     *
     * @return
     */
    public BehaviourTreeBuilder<T> pushSubTree(IBehaviourNode<T> node)
    {
        this.pushLeafNode(node);
        return this;
    }

    /**
     * 不需要将子节点压栈
     *
     * @return
     */
    public BehaviourTreeBuilder<T> pushLeafNode(IBehaviourNode<T> node)
    {
        if (stack.isEmpty())
        {
            stack.push(node);

            rootNode = node;
        }
        else
        {
            IBehaviourNode<T> cur = stack.peek();
            cur.addChild(node);
        }
        return this;
    }

    /**
     * 需要将子节点压栈, 对应的以后结束后用end方法退出
     *
     * @param node
     * @return
     */
    public BehaviourTreeBuilder<T> pushComposite(IBehaviourNode<T> node)
    {
        if (!stack.isEmpty())
        {
            IBehaviourNode<T> cur = stack.peek();
            cur.addChild(node);
        }
        else
        {
            rootNode = node;
        }

        stack.push(node);

        return this;
    }

    public IBehaviourNode<T> build()
    {
        if (rootNode == null)
        {
            throw new RuntimeException("rootNode is null");
        }
        return rootNode;
    }

    /**
     * 添加Invert节点
     *
     * @param tip
     * @return
     */
    public BehaviourTreeBuilder<T> Invert(String tip)
    {
        pushComposite(new InvertNode<>(tip));
        return this;
    }

    /**
     * 添加Action节点
     *
     * @param tip
     * @return
     */
    public BehaviourTreeBuilder<T> Do(String tip, BiFunction<BTContext<T>, Integer, NodeStatusEnum> biFunction)
    {
        pushLeafNode(new ActionNode<>(tip, biFunction));
        return this;
    }

    /**
     * 添加Select节点
     *
     * @param tip
     * @return
     */
    public BehaviourTreeBuilder<T> Selector(String tip)
    {
        pushComposite(new SelectorNode<>(tip));
        return this;
    }

    /**
     * 添加Sequence节点
     *
     * @param tip
     * @return
     */
    public BehaviourTreeBuilder<T> Sequence(String tip)
    {
        pushComposite(new SequenceNode<>(tip));
        return this;
    }

    /**
     * 添加Sequence节点
     *
     * @param tip
     * @return
     */
    public BehaviourTreeBuilder<T> PrioritySelector(String tip)
    {
        pushComposite(new PrioritySelectorNode<>(tip));
        return this;
    }

    /**
     * 添加Condition节点
     *
     * @param tip
     * @return
     */
    public BehaviourTreeBuilder<T> Condition(String tip, ICondition<T> condition)
    {
        pushLeafNode(new Condition<T>(tip, condition));
        return this;
    }


}
