package disruptorTest.dis;

import disruptorTest.dis.consume.WaitStrategy;

/**
 * @Description 序号产生器
 * <p>
 * 计数调度器超类, 环形数组原型.  数组的约束用此类实现
 * @Author zhangfan
 * @Date 2020/9/29 7:40
 * @Version 1.0
 */
public abstract class MySequencer implements MySequenced
{
    // 一个数组大小, 用来获取下标. 所以是len-1
    protected int buffSize;

    /**
     * 生产者的序列，表示生产者的进度。
     * PS: 代码里面的带cursor的都表示生产者们的Sequence。
     * <p>
     * 消费者与生产者之间的交互（可见性保证）是通过volatile变量的读写来保证的。
     * 当前的序号
     */
    protected final Seq cursor = new Seq(Seq.INITIAL_VALUE);

    /**
     * 网关Sequences，序号生成器必须和这些Sequence满足约束:
     * cursor-bufferSize <= Min(gatingSequence)
     * 即：所有的gatingSequences让出下一个插槽后，生产者才能获取该插槽。
     */
    protected volatile Seq[] gatingSequences = new Seq[0];

    /**
     * 等待策略
     */
    protected WaitStrategy waitStrategy;

    public MySequencer(int buffSize, WaitStrategy waitStrategy)
    {
        this.buffSize = buffSize;
        this.waitStrategy = waitStrategy;
    }

}
