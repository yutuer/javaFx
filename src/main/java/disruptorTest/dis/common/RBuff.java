package disruptorTest.dis.common;

import disruptorTest.dis.Seq;
import disruptorTest.dis.consume.EventProcess;
import disruptorTest.dis.produce.EventFactory;

import java.util.List;

/**
 * @Description 事件对象共享对象
 * @Author zhangfan
 * @Date 2020/9/27 23:42
 * @Version 1.0
 */
public class RBuff<T>
{

    /**
     * buffer大小
     */
    private final int bufferSize;

    private Object[] datas;

    private EventFactory<T> eventFactory;

    /**
     * 用于计算消费者的索引
     */
    private Seq seq;

    /**
     * 消费者集合
     */
    private List<EventProcess> eventProcesses;

    public RBuff(EventFactory<T> eventFactory, Seq seq, int size)
    {
        this.eventFactory = eventFactory;
        this.seq = seq;

        bufferSize = seq.bufferSize();

        datas = new Object[size];

        fill();
    }

    private void fill()
    {
        for (int i = 0; i < datas.length; i++)
        {
            datas[i] = eventFactory.create();
        }
    }

    public final T getElementAt(long index)
    {
        int i = (int) (index & bufferSize);
        return (T) datas[i];
    }
}
