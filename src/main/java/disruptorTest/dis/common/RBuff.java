package disruptorTest.dis.common;

import disruptorTest.dis.Seq;
import disruptorTest.dis.Sequence;
import disruptorTest.dis.produce.EventFactory;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/27 23:42
 * @Version 1.0
 */
public class RBuff<T>
{

    private T[] datas;

    private EventFactory eventFactory;

    private Seq seq;

    public RBuff(EventFactory eventFactory, Seq seq, int size)
    {
        this.eventFactory = eventFactory;
        this.seq = seq;

        datas = (T[]) new Object[size];
    }

    private void fill()
    {

    }
}
