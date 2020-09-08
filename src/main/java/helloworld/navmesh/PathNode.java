package helloworld.navmesh;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/8 20:21
 * @Version 1.0
 */
public class PathNode
{
    /**
     * 父引用
     */
    private PathNode parent;

    /**
     * 当前三角形索引
     */
    private int index;

    /**
     * 当前点
     */
    private VectorInt2 pos;

    /**
     * 目标点
     */
    private VectorInt2 target;

    /**
     * 之前的路径和
     */
    private double total;

    /**
     * 当前点的总和
     */
    private double calTotal;

    public PathNode(PathNode parent, int index, VectorInt2 pos, VectorInt2 target, double total)
    {
        this.parent = parent;
        this.index = index;
        this.pos = pos;
        this.target = target;
        this.total = total;
    }

    /**
     * f = g  + h
     *
     * @return
     */
    public double calCost()
    {
        calTotal = total + NavUtil.distanceDouble(pos, target);
        return calTotal;
    }


}
