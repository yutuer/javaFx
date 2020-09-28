package disruptorTest.dis.consume;

import disruptorTest.dis.Seq;

/**
 * @Description 事件处理, 放在线程中
 * @Author zhangfan
 * @Date 2020/9/28 9:51
 * @Version 1.0
 */
public interface EventProcess extends Seq
{

    /**
     * 不停的next(), eventProcess()
     */
    void eventProcess();

}
