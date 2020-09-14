package fasterLogger;

import fasterLogger.write.WriterBuffer;

/**
 * @Description 负责接受写入值的工具
 * @Author zhangfan
 * @Date 2020/9/10 18:57
 * @Version 1.0
 */
public interface IWriteTool
{

    /**
     * 写入数据
     *
     * @return
     */
    boolean write(String msg, long actorId, String content);

    /**
     * 将数据写入buffer
     *
     * @param writerBuffer
     */
    void writeToBuffer(WriterBuffer writerBuffer);

}
