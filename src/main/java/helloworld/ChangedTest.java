package helloworld;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/8/20 20:54
 * @Version 1.0
 */
public class ChangedTest
{
    public static void main(String[] args)
    {
        SimpleIntegerProperty simpleIntegerProperty = new SimpleIntegerProperty(100);
        simpleIntegerProperty.addListener(ChangedTest::changed);
        simpleIntegerProperty.addListener(ChangedTest::changed2);

        System.out.println("-------------------");
        simpleIntegerProperty.set(101);
        System.out.println("-------------------");
        simpleIntegerProperty.set(102);
        System.out.println("-------------------");
        int i = simpleIntegerProperty.get();
        System.out.println("-------------------");
        simpleIntegerProperty.set(103);
        System.out.println("-------------------");

        simpleIntegerProperty.removeListener(ChangedTest::changed);
        simpleIntegerProperty.removeListener(ChangedTest::changed2);
    }

    public static void changed(ObservableValue<? extends Number> prop, Number oldValue, Number newValue)
    {
        System.out.printf("prop is changed1: old:%d new:%d \n", oldValue, newValue);
    }

    public static void changed2(ObservableValue<? extends Number> prop, Number oldValue, Number newValue)
    {
        System.out.printf("prop is changed2: old:%d new:%d \n", oldValue, newValue);
    }
}
