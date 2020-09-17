package crossLink.javaFx;

import crossLink.aoi.cross.CrossLinkNode;
import crossLink.aoi.cross.CrossAoi;
import crossLink.listener.CrossLinkBroadListener;
import crossLink.listener.ChessListener;
import crossLink.listener.DebugListener;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.PropertiesUtils;

import java.util.Properties;
import java.util.Random;

/**
 * @Description aoi test
 * @Author zhangfan
 * @Date 2020/9/15 18:53
 * @Version 1.0
 */
public class CrossAoiTest extends Application
{
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        /*
         广播范围会使用方框框起来

         被广播的节点标记为红色
         添加形式的节点标记为金色

         删除的节点不会移除, 被标记为褐色

         move 是先删除, 然后添加
         */
        Properties properties = PropertiesUtils.getProperties("crossLink.properties");
        int num = PropertiesUtils.getInt(properties, "num", 200);

        int maxX = PropertiesUtils.getInt(properties, "maxX", 20);
        int maxY = PropertiesUtils.getInt(properties, "maxY", 20);
        int scale = PropertiesUtils.getInt(properties, "scale", 50);

        final int debugWidth = PropertiesUtils.getInt(properties, "xRange", 100);
        final int debugHeight = PropertiesUtils.getInt(properties, "yRange", 100);

        int xRange = maxX * scale;
        int yRange = maxY * scale;

        CrossAoi crossAoi = new CrossAoi(xRange, yRange);

        Pane root = new Pane();

        Chessboard chessboard = new Chessboard(root, scale, maxX, maxY, false);
        chessboard.draw();

        ChessListener chessListener = new ChessListener(chessboard);
        crossAoi.addListener(chessListener);

        CrossLinkNode first = null;

        Random random = new Random();
        for (int i = 1; i <= num; i++)
        {
            CrossLinkNode baseNode = new CrossLinkNode(i, (int) (random.nextDouble() * xRange), (int) (random.nextDouble() * yRange));
//            BaseNode baseNode = new BaseNode((int) (1.0 * xRange / num * i), (int) (1.0 * yRange / num * i));
            if (first == null)
            {
                first = baseNode;
            }
            crossAoi.addNode(baseNode);
        }

        CrossLinkBroadListener broadListener = new CrossLinkBroadListener(debugWidth, debugHeight);
        crossAoi.addListener(broadListener);
        DebugListener debugListener = new DebugListener(chessboard, debugWidth, debugHeight);
        crossAoi.addListener(debugListener);

//        crossAoi.addNode(new BaseNode((int) (random.nextDouble() * xRange), (int) (random.nextDouble() * yRange)));

//        crossAoi.removeNode(first);

        boolean isLeft = first.x > (xRange / 2);
        boolean isUpper = first.y > (yRange / 2);

        final int dis = 100;

        final CrossLinkNode f = first;
        crossAoi.moveNode(f, f.x + (isLeft ? -1 * dis : dis), f.y + (isUpper ? -1 * dis : dis));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("CrossAoi Test");
        stage.show();
    }
}
