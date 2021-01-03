package behaviorTree.GameEngine.familyManager;

import behaviorTree.GameEngine.familyManager.node.Node;
import behaviorTree.entity.Entity;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @Description 包装 主要是泛型
 * @Author zhangfan
 * @Date 2020/12/10 12:47
 * @Version 1.0
 */
public class ComponentMatchingFamily implements IFamily
{

    private Type type;

    public ComponentMatchingFamily(Type type)
    {
        this.type = type;
    }

    @Override
    public List<Node> getNodes()
    {
        return null;
    }

    @Override
    public void newEntity(Entity entity)
    {

    }

    @Override
    public void removeEntity(Entity entity)
    {

    }

    @Override
    public void ComponentAddedToEntity(Entity entity, Type componentType)
    {

    }

    @Override
    public void ComponentRemovedFromEntity(Entity entity, Type componentType)
    {

    }
}
