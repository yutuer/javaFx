package simpleThreadProcessPool;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/30 10:14
 * @Version 1.0
 */
public interface IService
{
    /**
     * 核心方法,  tick
     *
     * @param inteval 间隔的毫秒数
     */
    void tick(int inteval);

    /**
     * 被从池中移除出去的时候, 可以做清理工作
     */
    void close();

    /**
     * 暂停(TODO)
     */
    void pause();

    /**
     * 重新启动(TODO)
     */
    void resume();
}
