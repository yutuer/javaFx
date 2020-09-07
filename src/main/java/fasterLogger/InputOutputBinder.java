package fasterLogger;

import fasterLogger.v1.DoubleCache;
import fasterLogger.write.WriterBuffer;

import java.util.ArrayList;
import java.util.List;

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
        instance = create();
    }

    public static InputOutputBinder getInstance()
    {
        return instance;
    }

    /**
     * 加入管理的doubleCache类
     */
    private List<DoubleCache> doubleCaches;

    private volatile DoubleCache[] dcs;

    public static <T> InputOutputBinder<T> create()
    {
        return new InputOutputBinder<>();
    }

    private InputOutputBinder()
    {
        doubleCaches = new ArrayList<>();
    }

    public static void bind(ThreadLocal tl)
    {
        DoubleCache doubleCache = new DoubleCache();

        InputOutputBinder instance = getInstance();
        instance.add(doubleCache);

        tl.set(doubleCache);
    }

    private void add(DoubleCache doubleCache)
    {
        synchronized (this)
        {
            doubleCaches.add(doubleCache);
        }
        dcs = doubleCaches.toArray(new DoubleCache[0]);
    }

    /**
     * 给输出线程使用
     *
     * @param writerBuffer
     */
    public void writeToBufferAndFlush(WriterBuffer writerBuffer)
    {
        if (dcs != null)
        {
            for (int i = 0; i < dcs.length; i++)
            {
                dcs[i].writeToBuffer(writerBuffer);
            }
            writerBuffer.flush();
        }
    }
}
