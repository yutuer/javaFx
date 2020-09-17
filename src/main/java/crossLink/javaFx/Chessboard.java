package crossLink.javaFx;

import crossLink.aoi.BaseNode;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.util.Random;

/**
 * @Description 棋盘(用于辅助表现)
 * @Author zhangfan
 * @Date 2020/9/15 19:59
 * @Version 1.0
 */
public class Chessboard
{
    private Pane pane;

    /**
     * 系数
     */
    private int scale;
    private int maxX;
    private int maxY;

    /**
     * 添加的棋子是否需要按照scale来计算位置
     */
    private boolean addWithScale;

    /**
     * 添加的圆形的半径
     */
    private int radius = 3;

    public Chessboard(Pane pane, int scale, int maxX, int maxY)
    {
        this(pane, scale, maxX, maxY, true);
    }

    public Chessboard(Pane pane, int scale, int maxX, int maxY, boolean addWithScale)
    {
        this.pane = pane;
        this.scale = scale;
        this.maxX = maxX;
        this.maxY = maxY;
        this.addWithScale = addWithScale;
    }

    public void draw()
    {
        ObservableList<Node> children = pane.getChildren();

        // 先画平行于x轴的
        for (int i = 0; i < maxY; i++)
        {
            int ii = i * scale;
            children.add(new Line(0, ii, maxX * scale, ii));
        }

        // 再画平行于Y轴的
        for (int i = 0; i < maxX; i++)
        {
            int ii = i * scale;
            children.add(new Line(ii, 0, ii, maxY * scale));
        }
    }

    /**
     * 添加一个用来测试的矩形
     *
     * @param node
     * @param width
     * @param height
     */
    public void addDebugRec(BaseNode node, int width, int height)
    {
        int x = node.x;
        int y = node.y;

        int leftX = (x - width);
        int upperY = (y - height);

        ObservableList<Node> children = pane.getChildren();

        Path path = new Path();
        path.getElements().addAll(new MoveTo(leftX, upperY), new LineTo(leftX, upperY + 2 * height));
        path.getElements().addAll(new LineTo(leftX + 2 * width, upperY + 2 * height), new LineTo(leftX + 2 * width, upperY));
        path.getElements().add(new ClosePath());
        children.add(path);
    }

    /**
     * 移除
     *
     * @param shape
     */
    public void removeChess(Shape shape)
    {
        ObservableList<Node> children = pane.getChildren();
        children.remove(shape);
    }

    /**
     * 添加
     *
     * @param x
     * @param y
     * @param isRandomColor
     * @return
     */
    public Shape addChess(int x, int y, boolean isRandomColor)
    {
        ObservableList<Node> children = pane.getChildren();
        if (isRandomColor)
        {
            Random random = new Random();
            Color color = Color.color(random.nextFloat(), random.nextFloat(), random.nextFloat());
            Circle circle;
            if (addWithScale)
            {
                circle = new Circle(x * scale, y * scale, radius, color);
            }
            else
            {
                circle = new Circle(x, y, radius, color);
            }
            children.add(circle);
            return circle;
        }
        else
        {
            Circle circle;
            if (addWithScale)
            {
                circle = new Circle(x * scale, y * scale, radius);
            }
            else
            {
                circle = new Circle(x, y, radius);
            }
            children.add(circle);
            return circle;
        }
    }
}
