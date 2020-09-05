package ioTest.write;

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
     * 固定长度
     */
    private ByteBuffer byteBuffer;

    /**
     * 这里os是 volatile的
     */
    private volatile OutputStream os;

    public WriterBuffer(ByteBuffer buffer)
    {
        byteBuffer = buffer;
    }

    /**
     * @return
     */
    private boolean isExceedFull(int length)
    {
        return byteBuffer.limit() + length > byteBuffer.capacity();
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

        if (isExceedFull(data.length))
        {
            // 如果满了, 要首先flush
            flush();
        }

        // 再写入
        byteBuffer.put(data);
    }

    /**
     * 刷新到存储设备, 外部调用接口
     */
    public void flush()
    {
        final OutputStream outputStream = this.os;
        if (outputStream != null)
        {
            writeBufferToOutputSteam(byteBuffer);
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
     * 将缓存写入输出流
     *
     * @param buffer
     */
    private void writeBufferToOutputSteam(ByteBuffer buffer)
    {
        byteBuffer.flip();
        if (byteBuffer.limit() > 0)
        {
            writeToOutputStream(byteBuffer.array());
        }
        buffer.clear();
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
        this.os = os;
    }
}
