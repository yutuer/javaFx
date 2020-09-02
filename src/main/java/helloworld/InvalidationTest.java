package helloworld;

import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/8/20 18:13
 * @Version 1.0
 */
public class InvalidationTest
{
    public static void main(String[] args)
    {
        SimpleIntegerProperty simpleIntegerProperty = new SimpleIntegerProperty(100);
        simpleIntegerProperty.addListener(InvalidationTest::invalid);

        System.out.println("-------------------");
        simpleIntegerProperty.set(101);
        System.out.println("-------------------");
        simpleIntegerProperty.set(102);
        System.out.println("-------------------");
        int i = simpleIntegerProperty.get();
        System.out.println("-------------------");
        simpleIntegerProperty.set(103);
        System.out.println("-------------------");

        simpleIntegerProperty.removeListener(InvalidationTest::invalid);
    }

    public static void invalid(Observable prop)
    {
        System.out.println("prop is invalid");
    }
}
