package behaviorTree.GameEngine.familyManager;

import behaviorTree.GameEngine.entityManager.EntityManager;
import behaviorTree.IComponent;

import java.util.List;
import java.util.Map;

/**
 * @Description 模仿客户端, 每个组件都由每个独立的系统来负责调度.
 * 所以这个类里面会维护Entity和组件之间的联系
 * @Author zhangfan
 * @Date 2020/12/9 10:49
 * @Version 1.0
 */
public class FamilyManager
{

    private EntityManager entityManager;

    private Map<Class, List<FNode>> nodes;

    public FamilyManager(EntityManager entityManager)
    {
        this.entityManager = entityManager;
        entityManager.addAddHandler((game, entity) ->
        {

        });
    }

    public <T extends IComponent> List<FNode> getNodes(Class<T> c)
    {
        return nodes.get(c);
    }


    public <TNodeType> List<FNode<TNodeType>> getNodes()
    {

        return null;
    }
}
