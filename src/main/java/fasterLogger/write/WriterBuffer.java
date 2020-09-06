package fasterLogger.write;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

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

    private int size;

    public WriterBuffer(int size)
    {
        this.size = size;
    }

    /**
     * @return
     */
    private boolean isExceedFull(int length)
    {
//        return byteBuffer.limit() + length > byteBuffer.capacity();
        return true;
    }

    //    /**
//     * 将缓存写入输出流
//     *
//     * @param buffer
//     */
//    private void writeBufferToOutputSteam(ByteBuffer buffer)
//    {
//        byteBuffer.flip();
//        if (byteBuffer.limit() > 0)
//        {
//            writeToOutputStream(byteBuffer.array());
//        }
//        buffer.clear();
//    }

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

    /**
     * 此方法 如果多线程环境下, 需要 加上synchronized
     *
     * @param array
     */
    private void writeToOutputStream(byte[] array)
    {
        final OutputStream outputStream = this.os;
        if (outputStream != null)
        {
            try
            {
                os.write(array, 0, array.length);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void setOs(OutputStream os)
    {
        this.os = new BufferedOutputStream(os);
    }
}
