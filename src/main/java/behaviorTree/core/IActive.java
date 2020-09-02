package behaviorTree.core;

/**
 * @Description 激活, 用于图形表现
 * @Author zhangfan
 * @Date 2020/8/28 10:30
 * @Version 1.0
 */
public interface IActive
{

    /**
     * 进入
     */
    void enter();

    /**
     * 离开
     */
    void leave();
}
