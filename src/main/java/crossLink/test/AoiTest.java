package crossLink.test;

import util.PropertiesUtils;

import java.util.Properties;
import java.util.Random;

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
        Random random = new Random();

        Properties properties = PropertiesUtils.getProperties("crossLink.properties");
        int defaultNodeNum = PropertiesUtils.getInt(properties, "defaultNodeNum", 200);
        int maxX = PropertiesUtils.getInt(properties, "maxX", 20);
        int maxY = PropertiesUtils.getInt(properties, "maxY", 20);
        int scale = PropertiesUtils.getInt(properties, "scale", 50);
        int addNum = PropertiesUtils.getInt(properties, "addNum", 1);

        AoiTestUnit aoiTestUnit = new AoiTestUnit(defaultNodeNum, maxX, maxY, scale);

        for (int i = 0; i < 10; i++)
        {
            aoiTestUnit.initNodes();
            aoiTestUnit.makeTestAddNodeData(addNum);

            aoiTestUnit.loadAoi("Cell").testAdd();
            aoiTestUnit.loadAoi("CrossLink").testAdd();
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
