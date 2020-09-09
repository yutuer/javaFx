package fasterLogger.v1;

import fasterLogger.IDataProvider;
import fasterLogger.IFastLogger;
import fasterLogger.write.StringWriteSource;
import fasterLogger.write.WriterBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 双重缓冲区
 * @Author zhangfan
 * @Date 2020/9/7 10:47
 * @Version 1.0
 */
public class DoubleCache implements IFastLogger
{
    private List<IDataProvider>[] dataLists;

    private volatile int curIndex;

    private volatile int nextIndex;

    public DoubleCache()
    {
        dataLists = new List[2];
        for (int i = 0; i < 2; i++)
        {
            dataLists[i] = new ArrayList<>();
        }
        curIndex = 0;
        nextIndex = 1;
    }

    /**
     * 此方法会在buff处理线程中调用
     */
    private void exchange()
    {
        final int cur = curIndex;

        // 因为此方法会在buff处理线程中调用, 所以这里尝试了不加锁的策略.
        // 1. 必须先切换curIndex 到 NextIndex. 因为外部写线程会使用curIndex索引, 所以先让外部线程能获取到最新的空集合
        curIndex = nextIndex;
        // 2. 改变处理容器的索引. 因为是在同一个buff线程中的逻辑. 所以这里可以慢慢修改, 而不用担心导致并发
        nextIndex = cur;
    }

    /**
     * 给log日志线程使用
     *
     * @return
     */
    private List<IDataProvider> getDataAccept()
    {
        return dataLists[curIndex];
    }

    /**
     * 给buff线程使用
     *
     * @return
     */
    private List<IDataProvider> getBufferAccpet()
    {
        return dataLists[nextIndex];
    }

    /**
     * log 接口调用
     *
     * @param writeDataSource
     */
    public void write(IDataProvider writeDataSource)
    {
        getDataAccept().add(writeDataSource);
    }

    public void writeToBuffer(WriterBuffer writerBuffer)
    {
        if (writerBuffer == null)
        {
            throw new RuntimeException("没有writerBuffer");
        }

        exchange();

        final List<IDataProvider> bufferAccpet = getBufferAccpet();
        int size = bufferAccpet.size();
        for (int i = 0; i < size; i++)
        {
            IDataProvider iWriteDataSource = bufferAccpet.get(i);
            byte[] data = iWriteDataSource.getData();

            writerBuffer.write(data);
        }

        // 其实这里是有问题的. 其实应该删除 0-size长度的数据
        bufferAccpet.clear();
    }

    @Override
    public void log(String msg, long actorId, String content)
    {
        write(new StringWriteSource(msg, actorId));
    }
}
