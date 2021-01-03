package behaviorTree.GameEngine.system;

import behaviorTree.GameEngine.Engine;
import behaviorTree.GameEngine.familyManager.node.Node;

import java.util.List;

/**
 * @Description engine上的module. 每个System都负责调用有某种特定功能的组件
 * 比如声音System, 会负责调度所有有声音组件的entity..
 * @Author zhangfan
 * @Date 2020/12/10 11:29
 * @Version 1.0
 */
public abstract class IterativeSystem<TNodeType extends Node> implements ISystem
{

    /**
     * 引擎
     */
    protected Engine engine;

    /**
     * 特定System 的Node集合
     */
    protected List<TNodeType> nodes;

    public IterativeSystem(Engine engine)
    {
        this.engine = engine;

        nodes = engine.getNodes(this);
    }

    public void update(int interval)
    {
        for (TNodeType node : nodes)
        {
            updateNode(node, interval);
        }
    }

    protected abstract void updateNode(TNodeType node, int interval);

}
