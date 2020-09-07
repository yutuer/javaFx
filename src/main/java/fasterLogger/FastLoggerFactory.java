package fasterLogger;

/**
 * @Description
 * @Author zhangfan
 * @Date 2020/9/7 11:11
 * @Version 1.0
 */
public class FastLoggerFactory
{

    public static FastLogger getLogger(String name)
    {
        return new FastLogger(name);
    }

}
