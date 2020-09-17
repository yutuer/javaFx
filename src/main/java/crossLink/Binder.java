package crossLink;

import crossLink.aoi.BaseNode;
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

    public static Map<BaseNode, Shape> relations = new HashMap<>();

    public static void bind(Shape shape, BaseNode node)
    {
        relations.put(node, shape);
    }

    public static Shape removeBind(BaseNode node)
    {
        return relations.remove(node);
    }

    public static Shape get(BaseNode node)
    {
        return relations.get(node);
    }
}
