package pulsarTest.bootstrap;

import org.apache.bookkeeper.bookie.Bookie;
import org.apache.bookkeeper.bookie.BookieException;
import org.apache.bookkeeper.conf.ServerConfiguration;
import pulsarTest.ReceiveMessage;

import java.io.IOException;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/7/1 18:13
 * @Version 1.0
 */
public class ReceiveBootStrap
{
    public static void main(String[] args) throws InterruptedException, BookieException, IOException
    {
        ReceiveMessage.receive();

        Bookie bookie = new Bookie(new ServerConfiguration());

    }
}
