package simpleThreadProcessPool;

import java.util.concurrent.TimeUnit;

/**
 * @Description 程序启动入口
 * @Author zhangfan
 * @Date 2020/9/30 10:27
 * @Version 1.0
 */
public class ServiceSchedulerBooter
{
    public static void main(String[] args) throws InterruptedException
    {
        ServiceScheduler serviceScheduler = new ServiceScheduler(Runtime.getRuntime().availableProcessors() + 1, 0, 1, TimeUnit.MILLISECONDS);
        serviceScheduler.start();

        Thread.sleep(10 * 1000L);

        serviceScheduler.shutDown();
    }
}
