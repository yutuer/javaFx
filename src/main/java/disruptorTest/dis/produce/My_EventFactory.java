package disruptorTest.dis.produce;

import com.lmax.disruptor.RingBuffer;

/**
 * @Description 事件工厂
 * RingBuffer使用{@link #newInstance()}预创建对象预填充共享数据区。
 * 创建的实际是数据的载体对象，载体对象是反复使用的，会预分配，因此存在短时间的内存泄漏风险，
 * 因此讲道理最好每次处理完之后将数据进行清理，以帮助垃圾回收。(这里的T可以继承一个带clear()方法的接口)
 * <p>
 * * Called by the {@link RingBuffer} to pre-populate all the events to fill the RingBuffer.
 * * @param <T> event implementation storing the data for sharing during exchange or parallel coordination of an event.
 * @Author zhangfan
 * @Date 2020/9/28 8:29
 * @Version 1.0
 */
public interface My_EventFactory<T>
{
    T newInstance();
}
