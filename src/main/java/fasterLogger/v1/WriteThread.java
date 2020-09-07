package fasterLogger.v1;

import fasterLogger.FastLogger;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/7 12:53
 * @Version 1.0
 */
public class WriteThread extends Thread
{
    private FastLogger fastLogger;

    public WriteThread(String name, FastLogger fastLogger)
    {
        super(name);
        this.fastLogger = fastLogger;
    }

    @Override
    public void run()
    {
        while (true)
        {
            fastLogger.log(getName(), 0, "");

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
