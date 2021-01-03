package behaviorTree.GameEngine.familyManager;

import behaviorTree.GameEngine.entityManager.EntityManager;
import behaviorTree.GameEngine.familyManager.node.Node;
import behaviorTree.GameEngine.system.IterativeSystem;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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

    private Map<Type, IFamily> families;

    public FamilyManager(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    private <TNodeType extends Node> Type getType(IterativeSystem<TNodeType> system)
    {
        Type genericSuperclass = system.getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) genericSuperclass;
        Type actualTypeArgument = pt.getActualTypeArguments()[0];
        return actualTypeArgument;
    }

    public <TNodeType extends Node> List<Node> getNodes(IterativeSystem<TNodeType> system)
    {
        Type type = getType(system);
        IFamily iFamily = families.get(type);
        if (iFamily == null)
        {
            iFamily = new ComponentMatchingFamily(type);
            families.put(type, iFamily);
        }
        return iFamily.getNodes();
    }

}
