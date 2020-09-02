package pulsarTest.bootstrap;

import pulsarTest.ReceiveMessage;
import pulsarTest.SendMessage;

/**
 * @Description
 * @Author zhangfan
 * @Date 2020/7/1 17:37
 * @Version 1.0
 */
public class Bootstrap
{
    public static void main(String[] args)
    {
        try
        {
            SendMessage.send();

            Thread.sleep(1000);

            ReceiveMessage.receive();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
