package simpleThreadProcessPool;

/**
 * @Description service调度策略
 * @Author zhangfan
 * @Date 2020/9/30 10:19
 * @Version 1.0
 */
public interface IServiceSchedulerStrategy
{
    /**
     * 获取下一个待执行的service
     *
     * @return
     */
    IService next();

}
