package fasterLogger.process;

/**
 * @Description 服务器  调度单位
 * @Author zhangfan
 * @Date 2020/9/7 17:16
 * @Version 1.0
 */
public interface IService
{
    /**
     * 单位时间的调用
     */
    void tick(int ticker);
}
