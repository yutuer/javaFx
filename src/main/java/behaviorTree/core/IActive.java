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
     * 第一次进入回调
     */
    void onEnter();

    /**
     * 离开回调
     */
    void onLeave();
}
