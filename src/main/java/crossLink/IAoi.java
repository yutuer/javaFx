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
    void removeNode(T node);

    /**
     * 节点移动到x, y
     *
     * @param node
     * @param x
     * @param y
     */
    void moveNode(T node, int x, int y);
}
