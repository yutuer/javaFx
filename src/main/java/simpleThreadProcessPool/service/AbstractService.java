package simpleThreadProcessPool.service;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/30 10:14
 * @Version 1.0
 */
public abstract class AbstractService
{
    /**
     * 初始化
     */
    public void init()
    {

    }

    /**
     * 核心方法,  tick
     *
     * @param inteval 间隔的毫秒数
     */
    public void tick(int inteval)
    {

    }

    /**
     * 被从池中移除出去的时候, 可以做清理工作
     */
    public void destory()
    {

    }

    /**
     * 暂停(TODO)
     */
    public void pause()
    {

    }

    /**
     * 重新启动(TODO)
     */
    public void resume()
    {
    }

}
