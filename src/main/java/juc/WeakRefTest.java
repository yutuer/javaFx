package juc;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/3 18:35
 * @Version 1.0
 */
public class WeakRefTest
{

    public static void main(String[] args)
    {

        List<Object> list = new ArrayList<>();
        WeakReference<List> listWeakReference = new WeakReference<>(list);


        Object o = new Object();
        list.add(o);
        list.add(new Object());

        list = null;
        System.gc();

        System.out.println(listWeakReference.get());
    }
}
