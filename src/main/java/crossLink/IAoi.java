package crossLink;

import crossLink.aoi.BaseNode;

/**
 * @Description aoi模块 统一接口
 * @Author zhangfan
 * @Date 2020/9/17 11:07
 * @Version 1.0
 */
public interface IAoi<T extends BaseNode>
{
    /**
     * 加入一个节点
     *
     * @param node
     */
    void addNode(T node);

    /**
     * 删除一个节点
     *
     * @param node
     */
    void removeNode(long node);

    /**
     * 节点移动到x, y
     *
     * @param node
     * @param x
     * @param y
     */
    void moveNode(T node, int x, int y);

    /**
     * 接受一组节点, 用来初始化
     *
     * @param nodes
     */
    default void acceptDatas(T[] nodes)
    {
        for (int i = 0, size = nodes.length; i < size; i++)
        {
            addNode(nodes[i]);
        }
    }
}
