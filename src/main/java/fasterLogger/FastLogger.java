package fasterLogger;

/**
 * @Description 日志记录. 对应用程序的接口
 * 当输入过快的时候
 * @Author zhangfan
 * @Date 2020/9/5 15:58
 * @Version 1.0
 */
public class FastLogger implements IFastLogger
{
    private ThreadLocal<IFastLogger> tl = new ThreadLocal();

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
        IFastLogger fastLoggerProxy = tl.get();
        if (fastLoggerProxy == null)
        {
            fastLoggerProxy = InputOutputBinder.bind(tl);
        }
        fastLoggerProxy.log(msg, actorId, content);

//        IFastLogger.write(new StringWriteSource(msg, actorId));
    }

}
