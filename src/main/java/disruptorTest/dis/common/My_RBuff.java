package disruptorTest.dis.common;

import disruptorTest.dis.My_Cursor;
import disruptorTest.dis.My_Sequenced;
import disruptorTest.dis.consume.My_EventProcessor;
import disruptorTest.dis.produce.My_EventFactory;

import java.util.List;

/**
 * @Description 事件对象共享对象
 * @Author zhangfan
 * @Date 2020/9/27 23:42
 * @Version 1.0
 */
public class My_RBuff<T> implements My_Cursor
{

    /**
     * buffer大小
     */
    private final int bufferSize;

    private Object[] datas;

    private My_EventFactory<T> eventFactory;

    /**
     * 用于计算消费者的索引
     */
    private My_Sequenced produceSeq;

    /**
     * 用于计算消费者的索引
     */
    private My_Sequenced consumeSeq;

    /**
     * 消费者集合
     */
    private List<My_EventProcessor> eventProcesses;

    public My_RBuff(My_EventFactory<T> eventFactory, My_Sequenced seq, int size)
    {
        this.eventFactory = eventFactory;
//        this.seq = seq;

        bufferSize = size - 1;
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

    @Override
    public long getCursor()
    {
        return 0;
    }
}
