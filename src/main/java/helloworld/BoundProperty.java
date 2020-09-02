package helloworld;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * @Author zhangfan
 * @Date 2020/8/27 18:17
 * @Version 1.0
 */
public class BoundProperty
{
    public static void main(String[] args)
    {
        IntegerProperty x = new SimpleIntegerProperty(10);
        IntegerProperty y = new SimpleIntegerProperty(20);
        IntegerProperty z = new SimpleIntegerProperty(60);

        z.bind(x.add(y));
        System.out.println("after bind , z :Bound = " + z.isBound() + ", z = " + z.get()); //z的值为30

        x.set(15);
        y.set(19);
        System.out.println("after change x y, z :Bound = " + z.isBound() + ", z = " + z.get()); //z的值为34

        z.unbind();

        x.set(100);
        y.set(200);
        System.out.println("after unbinding, z :Bound = " + z.isBound() + ", z = " + z.get()); //!!! z的值为34
    }
}
