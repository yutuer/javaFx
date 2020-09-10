package fasterLogger;

/**
 * @Description
 * @Author zhangfan
 * @Date 2020/9/7 11:11
 * @Version 1.0
 */
public interface IFastLoggerFactory
{
    /**
     * 获取日志对象
     *
     * @return
     */
    IFastLogger getFastLogger();
}
