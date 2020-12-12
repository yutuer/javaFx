package behaviorTree.GameEngine.familyManager;

import behaviorTree.GameEngine.familyManager.node.Node;

/**
 * @Description 包装 主要是泛型
 * @Author zhangfan
 * @Date 2020/12/10 12:47
 * @Version 1.0
 */
public class ComponentMatchingFamily<T extends Node> implements IFamily
{

    private T t;


    @Override
    public int getEntityId()
    {
        return 0;
    }
}
