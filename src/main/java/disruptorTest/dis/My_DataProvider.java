package disruptorTest.dis;

import disruptorTest.dis.produce.My_RingBuffer;

/**
 * @Description 数据提供者，目前就是 {@link My_RingBuffer}
 * @Author zhangfan
 * @Date 2021/1/5 15:58
 * @Version 1.0
 */
public interface My_DataProvider<T>
{
    /**
     * 根据指定序号获取data
     */
    T get(long sequence);
}
