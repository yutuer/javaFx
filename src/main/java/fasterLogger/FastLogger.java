package fasterLogger;

import fasterLogger.write.StringWriteSource;

/**
 * @Description 日志记录
 * 当输入过快的时候
 * @Author zhangfan
 * @Date 2020/9/5 15:58
 * @Version 1.0
 */
public class FastLogger
{
    private ThreadLocal<RingBuffer<StringWriteSource>> tl = new ThreadLocal();

    public void log(String msg, Object p0, Object p1)
    {

    }

    private void log(String msg, long actorId, String content)
    {
        RingBuffer<StringWriteSource> ringBuffer = tl.get();
        if(ringBuffer == null)
        {
            InputOutputBinder.bind(tl);
        }
        ringBuffer.add(new StringWriteSource());
    }

}
