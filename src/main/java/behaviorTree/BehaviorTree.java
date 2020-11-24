package behaviorTree;

import behaviorTree.context.BTContext;
import behaviorTree.ifs.IBehaviourNode;
import behaviorTree.treeEvent.ITreeEvent;

/**
 * 行为树 门面类 (一颗树下面仅允许有一个运行节点)
 * Tree控制着结构.  Node控制逻辑
 * 深度优先搜索
 *
 * @author Administrator
 */
public class BehaviorTree<T>
{

    /**
     * 根节点
     */
    private IBehaviourNode<T> rootNode;

    /**
     * 当前运行节点 null表示没有. 只有leaf节点 才能有运行状态
     * <p>
     * 运行的节点的条件, 要么自己慢慢终止, 要么由外部事件触发终止
     */
//    private ILeafNode<T> runningNode;

//    private IContext<T> context;
    public BehaviorTree(IBehaviourNode<T> rootNode)
    {
        this.rootNode = rootNode;
    }

    /**
     * 行为树需要有个能接受外部事件的接口
     *
     * @param treeEvent
     */
    public void accept(ITreeEvent treeEvent)
    {
        treeEvent.accept(this);
    }

//    public boolean isRunning()
//    {
//        return runningNode != null;
//    }

//    public IBehaviourNode<T> getRunningNode()
//    {
//        return runningNode;
//    }
//
//    public void setRunningNode(ILeafNode<T> runningNode)
//    {
//        this.runningNode = runningNode;
//    }

    public void init()
    {

    }

    public void tick(BTContext<T> context, int interval)
    {
        rootNode.tick(context, interval);
    }

    public void destory()
    {

    }

    public void pause()
    {

    }

    public void resume()
    {

    }
}
