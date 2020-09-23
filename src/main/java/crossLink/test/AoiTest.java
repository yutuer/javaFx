package crossLink.test;

import util.Log;
import util.PropertiesUtils;

import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/18 16:55
 * @Version 1.0
 */
public class AoiTest
{
    public static int SCALE = 1;

    public static void main(String[] args)
    {
        Properties properties = PropertiesUtils.getProperties("crossLink.properties");

        int[] testTypes = PropertiesUtils.getIntArray(properties, "testType", new int[]{1, 2, 3});
        int testNum = PropertiesUtils.getInt(properties, "testNum", 1);

        int defaultNodeNum = PropertiesUtils.getInt(properties, "defaultNodeNum", 200);
        int maxX = PropertiesUtils.getInt(properties, "maxX", 20);
        int maxY = PropertiesUtils.getInt(properties, "maxY", 20);
        int scale = PropertiesUtils.getInt(properties, "scale", 50);
        int addNum = PropertiesUtils.getInt(properties, "addNum", 1);

        int v = PropertiesUtils.getInt(properties, "v", 5);
        int moveInterval = PropertiesUtils.getInt(properties, "moveInterval", 2000);

        AoiTestUnit aoiTestUnit = new AoiTestUnit(defaultNodeNum, maxX, maxY, scale);

        for (int t = 0; t < testTypes.length; t++)
        {
            int testType = testTypes[t];
            if (testType == 1)
            {
                for (int i = 1; i <= testNum; i++)
                {
                    Log.CrossAOI_Logger.warn("TestAdd 第{}次 [{} {} {} {} {}]",
                            i, defaultNodeNum, addNum, maxX, maxY, scale);

                    aoiTestUnit.initNodes();
                    aoiTestUnit.makeTestAddNodeData(addNum);

                    aoiTestUnit.loadAoi("Cell").testAdd();
                    aoiTestUnit.loadAoi("CrossLink").testAdd();
                }
            }
            else if (testType == 2)
            {

            }
            else if(testType == 3)
            {
                ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
                for (int i = 1; i <= testNum; i++)
                {
                    Log.CrossAOI_Logger.warn("TestMove 第{}次 [{} {} {} {} {}]",
                            i, defaultNodeNum, addNum, maxX, maxY, scale);

                    aoiTestUnit.initNodes();

                    // 随机移动1号节点
                    aoiTestUnit.makeTestMoveData(1, v, moveInterval);

                    aoiTestUnit.loadAoi("Cell").testMove(ses);
//                    aoiTestUnit.loadAoi("CrossLink").testMove(ses);
                }
                ses.shutdown();
            }
        }

//        {
//            aoiTestUnit.makeTestRemoveDataAll();
//
//            aoiTestUnit.loadAoi("Cell").testRemove();
//            aoiTestUnit.loadAoi("CrossLink").testRemove();
//        }


//        // cell 移动
//        moveNodeTestUnit.accpetInput(iaoi);

//        // crossLink 移动
//        moveNodeTestUnit.accpetInput(iaoi);

//        Log.CrossAOI_Logger.info("------------------------------------------------");
//
//        {
//            new RemoveNodeTestUnit(total / 2, total / 2 + 1).accpetInput(iaoi);
//        }

//        {
//            MoveNodeTestUnit moveNodeTestUnit = new MoveNodeTestUnit(total, 5);
//            moveNodeTestUnit.accpetInput(iaoi);
//
//            Executors.newScheduledThreadPool(1).scheduleAtFixedRate(moveNodeTestUnit, 1000, 2000, TimeUnit.MILLISECONDS);
//        }
    }
}
