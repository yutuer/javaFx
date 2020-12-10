package behaviorTree.GameEngine.eventManager.event;

/**
 * @Description 事件基础类. 这里应该有发送者的引用(但是为了不内存泄漏, 最好使用id来引用, 使用的时候获取即可)
 * @Author zhangfan
 * @Date 2020/12/9 11:18
 * @Version 1.0
 */
public class Event
{
    private static int EventCounter;

    /**
     * 事件id
     */
    private final int eventId;

    public Event()
    {
        EventCounter++;
        eventId = EventCounter;
    }

    public int getEventId()
    {
        return eventId;
    }

}
