package crossLink.aoi.cross;

/**
 * @Description 普通的用于索引的双向链表节点
 * @Author zhangfan
 * @Date 2020/9/27 11:31
 * @Version 1.0
 */
public class NormalIndexDoubleLinkNode
{
    public static final int X = 1;
    public static final int Y = 2;

    public int direction;
    public int index;

    public int pos;

    /**
     * 查找的第一个
     */
    CrossLinkNode first;
}
