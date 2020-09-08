package helloworld.navmesh;

/**
 * @author zhoufang
 * @version v0.1 2018年8月22日 下午8:32:07  zhoufang
 */
public class NavConst
{

    public static final String BOUNDS_PRIFIX = "bound:";
    public static final String NODE_PRIFIX = "nodes:";
    public static final String NODE_CONECTION_PRIFIX = "node-connection:";
    public static final String CONNECTION_PRIFIX = "connections:";
    public static final String COMMA = ",";
    public static final String SEMI = ";";

    public static final float PrecisionFactor = 0.001F;
    public static final int MAX_SEARCH_COUNT = 10000;

    public static final double NAV_SCALE = 100.0;

    public static final int INIT_PATHNODE_COUNT = 300;

    public static final double CELLSIZE = 10;
}
