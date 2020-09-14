package fasterLogger.v1;

import fasterLogger.IFastLogger;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/7 12:53
 * @Version 1.0
 */
public class WriteThread extends Thread
{
    private IFastLogger fastLogger;

    private int counter;

    public WriteThread(String name, IFastLogger fastLogger)
    {
        super(name);
        this.fastLogger = fastLogger;
    }

    @Override
    public void run()
    {
        while (true)
        {
            fastLogger.log(getName(), counter++, "");
            try
            {
                Thread.sleep(10);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
