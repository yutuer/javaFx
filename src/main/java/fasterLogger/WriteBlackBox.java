package fasterLogger;

import fasterLogger.v1.DoubleCache;
import fasterLogger.write.StringDataProvider;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/10 14:35
 * @Version 1.0
 */
public class WriteBlackBox implements IWriteBlackBox
{
    private ThreadLocal<DoubleCache> tl = new ThreadLocal();

    /**
     * 工厂
     */
    private IFastLoggerFactory fastLoggerFactory;

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
        DoubleCache doubleCache = tl.get();
        if (doubleCache == null)
        {
//            fastLogger = fastLoggerFactory.getFastLogger();

            doubleCache = new DoubleCache();

            binder.register(doubleCache);
            tl.set(doubleCache);
        }
        doubleCache.write(new StringDataProvider(msg, actorId));
    }
}
