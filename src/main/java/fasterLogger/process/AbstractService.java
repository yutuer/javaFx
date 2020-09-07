package fasterLogger.process;

/**
 * @Description service
 * @Author zhangfan
 * @Date 2020/9/7 17:38
 * @Version 1.0
 */
public abstract class AbstractService implements IService
{
    @Override
    public void tick(int ticker)
    {
        try
        {
            tick0(ticker);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    protected abstract void tick0(int ticker);
}
