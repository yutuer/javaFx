package crossLink;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 绑定器
 * @Author zhangfan
 * @Date 2020/9/15 20:29
 * @Version 1.0
 */
public class Binder
{

    public static Map<Long, Shape> relations = new HashMap<>();

    public static void bind(Shape shape, long label)
    {
        relations.put(label, shape);
    }

    public static Shape removeBind(long label)
    {
        return relations.remove(label);
    }

    public static Shape get(long label)
    {
        return relations.get(label);
    }

    public static void changeColor(long label, Color color)
    {
        Shape shape = get(label);
        if (shape != null)
        {
            shape.setFill(color);
        }
    }
}
