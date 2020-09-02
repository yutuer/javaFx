package jvmByteCode;

import java.io.InputStream;
import java.net.URL;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/8/14 21:05
 * @Version 1.0
 */
public class StringTest
{
    public static void main(String[] args)
    {
        String str = "aaa";


        Class<Object> objectClass = Object.class;

        InputStream resourceAsStream = objectClass.getResourceAsStream("jvmByteCode/StringTest.class");
        URL resource = objectClass.getResource("jvmByteCode/StringTest.class");

        System.out.println(resourceAsStream);
        System.out.println(resource);


    }
}
