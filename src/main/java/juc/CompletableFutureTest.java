package juc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description
 * @Author zhangfan
 * @Date 2020/8/3 17:22
 * @Version 1.0
 */
public class CompletableFutureTest
{
    /**
     * logger
     */
    public static final Logger logger = LoggerFactory.getLogger(CompletableFutureTest.class);

    public void a()
    {
        List<CompletableFuture<Void>> list = new ArrayList<>();

        ExecutorService mainExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 1);

        for (int i = 1; i <= 20; i++)
        {
            final int j = i;
            CompletableFuture<Void> future = CompletableFuture.runAsync(() ->
            {
                try
                {
                    Thread.sleep((long) (3000L * Math.random()));
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                logger.info("j: {}", j);
            }, mainExecutorService);

            list.add(future);
        }

        CompletableFuture.allOf(list.toArray(new CompletableFuture[0])).whenComplete((r, e) ->
        {
            System.out.println("All finish");
        }).join();

        mainExecutorService.shutdown();
    }

    public static void main(String[] args)
    {
        new CompletableFutureTest().a();
    }
}
