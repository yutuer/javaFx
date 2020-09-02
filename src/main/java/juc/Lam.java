package juc;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/8/6 14:18
 * @Version 1.0
 */
public class Lam
{
    public void a()
    {
        Runnable r = () ->
        {
            System.out.println(1);
        };

        r.run();
    }
}
