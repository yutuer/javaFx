package fasterLogger.write;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Description 写入缓冲区
 * @Author zhangfan
 * @Date 2020/9/4 13:00
 * @Version 1.0
 */
public class WriterBuffer
{
    /**
     * 这里os是 volatile的
     */
    private volatile OutputStream os;

    //4k page
    private final int pageSize = 1 << 12;

    public WriterBuffer(OutputStream os, int pageSize)
    {
        this.setOs(os, pageSize);
    }

    public WriterBuffer(OutputStream os)
    {
        this.setOs(os);
    }

    /**
     * 写数据
     *
     * @param data
     */
    public void write(byte[] data)
    {
        if (data == null)
        {
            return;
        }

        try
        {
            os.write(data);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 刷新到存储设备, 外部调用接口
     */
    public void flush()
    {
        final OutputStream outputStream = this.os;
        if (outputStream != null)
        {
//            writeBufferToOutputSteam(byteBuffer);
            flushDestination();
        }
    }

    private void flushDestination()
    {
        final OutputStream outputStream = this.os;
        if (outputStream != null)
        {
            try
            {
                outputStream.flush();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void setOs(OutputStream os, int size)
    {
        if (!(os instanceof BufferedOutputStream))
        {
            this.os = new BufferedOutputStream(os, size);
        }
        else
        {
            this.os = os;
        }
    }

    public void setOs(OutputStream os)
    {
        if (!(os instanceof BufferedOutputStream))
        {
            this.os = new BufferedOutputStream(os, pageSize);
        }
        else
        {
            this.os = os;
        }
    }
}
