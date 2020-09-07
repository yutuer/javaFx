package fasterLogger;

import fasterLogger.v1.DoubleCache;
import fasterLogger.write.StringWriteSource;

/**
 * @Description 日志记录
 * 当输入过快的时候
 * @Author zhangfan
 * @Date 2020/9/5 15:58
 * @Version 1.0
 */
public class FastLogger
{
    private ThreadLocal<DoubleCache> tl = new ThreadLocal();

    /**
     * 此log的标识
     */
    private String name;

    public FastLogger(String name)
    {
        this.name = name;
    }

    public void log(String msg, Object p0, Object p1)
    {

    }

    /**
     * 特定字符的log
     *
     * @param msg
     * @param actorId
     * @param content
     */
    public void log(String msg, long actorId, String content)
    {
        DoubleCache doubleCache = tl.get();
        if (doubleCache == null)
        {
            InputOutputBinder.bind(tl);
            doubleCache = tl.get();
        }
        doubleCache.write(new StringWriteSource(msg));
    }

}
