package crossLink.aoi.cross;

/**
 * @Description 普通的用于索引的跳点
 *
 * 会和某一个节点 建立互相之间的引用
 *
 * @Author zhangfan
 * @Date 2020/9/27 11:31
 * @Version 1.0
 */
public class NormalIndexSkipNode
{
    public static final int X = 1;
    public static final int Y = 2;

    /**
     * 方向
     */
    public int direction;

    /**
     * 索引
     */
    public int index;

    /**
     * 在格子轴上的位置(= index * scale)
     */
    public int pos;

    /**
     * 查找的第一个
     */
    CrossLinkNode first;
}
