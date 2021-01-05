package disruptorTest.dis;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2021/1/5 17:37
 * @Version 1.0
 */
public class My_BasicExecutor implements Executor
{
    private final ThreadFactory threadFactory;
    private final Queue<Thread> threads = new ConcurrentLinkedQueue<>();

    public My_BasicExecutor(ThreadFactory threadFactory)
    {
        this.threadFactory = threadFactory;
    }

    @Override
    public void execute(Runnable command)
    {
        final Thread thread = threadFactory.newThread(command);
        if (null == thread)
        {
            throw new RuntimeException("Failed to create thread to run: " + command);
        }

        thread.start();

        threads.add(thread);
    }


}
