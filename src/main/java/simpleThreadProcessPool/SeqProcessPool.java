package simpleThreadProcessPool;

/**
 * @Description 顺序 process池的具体实现
 * 使用先进先出的策略.
 *
 * @Author zhangfan
 * @Date 2020/9/30 10:26
 * @Version 1.0
 */
public class SeqProcessPool implements IProcessPool
{

    /**
     * 单例
     */
    private static final SeqProcessPool processPool = new SeqProcessPool();

    private SeqProcessPool()
    {
    }

    public static SeqProcessPool getInstance()
    {
        return processPool;
    }




    @Override
    public void addService(IService service)
    {

    }

    @Override
    public int size()
    {
        return 0;
    }

    @Override
    public void removeService(IService service)
    {

    }

    @Override
    public void setServiceSchedulerStrategy(IServiceSchedulerStrategy serviceSchedulerStrategy)
    {

    }
}
