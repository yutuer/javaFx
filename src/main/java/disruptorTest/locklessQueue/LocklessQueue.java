package disruptorTest.locklessQueue;

import disruptorTest.ICreateTemplateArray;

/**
 * 无锁队列
 * Created by wangqiang on 2018/6/21.
 */
public class LocklessQueue<T>
{
    /**
     * 写索引
     */
    private volatile long write;

    /**
     * 读索引
     */
    private volatile long read;

    /**
     * 元素数组
     */
    private T[] elements;

    /**
     * 容量
     */
    private int capacity;

    /**
     * 容量减一,用于计算缓存
     */
    private int modeNumber;

    public LocklessQueue(int capacity, ICreateTemplateArray<T> func)
    {
        write = 0;
        read = 0;

        if ((capacity & (capacity - 1)) != 0)
        {
            capacity = Integer.MIN_VALUE >>> (Integer.numberOfLeadingZeros(capacity) - 1);
        }

        this.capacity = capacity;
        elements = func.create(capacity);
        modeNumber = this.capacity - 1;
    }

    /**
     * 添加一个元素
     *
     * @param t
     */
    public boolean offer(T t)
    {
        long pos = (write & modeNumber);
        if ((pos == (read & modeNumber)) && (read != write))
        {
            // 已满
            return false;
        }

        elements[(int) pos] = t;
        write++;
        return true;
    }

    /**
     * 取出一个元素
     *
     * @return
     */
    public T poll()
    {
        if (isEmpty())
        {
            return null;
        }

        int pos = (int) (read & modeNumber);
        T result = elements[pos];
        elements[pos] = null;
        read++;
        return result;
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty()
    {
        return write == read;
    }

    public int size()
    {
        return (int) (write - read);
    }

    public static void main(String args[])
    {
        LocklessQueue queue = new LocklessQueue(2, (capacity) -> new Integer[capacity]);

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
