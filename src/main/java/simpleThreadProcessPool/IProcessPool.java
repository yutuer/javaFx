package simpleThreadProcessPool;

/**
 * @Description process池. 使用策略将iService交给池来调度
 * @Author zhangfan
 * @Date 2020/9/30 10:14
 * @Version 1.0
 */
public interface IProcessPool
{

    /**
     * 将一个service加入到池中
     *
     * @param service
     */
    void addService(IService service);

    /**
     * 当前大小
     *
     * @return
     */
    int size();

    /**
     * 从池中移除一个service
     *
     * @param service
     */
    void removeService(IService service);

    /**
     * 设置调度策略
     *
     * @param serviceSchedulerStrategy
     */
    void setServiceSchedulerStrategy(IServiceSchedulerStrategy serviceSchedulerStrategy);
}
