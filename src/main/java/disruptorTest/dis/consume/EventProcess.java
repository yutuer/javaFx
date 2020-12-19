package disruptorTest.dis.consume;

import disruptorTest.dis.MySequenced;

/**
 * @Description 事件处理, 放在线程中
 * @Author zhangfan
 * @Date 2020/9/28 9:51
 * @Version 1.0
 */
public interface EventProcess extends MySequenced
{

    /**
     * 不停的next(), eventProcess()
     */
    void eventProcess();

}
