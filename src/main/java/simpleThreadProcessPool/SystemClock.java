package simpleThreadProcessPool;

/**
 * @Description 系统统一时钟
 * @Author zhangfan
 * @Date 2020/10/9 12:28
 * @Version 1.0
 */
public class SystemClock
{

    private static volatile long Now = System.currentTimeMillis();

    public static void updateTime(long now)
    {
        Now = now;
    }

    public static long getSystemClock()
    {
        return Now;
    }
}
