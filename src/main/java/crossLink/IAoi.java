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
     * 加入一个节点
     */
    void addNode(int x, int y);

    /**
     * 删除一个节点
     *
     * @param label
     */
    void removeNode(long label);

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
     * @param nodePos
     */
    void acceptDatas(int[] nodePos);

    T getNode(long label);

    int getXRange();

    int getYRange();

}
