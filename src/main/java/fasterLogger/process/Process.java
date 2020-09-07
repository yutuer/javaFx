package fasterLogger.process;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/7 17:18
 * @Version 1.0
 */
public class Process
{
    private IService service;

    /**
     * 调度间隔
     */
    private int frame;

    /**
     * 上次执行时间
     */
    private volatile long lastExecuteTime;

    public Process(IService service, int frame)
    {
        this.service = service;
        this.frame = frame;
    }

    /**
     * 执行逻辑
     *
     * @param now 当前时间
     */
    public boolean ticker(long now)
    {
        if (isCanExecute(now))
        {
            int ticker = (int) (now - lastExecuteTime);

            service.tick(ticker);
            return true;
        }
        return false;
    }

    /**
     * 是否可以执行
     *
     * @param now
     * @return
     */
    public boolean isCanExecute(long now)
    {
        return lastExecuteTime + frame >= now;
    }

    public void setLastExecuteTime(long lastExecuteTime)
    {
        this.lastExecuteTime = lastExecuteTime;
    }
}
