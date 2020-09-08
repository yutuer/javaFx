package helloworld.navmesh;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/7 18:46
 * @Version 1.0
 */
public class VectorInt3
{
    public double x;
    public double y;
    public double z;

    public VectorInt3(int x, int y, int z)
    {
        this.x = x / NavConst.NAV_SCALE;
        this.y = y / NavConst.NAV_SCALE;
        this.z = z / NavConst.NAV_SCALE;
    }

    public static VectorInt3 parse(String data)
    {
        String[] split = data.split(NavConst.COMMA);
        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[1]);
        int z = Integer.parseInt(split[2]);
        return new VectorInt3(x, y, z);
    }
}
