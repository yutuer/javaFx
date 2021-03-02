package disruptorTest.dis;

import java.util.concurrent.Executor;

/**
 * 一个消费者信息的抽象，
 *
 * @Author zhangfan
 * @Date 2021/1/5 20:59
 * @Version 1.0
 */
public interface My_ConsumerInfo
{
    My_Sequencer[] getSequences();

    void start(Executor executor);
}
