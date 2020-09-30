package simpleThreadProcessPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description service调度器
 * @Author zhangfan
 * @Date 2020/9/30 10:14
 * @Version 1.0
 */
public class ServiceScheduler
{
    private ScheduledExecutorService ses;

    private int initialDelay;
    private long period;
    private TimeUnit unit;

    public ServiceScheduler(int coreSize, int initialDelay, long period, TimeUnit unit)
    {
        ses = Executors.newScheduledThreadPool(coreSize);
        this.initialDelay = initialDelay;
        this.period = period;
        this.unit = unit;
    }

    public void start()
    {
        ses.scheduleAtFixedRate(() ->
        {
            System.out.println(111);
        }, initialDelay, period, unit);
    }

    public void shutDown()
    {
        if (ses != null)
        {
            ses.shutdown();
        }
    }

}
