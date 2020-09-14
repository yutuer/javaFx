package fasterLogger.v2;

import fasterLogger.IDataProvider;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/10 20:08
 * @Version 1.0
 */
public interface IDataProviderFactory
{
    IDataProvider createDataProvider();
}
