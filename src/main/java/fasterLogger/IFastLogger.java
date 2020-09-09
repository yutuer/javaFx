package fasterLogger;

/**
 * @Description 和应用程序的日志交互接口
 * @Author zhangfan
 * @Date 2020/9/9 9:44
 * @Version 1.0
 */
public interface IFastLogger
{

    void log(String msg, long actorId, String content);
}
