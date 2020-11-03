package simpleThreadProcessPool.service;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/10/9 14:12
 * @Version 1.0
 */
public class TestService extends AbstractService
{
    private int tag;

    public TestService(int tag)
    {
        this.tag = tag;
    }

    @Override
    public void init()
    {
        System.out.printf("TestService%d init\n", tag);
    }

    @Override
    public void tick(int inteval)
    {
        System.out.println(tag + "  " + inteval);
    }

    @Override
    public void destory()
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }
}
