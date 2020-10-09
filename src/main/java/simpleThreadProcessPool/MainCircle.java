package simpleThreadProcessPool;


import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description 主循环
 * @Author zhangfan
 * @Date 2020/10/9 10:08
 * @Version 1.0
 */
public class MainCircle
{

    private SimpleProcessPool processPool;

    public MainCircle(SimpleProcessPool processPool)
    {
        this.processPool = processPool;
    }

    public void start()
    {
        Executors.newSingleThreadScheduledExecutor((r) ->
        {
            return new Thread(r, "MainCircle");
        }).scheduleAtFixedRate(() ->
        {
            try
            {
                SystemClock.updateTime(System.currentTimeMillis());

                // 可以把这段逻辑 放在processPool中 变成 ProcessPool.tick()
                processPool.tick();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }, 1, 1, TimeUnit.MILLISECONDS);
    }
}
