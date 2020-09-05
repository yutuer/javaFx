package fasterLogger;

/**
 * @Description 事件容器
 * @Author zhangfan
 * @Date 2020/9/5 11:01
 * @Version 1.0
 */
public class EventContainer<T>
{
    /**
     * 事件源
     */
    private T source;

    public T getSource()
    {
        return source;
    }

    public void setSource(T source)
    {
        this.source = source;
    }
}
