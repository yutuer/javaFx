package fasterLogger;

import sun.misc.Contended;
import util.Util;

/**
 * @Description 环形数组buffer
 * @Author zhangfan
 * @Date 2020/9/5 11:00
 * @Version 1.0
 */
public class RingBuffer<T>
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
    private EventContainer<T>[] eventContainers;

    public RingBuffer(int capacity)
    {
        capacity = Util.intToMaxTowPower(capacity);
        this.capacity = capacity;
        eventContainers = new EventContainer[capacity];

        fillEventContainers(capacity);
    }

    private void fillEventContainers(int capacity)
    {
        for (int i = 0; i < capacity; i++)
        {
            eventContainers[i] = new EventContainer();
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

    /**
     * 添加
     *
     * @param t
     * @return
     */
    public boolean add(T t)
    {
        if (!canWrite())
        {
            return false;
        }

        final int writePos = (int) (writeIndex & (capacity - 1));
        eventContainers[writePos].setSource(t);
        writeIndex++;
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
            return null;
        }

        final int readPos = (int) (readIndex & (capacity - 1));
        final T t = eventContainers[readPos].getSource();
        readIndex++;
        return t;
    }


}
