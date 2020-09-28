package fasterLogger;

import fasterLogger.v2.IDataProviderFactory;
import fasterLogger.v2.LoggerRingBuffer;
import fasterLogger.write.StringDataProvider;

/**
 * @Description 负责构造并转发给真正处理数据的类
 * @Author zhangfan
 * @Date 2020/9/10 14:35
 * @Version 1.0
 */
public class WriteBlackBox implements IWriteBlackBox
{
    private ThreadLocal<IWriteUnit> tl = new ThreadLocal();

    /**
     * 绑定器
     */
    private Binder binder;

    public WriteBlackBox(Binder binder)
    {
        this.binder = binder;
    }

    @Override
    public void log(String msg, long actorId, String content)
    {
        IWriteUnit doubleCache = tl.get();
        if (doubleCache == null)
        {
            doubleCache = new LoggerRingBuffer(new IDataProviderFactory()
            {
                @Override
                public IDataProvider createDataProvider()
                {
                    return new StringDataProvider();
                }
            }, 1 << 10);
            binder.register(doubleCache);

            tl.set(doubleCache);
        }

        doubleCache.write(msg, actorId, content);
    }
}
