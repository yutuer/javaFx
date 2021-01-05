package disruptorTest.dis;

import com.lmax.disruptor.DataProvider;

/**
 * @Description 事件序号生成器
 * @Author zhangfan
 * @Date 2021/1/5 16:00
 * @Version 1.0
 */
public interface My_EventSequencer<T> extends DataProvider<T>, My_Sequenced
{
}
