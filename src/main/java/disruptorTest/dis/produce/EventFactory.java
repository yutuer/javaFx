package disruptorTest.dis.produce;

/**
 * @Description 事件工厂
 * @Author zhangfan
 * @Date 2020/9/28 8:29
 * @Version 1.0
 */
public interface EventFactory<T>
{
    T create();
}
