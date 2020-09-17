package crossLink.comparator;

import crossLink.aoi.cross.CrossLinkNode;

import java.util.Comparator;

/**
 * @Description x比较器
 * @Author zhangfan
 * @Date 2020/9/15 17:38
 * @Version 1.0
 */
public class XNodeComparator<T extends CrossLinkNode> implements Comparator<T>
{
    @Override
    public int compare(T o1, T o2)
    {
        return o1.x - o2.x;
    }
}
