package simpleThreadProcessPool;

import simpleThreadProcessPool.service.AbstractService;

import java.util.ArrayDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Description 顺序 process池的具体实现
 * 使用先进先出的策略.
 * @Author zhangfan
 * @Date 2020/9/30 10:26
 * @Version 1.0
 */
public class SimpleProcessPool
{
    private ExecutorService executorService;
    /**
     *
     */
    private ArrayDeque<Process> processes = new ArrayDeque<>();

    /**
     *
     */
    private ArrayDeque<Process> waitAddProcesses = new ArrayDeque<>();
    /**
     *
     */
    private ArrayDeque<Process> canRunProcesses = new ArrayDeque<>();


    private ArrayDeque<Future<Process>> runningProcesses = new ArrayDeque<>();

    /**
     * 允许最高运行数量
     */
    private int maxRun;

    public SimpleProcessPool(int maxRun)
    {
        this.maxRun = maxRun;
        this.executorService = Executors.newFixedThreadPool(maxRun);
    }

    public void tick()
    {
        tickRunningProcess();

        tickWaitAddProcess();

        tickCanRunProcess();

        tickRunProcess();
    }

    /**
     * 处理哪些process运行完毕
     */
    private void tickRunningProcess()
    {
        int count = runningProcesses.size();
        while (count-- > 0)
        {
            Future<Process> future = runningProcesses.poll();
            if (future.isDone())
            {
                try
                {
                    waitAddProcesses.addLast(future.get());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                runningProcesses.addLast(future);
            }
        }
    }

    private void tickRunProcess()
    {
        while (canRunProcesses.size() > 0 && runningProcesses.size() < maxRun)
        {
            Process process = canRunProcesses.poll();
            Future<Process> future = executorService.submit(process);
            runningProcesses.addLast(future);
        }
    }

    private void tickCanRunProcess()
    {
        long now = SystemClock.getSystemClock();
        int size = processes.size();
        while (size-- > 0)
        {
            Process process = processes.poll();
            if (process.canExec(now))
            {
                canRunProcesses.addLast(process);
            }
            else
            {
                processes.addLast(process);
            }
        }
    }

    public void tickWaitAddProcess()
    {
        while (!waitAddProcesses.isEmpty())
        {
            processes.addLast(waitAddProcesses.poll());
        }
    }

    public void addService(AbstractService service, int frameRate)
    {
        add(new Process(service, frameRate));
    }

    public void add(Process process)
    {
        waitAddProcesses.addLast(process);
    }

    public void removeService(AbstractService service)
    {
        for (Process process : processes)
        {
            if (process.isSameService(service))
            {
                processes.remove(process);
                break;
            }
        }
        for (Process process : waitAddProcesses)
        {
            if (process.isSameService(service))
            {
                processes.remove(process);
                break;
            }
        }
    }

}
