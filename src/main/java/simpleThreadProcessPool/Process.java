package simpleThreadProcessPool;

import simpleThreadProcessPool.service.AbstractService;

import java.util.concurrent.Callable;

/**
 * @Description 封装真正的任务
 * @Author zhangfan
 * @Date 2020/9/30 10:14
 * @Version 1.0
 */
public class Process implements Callable<Process>
{

    private boolean isInit;

    /**
     * 包装的真正的逻辑 service
     */
    private AbstractService service;

    /**
     * 运行的帧率
     */
    private int frameRate;

    /**
     * 上次运行时间
     */
    private long lastExecTime;

    public Process(AbstractService service, int frameRate)
    {
        this.service = service;
        this.frameRate = frameRate;
    }

    /**
     * 初始化
     */
    public void init()
    {
        if (!isInit)
        {
            isInit = true;
            lastExecTime = SystemClock.getSystemClock();

            service.init();
        }
    }

    /**
     * 是否可以执行
     *
     * @param now
     * @return
     */
    public boolean canExec(long now)
    {
        return now - lastExecTime >= frameRate;
    }

    /**
     * 执行
     *
     * @param now
     */
    public void exec(long now)
    {
        int diff = (int) (now - lastExecTime);
        lastExecTime = now;

        service.tick(diff);
    }

    /**
     * 关闭
     */
    public void close()
    {
        service.close();
    }

    @Override
    public Process call() throws Exception
    {
        init();

        exec(SystemClock.getSystemClock());

        return this;
    }

    public boolean isSameService(AbstractService service)
    {
        return this.service == service;
    }
}
