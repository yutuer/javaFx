package simpleThreadProcessPool;

import simpleThreadProcessPool.service.TestService;

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
        SimpleProcessPool simpleProcessPool = new SimpleProcessPool(Runtime.getRuntime().availableProcessors());
        MainCircle mainCircle = new MainCircle(simpleProcessPool);
        mainCircle.start();

        for (int i = 0; i < 10; i++)
        {
            simpleProcessPool.addService(new TestService(i), (i + 1) * 1000);
        }
    }
}
