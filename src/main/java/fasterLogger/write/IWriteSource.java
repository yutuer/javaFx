package fasterLogger.write;

/**
 * @Description 写数据来源 接口
 * @Author zhangfan
 * @Date 2020/9/4 12:56
 * @Version 1.0
 */
public interface IWriteSource<T>
{
    /**
     * 获取来源
     *
     * @return
     */
    T getSource();
}
