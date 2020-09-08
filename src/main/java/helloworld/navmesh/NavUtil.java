package helloworld.navmesh;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/8 20:29
 * @Version 1.0
 */
public class NavUtil
{

    /**
     * 是否在同一个三角形内
     *
     * @param p0
     * @param p1
     * @return
     */
    public static boolean isInSameTrigger(VectorInt2 p0, VectorInt2 p1)
    {
        return false;
    }

    /**
     * 两个点距离的平方和(未开方)
     *
     * @param p0
     * @param p1
     * @return
     */
    public static double distanceDouble(VectorInt2 p0, VectorInt2 p1)
    {
        return multiDouble(p0.x - p1.x) + multiDouble(p0.z - p1.z);
    }

    public static double multiDouble(double x)
    {
        return x * x;
    }
}
