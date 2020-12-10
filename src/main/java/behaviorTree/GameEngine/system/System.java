package behaviorTree.GameEngine.system;

import behaviorTree.GameEngine.Engine;
import behaviorTree.GameEngine.familyManager.FNode;

import java.util.List;

/**
 * @Description engine上的module. 每个System都负责调用有某种特定功能的组件
 * 比如声音System, 会负责调度所有有声音组件的entity..
 * @Author zhangfan
 * @Date 2020/12/10 11:29
 * @Version 1.0
 */
public abstract class System<TNodeType> implements ISystem
{

    protected Engine engine;

    protected List<FNode<TNodeType>> nodes;

    public System(Engine engine)
    {
        this.engine = engine;
        nodes = engine.getNodes();
    }

    public abstract void update(int interval);

}
