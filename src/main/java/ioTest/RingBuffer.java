package ioTest;

import sun.misc.Contended;

/**
 * @Description 环形数组buffer
 * @Author zhangfan
 * @Date 2020/9/5 11:00
 * @Version 1.0
 */
public class RingBuffer<T>
{
    @Contended
    private volatile int readIndex;

    @Contended
    private volatile int writeIndex;

    private EventContainer<T>[] eventContainers;

    public RingBuffer(int capacity)
    {
        capacity = capacity;

        eventContainers = new EventContainer[capacity];
    }


}
