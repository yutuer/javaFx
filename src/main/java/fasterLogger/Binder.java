package fasterLogger;

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
public class Binder
{

    /**
     * 加入管理的doubleCache类
     */
    private List<IWriteTool> doubleCaches;

    private IWriteTool[] dcs;

    public Binder()
    {
        doubleCaches = new ArrayList<>();
    }

    /**
     * 注册,  之后会被数据读取到WriteBuffer中
     *
     * @param doubleCache
     */
    public void register(IWriteTool doubleCache)
    {
        synchronized (this)
        {
            // A
            doubleCaches.add(doubleCache);

            // 防止指令重排序.
            // 如果放在同步块外面, 如果先执行A 处的add, 但是后执行这行语句, 会导致dcs的结果数量不正确
            dcs = doubleCaches.toArray(new IWriteTool[0]);
        }
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
