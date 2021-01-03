package behaviorTree.GameEngine.familyManager;

import behaviorTree.GameEngine.familyManager.node.Node;
import behaviorTree.entity.Entity;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @Description family 控制接口
 * @Author zhangfan
 * @Date 2020/12/10 14:10
 * @Version 1.0
 */
public interface IFamily
{

    List<Node> getNodes();

    void newEntity(Entity entity);

    void removeEntity(Entity entity);

    void ComponentAddedToEntity(Entity entity, Type componentType);

    void ComponentRemovedFromEntity(Entity entity, Type componentType);

}
