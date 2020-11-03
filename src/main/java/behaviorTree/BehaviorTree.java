package behaviorTree;

import behaviorTree.context.IContext;
import behaviorTree.ifs.IBehaviourNode;
import behaviorTree.ifs.single.ILeafNode;
import behaviorTree.treeEvent.ITreeEvent;
import simpleThreadProcessPool.service.AbstractService;

/**
 * TODO 行为树可以连接一个新的行为树
 * 行为树 门面类 (一颗树下面仅允许有一个运行节点)
 * Tree控制着结构.  Node控制逻辑
 * 深度优先搜索
 *
 * @author Administrator
 */
public class BehaviorTree<T extends IContext> extends AbstractService
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
    private ILeafNode<T> runningNode;

    /**
     * 行为树需要有个能接受外部事件的接口
     *
     * @param treeEvent
     */
    public void accept(ITreeEvent treeEvent)
    {
        treeEvent.accept(this);
    }

    public void setRootNode(IBehaviourNode<T> rootNode)
    {
        this.rootNode = rootNode;
    }

    public boolean isRunning()
    {
        return runningNode != null;
    }

    public IBehaviourNode<T> getRunningNode()
    {
        return runningNode;
    }

    public void setRunningNode(ILeafNode<T> runningNode)
    {
        this.runningNode = runningNode;
    }

    public IBehaviourNode<T> getRootNode()
    {
        return rootNode;
    }

    @Override
    public void init()
    {

    }

    @Override
    public void tick(int interval)
    {
        if (runningNode != null)
        {
            runningNode.tick(interval);
        }
        else
        {
            rootNode.tick(interval);
        }
    }

    @Override
    public void destory()
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }
}
