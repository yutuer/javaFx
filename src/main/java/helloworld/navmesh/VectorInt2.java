package helloworld.navmesh;


/**
 * @author zhoufang
 * @version v0.1 2018年8月22日 下午8:19:21  zhoufang
 */
public class VectorInt2
{

    public int x;

    public int y;

    public static VectorInt2 zero = new VectorInt2(0, 0);

    public VectorInt2(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public long sqrMagnitudeLong()
    {
        long _x = x;
        long _y = y;
        return _x * _x + _y * _y;
    }

    public static long dotLong(VectorInt2 a, VectorInt2 b)
    {
        return (long) a.x * (long) b.x + (long) a.y * (long) b.y;
    }


}
