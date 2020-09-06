package fasterLogger;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * @Description 输入日志和某一个RingBuff绑定输出绑定
 * 支持扩容
 * @Author zhangfan
 * @Date 2020/9/5 15:56
 * @Version 1.0
 */
public class InputOutputBinder<T>
{

    private static InputOutputBinder instance;

    static
    {
        instance = create(16);
    }

    /**
     * ringBuffer库
     */
    private Queue<RingBuffer<T>> backupRingBuffers;

    /**
     * 跟踪处理的RingBuffer
     */
    private List<RingBuffer<T>> tracedRingBuffer;

    private int capacity;

    public static <T> InputOutputBinder<T> create(int capacity)
    {
        return new InputOutputBinder<>(capacity);
    }

    private InputOutputBinder(int capacity)
    {
        this.capacity = capacity;
        backupRingBuffers = new ArrayDeque<>(capacity * 2);
        for (int i = 0; i < capacity; i++)
        {
            backupRingBuffers.add(new RingBuffer<>(1 << 12)); //4k page
        }
    }

    private RingBuffer<T> poll()
    {
        RingBuffer poll = instance.poll();
        if (poll == null)
        {
            for (int i = 0; i < capacity; i++)
            {
                backupRingBuffers.add(new RingBuffer<>(1 << 12)); //4k page
            }
        }
        return instance.poll();
    }

    public static void bind(ThreadLocal tl)
    {
        synchronized (instance)
        {
            RingBuffer poll = instance.poll();
            instance.tracedRingBuffer.add(poll);

            tl.set(poll);
        }
    }
}
