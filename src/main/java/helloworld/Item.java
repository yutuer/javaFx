package helloworld;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/8/20 16:49
 * @Version 1.0
 */
public class Item
{

    private DoubleProperty weight;
    private double _weight = 50;

    public DoubleProperty getWeight()
    {
        if (weight == null)
        {
            weight = new SimpleDoubleProperty(this, "weight", _weight);
        }
        return weight;
    }
}
