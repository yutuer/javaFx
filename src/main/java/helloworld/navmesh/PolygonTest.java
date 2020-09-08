package helloworld.navmesh;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/7 18:18
 * @Version 1.0
 */
public class PolygonTest extends Application
{
    private NavNodeRasterizer navNodeRasterizer;

    public static void main(String[] args)
    {
        Application.launch(args);
    }

    private void build()
    {
        NavMeshBuilder navMeshBuilder = new NavMeshBuilder();
        String content = navMeshBuilder.parseFileInString("maps/Ani_Test_nav.txt");
        this.navNodeRasterizer = navMeshBuilder.build(content);
    }

    @Override
    public void start(Stage stage)
    {
        build();

        HBox root = new HBox();
        root.setSpacing(10);
        root.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: blue;");

        Polygon p = new Polygon();
        p.setFill(Color.WHITE);
        p.setStroke(Color.RED);

        NavNode[] allNodes = navNodeRasterizer.getAllNodes();
        for (int i = 0; i < allNodes.length; i++)
        {
            NavNode navNode = allNodes[i];
            Line[] lines = navNode.getLines();

            for (int j = 0; j < lines.length; j++)
            {
                Line line = lines[j];
                p.getPoints().addAll(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
            }
        }
        root.getChildren().add(p);

//        Polygon parallelogram = new Polygon();
//        parallelogram.getPoints().addAll(30.0, 0.0,
//                130.0, 0.0,
//                100.00, 50.0,
//                0.0, 50.0);
//        parallelogram.setFill(Color.YELLOW);
//        parallelogram.setStroke(Color.BLACK);
//        Polygon hexagon = new Polygon(100.0, 0.0,
//                120.0, 20.0,
//                120.0, 40.0,
//                100.0, 60.0,
//                80.0, 40.0,
//                80.0, 20.0);
//        hexagon.setFill(Color.WHITE);
//        hexagon.setStroke(Color.BLACK);
//
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Using Polygons");
        stage.show();
    }
}
