package disruptorTest.dis.produce;

import com.lmax.disruptor.InsufficientCapacityException;
import disruptorTest.dis.*;
import disruptorTest.dis.consume.My_WaitStrategy;
import sun.misc.Unsafe;

/**
 * @Description 承上启下
 * @Author zhangfan
 * @Date 2020/11/27 20:39
 * @Version 1.0
 */
public final class My_RingBuffer<E> implements My_Cursor, My_EventSequencer<E>
{
    // 数组填充大小，避免数组的有效元素出现伪共享
    // 数组的前X个元素会出现伪共享和后X个元素可能会出现伪共享,可能和无关数据加载到同一个缓存行。
    // （更多伪共享信息请查阅资料）
    private static final int BUFFER_PAD;
    // 引用对象数组的单个元素的地址移位量(用移位运算代替乘法)
    private static final int REF_ELEMENT_SHIFT;
    // 引用对象数组有效首元素地址偏移量(这里因为进行了填充，所以不是真正的首元素的地址偏移量)
    private static final long REF_ARRAY_BASE;
    private static final Unsafe UNSAFE = My_Util.getUnsafe();
    /**
     * 索引掩码，表示后X位是有效数字(截断)。位运算代替取余快速计算插槽索引
     */
    private final long indexMask;
    /**
     * 缓存有效空间大小(必须是2的整次幂，-1就是掩码)
     */
    protected final int bufferSize;

    /**
     * 一个 既有生产者功能, 又能接入消费者的接口
     */
    private My_Sequencer sequencer;

    /**
     * 事件对象数组，大于真正需要的容量，采用了缓存行填充减少伪共享。
     */
    private Object[] entries;

    static
    {
        final int scale = UNSAFE.arrayIndexScale(Object[].class);
        if (4 == scale)
        {
            REF_ELEMENT_SHIFT = 2;
        }
        else if (8 == scale)
        {
            REF_ELEMENT_SHIFT = 3;
        }
        else
        {
            throw new IllegalStateException("Unknown pointer size");
        }
        // 这里前后各填充了128字节，和Disruptor的其它设计并不一致，其它地方都是64字节，当然128兼容64
        BUFFER_PAD = 128 / scale;
        // Q: 这里是什么意思？
        // A: 因为数组的前后我们都进行了填充，以避免有效载荷和其它成员产生伪共享，
        // 因此我们需要在数组的起始偏移量上，再加上我们填充的那一段，才是我们有效载荷的起始偏移量
        // Including the buffer pad in the array base offset
        REF_ARRAY_BASE = UNSAFE.arrayBaseOffset(Object[].class) + (BUFFER_PAD << REF_ELEMENT_SHIFT);
    }

    My_RingBuffer(My_EventFactory eventFactory, My_Sequencer sequencer)
    {
        this.sequencer = sequencer;
        this.bufferSize = sequencer.getBufferSize();
        if (bufferSize < 1)
        {
            throw new IllegalArgumentException("bufferSize must not be less than 1");
        }
        if (Integer.bitCount(bufferSize) != 1)
        {
            throw new IllegalArgumentException("bufferSize must be a power of 2");
        }

        // 掩码
        this.indexMask = bufferSize - 1;
        // 额外创建 2个填充空间的大小，首尾填充，避免数组的有效载荷和其它成员加载到同一缓存行。
        entries = new Object[bufferSize + 2 * BUFFER_PAD];

        fill(eventFactory);
    }

    private void fill(My_EventFactory eventFactory)
    {
        for (int i = 0; i < bufferSize; i++)
        {
            // BUFFER_PAD + i为真正的数组索引
            entries[BUFFER_PAD + i] = eventFactory.newInstance();
        }
    }

    public static <E> My_RingBuffer<E> createSingleProducer(My_EventFactory eventFactory, int bufferSize,
                                                            My_WaitStrategy myWaitStrategy)
    {
        My_Sequencer sequencer = new My_SingleThreadSequencer(bufferSize, myWaitStrategy);
        return new My_RingBuffer(eventFactory, sequencer);
    }

    public static <E> My_RingBuffer<E> create(final My_ProducerType producerType, My_EventFactory eventFactory, int bufferSize,
                                              My_WaitStrategy myWaitStrategy)
    {
        My_Sequencer sequencer;
        if (producerType == My_ProducerType.SINGLE)
        {
            sequencer = new My_SingleThreadSequencer(bufferSize, myWaitStrategy);
        }
        else if (producerType == My_ProducerType.MULTI)
        {
            sequencer = new My_SingleThreadSequencer(bufferSize, myWaitStrategy);
        }
        return new My_RingBuffer(eventFactory, sequencer);
    }

    @Override
    public long getCursor()
    {
        return sequencer.getCursor();
    }

    @Override
    public E get(long sequence)
    {
        return null;
    }

    @Override
    public int getBufferSize()
    {
        return sequencer.getBufferSize();
    }

    @Override
    public long next()
    {
        return sequencer.next();
    }

    @Override
    public long tryNext() throws InsufficientCapacityException
    {
        return sequencer.tryNext();
    }

    @Override
    public long tryNext(int n) throws InsufficientCapacityException
    {
        return sequencer.tryNext(n);
    }

    @Override
    public long remainingCapacity()
    {
        return sequencer.remainingCapacity();
    }

    @Override
    public void publish(long sequence)
    {
        sequencer.publish(sequence);
    }

    @Override
    public void publish(long lo, long hi)
    {
        sequencer.publish(lo, hi);
    }
}
