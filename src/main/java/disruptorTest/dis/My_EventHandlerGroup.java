package disruptorTest.dis;

import disruptorTest.dis.consume.My_EventProcessor;

/**
 * 事件处理器组，作为Disruptor构成中的一部分。
 * <b>这个类很重要</b>
 * <p>
 * 用于组织消费者之间的依赖关系。
 * 建立消费者之间的依赖其实也就是建立消费者与前驱节点的Sequence之间的依赖
 * 它会建立依赖消费者之间的依赖，也就是Barrier中的dependentSequence的由来。
 * <p>
 * 这个类如果不研究一下，很多地方阅读可能有障碍。
 * <p>
 * A group of {@link My_EventProcessor}s used as part of the {@link My_Disruptor}.
 *
 * @Description
 * @Author zhangfan
 * @Date 2021/1/5 20:08
 * @Version 1.0
 */
public class My_EventHandlerGroup<T>
{
    /**
     * 方便回调，用于创建具有依赖关系的消费者
     * {@link My_Disruptor#createEventProcessors(LongForCacheLine[], My_EventHandler[])}
     * {@link My_Disruptor#createEventProcessors(LongForCacheLine[], My_EventProcessorFactory[])}
     */
    private final My_Disruptor<T> disruptor;

    public My_EventHandlerGroup(My_Disruptor<T> disruptor)
    {
        this.disruptor = disruptor;
    }
}
