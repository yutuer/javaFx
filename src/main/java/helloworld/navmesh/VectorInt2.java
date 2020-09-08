package helloworld.navmesh;


/**
 * @author zhoufang
 * @version v0.1 2018年8月22日 下午8:19:21  zhoufang
 */
public class VectorInt2
{

    public double x;

    public double z;

    public static VectorInt2 zero = new VectorInt2(0, 0);

    public VectorInt2(double x, double z)
    {
        this.x = x;
        this.z = z;
    }

    public double sqrMagnitudeLong()
    {
        double _x = x;
        double _z = z;
        return _x * _x + _z * _z;
    }

    public static double dotLong(VectorInt2 a, VectorInt2 b)
    {
        return a.x * b.x + a.z * b.z;
    }


}
