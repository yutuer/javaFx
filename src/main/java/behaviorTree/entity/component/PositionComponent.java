package behaviorTree.entity.component;

import behaviorTree.entity.BehaviourEntity;

/**
 * @Description 位置组件
 * @Author zhangfan
 * @Date 2020/11/24 10:42
 * @Version 1.0
 */
public class PositionComponent extends BaseComponent
{
    private int x;
    private int y;

    public PositionComponent(BehaviourEntity entity, int x, int y)
    {
        super(entity);
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }
}
