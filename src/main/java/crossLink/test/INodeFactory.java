package crossLink.test;

import crossLink.aoi.BaseNode;

/**
 * @Description 节点工厂
 * @Author zhangfan
 * @Date 2020/9/18 11:29
 * @Version 1.0
 */
public interface INodeFactory<T extends BaseNode>
{
    /**
     * 创建节点
     *
     * @param x
     * @param y
     * @return
     */
    T create(int x, int y);
}
