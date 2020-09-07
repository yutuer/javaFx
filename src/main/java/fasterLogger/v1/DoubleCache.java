package fasterLogger.v1;

import fasterLogger.write.IWriteDataSource;
import fasterLogger.write.WriterBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 双重缓冲区
 * @Author zhangfan
 * @Date 2020/9/7 10:47
 * @Version 1.0
 */
public class DoubleCache
{
    private List<IWriteDataSource>[] dataLists;

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
    private List<IWriteDataSource> getDataAccept()
    {
        return dataLists[curIndex];
    }

    /**
     * 给buff线程使用
     *
     * @return
     */
    private List<IWriteDataSource> getBufferAccpet()
    {
        return dataLists[nextIndex];
    }

    /**
     * log 接口调用
     *
     * @param writeDataSource
     */
    public void write(IWriteDataSource writeDataSource)
    {
        getDataAccept().add(writeDataSource);
    }

    public void writeToBuffer(WriterBuffer writerBuffer)
    {
        exchange();

        final List<IWriteDataSource> bufferAccpet = getBufferAccpet();
        for (int i = 0, size = bufferAccpet.size(); i < size; i++)
        {
            IWriteDataSource iWriteDataSource = bufferAccpet.get(i);
            byte[] data = iWriteDataSource.getData();

            writerBuffer.write(data);
        }
        bufferAccpet.clear();
    }

}
