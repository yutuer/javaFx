package behaviorTree;

import behaviorTree.context.IContext;
import behaviorTree.ifs.IBehaviourNode;
import behaviorTree.treeEvent.ITreeEvent;

import java.util.concurrent.TimeUnit;

/**
 * 行为树 门面类 (一颗树下面仅允许有一个运行节点)
 * <p>
 * TODO 行为树可以连接一个新的行为树
 * <p>
 * Tree控制着结构.  Node控制逻辑
 *
 * @author Administrator
 */
public class BehaviorTree<T extends IContext>
{

    /**
     * 根节点
     */
    private IBehaviourNode<T> rootNode;

    /**
     * 当前运行节点 null 表示没有
     */
    private IBehaviourNode<T> runningNode;

    public void accept(ITreeEvent treeEvent)
    {
        treeEvent.accpet(this);
    }

    /**
     * tick
     */
    public void tick()
    {
        while (true)
        {

            rootNode.doLogic();

            try
            {
                System.out.println("================我是分割线========================");

                TimeUnit.SECONDS.sleep(1);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

        }
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

    public void setRunningNode(IBehaviourNode<T> runningNode)
    {
        this.runningNode = runningNode;
    }

    public IBehaviourNode<T> getRootNode()
    {
        return rootNode;
    }
}
