package disruptorTest.dis;

public enum My_ProducerType
{
    /**
     * Create a RingBuffer with a single event publisher to the RingBuffer
     */
    SINGLE,

    /**
     * Create a RingBuffer supporting multiple event publishers to the one RingBuffer
     * 单生产者和多生产的主要差别在空间分配上(序号分配上)。
     * 在Disruptor下，其实都是多消费者模式，并没有针对单消费者的优化。
     */
    MULTI
}
