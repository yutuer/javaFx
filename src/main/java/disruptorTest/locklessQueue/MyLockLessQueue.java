package disruptorTest.locklessQueue;

import disruptorTest.ICreateTemplateArray;

/**
 * @Description 单线程读, 单线程写 交互的Queue
 * @Author zhangfan
 * @Date 2020/11/20 15:08
 * @Version 1.0
 */
public class MyLockLessQueue<T>
{

    private final int capacity;
    private final int mod;

    private volatile long readIndex;
    private volatile long writeIndex;

    private long readIndexCache;
    private long writeIndexCache;

    /**
     * 元素数组
     */
    private T[] elements;

    public MyLockLessQueue(int capacity, ICreateTemplateArray<T> func)
    {
        readIndex = -1;
        writeIndex = -1;

        readIndexCache = readIndex;
        writeIndexCache = writeIndex;

        if ((capacity & (capacity - 1)) != 0)
        {
            capacity = Integer.MIN_VALUE >>> (Integer.numberOfLeadingZeros(capacity) - 1);
        }

        this.capacity = capacity;
        this.mod = capacity - 1;

        elements = func.create(capacity);
    }

    public boolean offer(T t)
    {
        long nextWriteIndexCache = writeIndexCache + 1;
        if (writeFull(nextWriteIndexCache))
        {
            // 写满了, 试试写入最新的缓存
            readIndex = readIndexCache; // 写入volatile值

            if (writeFull(nextWriteIndexCache))
            {
                return false;
            }
        }

        writeIndexCache = nextWriteIndexCache;

        int index = (int) (nextWriteIndexCache & mod);
        elements[index] = t;
        return true;
    }

    private boolean writeFull(long nextWriteIndexCache)
    {
        return nextWriteIndexCache - readIndex > capacity;
    }

    public T poll()
    {
        long nextReadIndexCache = readIndexCache + 1;
        if (readFull(nextReadIndexCache))
        {
            // 读取满了, 写入最新的值
            writeIndex = writeIndexCache;

            if (readFull(nextReadIndexCache))
            {
                return null;
            }
        }

        readIndexCache = nextReadIndexCache;

        int index = (int) (nextReadIndexCache & mod);
        return elements[index];
    }

    private boolean readFull(long nextReadIndexCache)
    {
        return nextReadIndexCache > writeIndex;
    }

    public static void main(String args[])
    {
        MyLockLessQueue queue = new MyLockLessQueue(2, (capacity) -> new Integer[capacity]);

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        System.out.println(queue.poll());
        queue.offer(4);
        System.out.println(queue.poll());
        queue.offer(5);
        System.out.println(queue.poll());
    }
}
