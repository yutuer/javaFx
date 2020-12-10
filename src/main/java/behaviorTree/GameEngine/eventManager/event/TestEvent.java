package behaviorTree.GameEngine.eventManager.event;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/12/9 11:22
 * @Version 1.0
 */
public class TestEvent extends Event
{
    private String name;

    public TestEvent(String name)
    {
        super();
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
