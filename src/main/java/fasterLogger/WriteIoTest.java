package fasterLogger;

import fasterLogger.v1.WriteThread;
import fasterLogger.write.WriterBuffer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description 写io测试
 * @Author zhangfan
 * @Date 2020/9/4 10:03
 * @Version 1.0
 */
public class WriteIoTest
{

    public static void main(String[] args) throws FileNotFoundException
    {
        final Binder binder = new Binder();
        WriteBlackBox writeBlackBox = new WriteBlackBox(binder);

        final IFastLogger fastLogger = new FastLogger("FastLogger Test1", writeBlackBox);
        for (int i = 0; i < 4; i++)
        {
            new WriteThread("Thread" + i, fastLogger).start();
        }

        final String fileName = "c:/1.txt";
        File file = new File(fileName);
        file.delete();

        final WriterBuffer writerBuffer = new WriterBuffer(new FileOutputStream(fileName, true));
        ScheduledExecutorService consumeEs = Executors.newScheduledThreadPool(1);
        consumeEs.scheduleAtFixedRate(() ->
                {
                    binder.writeToBufferAndFlush(writerBuffer);
                }
                , 0, 1, TimeUnit.SECONDS);

    }
}
