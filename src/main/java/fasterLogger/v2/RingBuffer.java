package fasterLogger.v2;

import fasterLogger.IDataProvider;
import fasterLogger.IWriteTool;
import fasterLogger.write.WriterBuffer;
import sun.misc.Contended;
import util.Log;
import util.Util;

/**
 * @Description 环形数组buffer
 * @Author zhangfan
 * @Date 2020/9/5 11:00
 * @Version 1.0
 */
public class RingBuffer<T extends IDataProvider> implements IWriteTool
{
    @Contended
    private volatile long readIndex;

    @Contended
    private volatile long writeIndex;

    /**
     * ringBuffer 容量
     */
    private final int capacity;

    /**
     * 装载事件源的容器
     */
    private Object[] logEvents;

    public RingBuffer(IDataProviderFactory factory, int capacity)
    {
        capacity = Util.intToMaxTowPower(capacity);
        this.capacity = capacity;
        logEvents = new Object[capacity];

        fillLogEvents(factory);
    }

    private void fillLogEvents(IDataProviderFactory factory)
    {
        for (int i = 0; i < this.capacity; i++)
        {
            logEvents[i] = factory.createDataProvider();
        }
    }

    /**
     * 是否可写
     *
     * @return
     */
    public boolean canWrite()
    {
        return writeIndex - capacity < readIndex;
    }

    /**
     * 是否可读
     *
     * @return
     */
    private boolean canRead()
    {
        return readIndex < writeIndex;
    }

    private T getAtElement(int pos)
    {
        return (T) logEvents[pos];
    }

    /**
     * 添加
     *
     * @param
     * @return
     */
    public boolean add(String msg, long actorId, String content)
    {
        if (!canWrite())
        {
            Log.logger.info("cant Write");
            return false;
        }

        final int writePos = (int) (writeIndex & (capacity - 1));
        writeIndex++;

        T t = getAtElement(writePos);
        t.wrap(msg, actorId, content);

        return true;
    }

    /**
     * 获取
     *
     * @return
     */
    public T get()
    {
        if (!canRead())
        {
            Log.logger.info("cant Read");
            return null;
        }

        final int readPos = (int) (readIndex & (capacity - 1));
        final T t = getAtElement(readPos);
        readIndex++;
        return t;
    }

    @Override
    public boolean write(String msg, long actorId, String content)
    {
        return add(msg, actorId, content);
    }

    @Override
    public void writeToBuffer(WriterBuffer writerBuffer)
    {
        T t;

        while ((t = get()) != null)
        {
            byte[] bytes = t.provideData();
            writerBuffer.write(bytes);
        }

        writerBuffer.flush();
    }
}
