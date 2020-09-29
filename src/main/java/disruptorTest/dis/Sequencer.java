package disruptorTest.dis;

import disruptorTest.dis.Seqer;

/**
 * @Description 序号产生器
 * 计数调度器超类, 环形数组原型
 * @Author zhangfan
 * @Date 2020/9/29 7:40
 * @Version 1.0
 */
public abstract class Sequencer implements Seqer
{
    // 一个数组大小, 用来获取下标. 所以是len-1
    protected int buffSize;

    public Sequencer(int buffSize)
    {
        this.buffSize = buffSize;
    }

}
