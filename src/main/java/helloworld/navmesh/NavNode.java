package helloworld.navmesh;

import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

/**
 * @Description
 * @Author zhangfan
 * @Date 2020/9/7 18:20
 * @Version 1.0
 */
public class NavNode extends Polygon
{
    /**
     * 线
     */
    private Line[] lines;

    private int index;
    private VectorInt3 position;
    private byte area;
    private VectorInt3 vertex0;
    private VectorInt3 vertex1;
    private VectorInt3 vertex2;

    public NavNode(double a0, double a1, double b0, double b1, double c0, double c1)
    {
        super(a0, a1, b0, b1, c0, c1);

        lines = new Line[3];
        lines[0] = new Line(a0, a1, b0, b1);
        lines[1] = new Line(b0, b1, c0, c1);
        lines[2] = new Line(c0, c1, a0, a1);
    }

    public NavNode(int index, VectorInt3 position, byte area, VectorInt3 vertex0, VectorInt3 vertex1, VectorInt3 vertex2)
    {
        super(vertex0.x, vertex0.z,
                vertex1.x, vertex1.z,
                vertex2.x, vertex2.z);

        lines = new Line[3];
        lines[0] = new Line(vertex0.x, vertex0.z, vertex1.x, vertex1.z);
        lines[1] = new Line(vertex1.x, vertex1.z, vertex2.x, vertex2.z);
        lines[2] = new Line(vertex2.x, vertex2.z, vertex0.x, vertex0.z);

        this.index = index;
        this.position = position;
        this.area = area;
        this.vertex0 = vertex0;
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    public Line[] getLines()
    {
        return lines;
    }

}
