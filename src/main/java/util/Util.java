package util;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/2 17:29
 * @Version 1.0
 */
public class Util
{


    /**
     * 首字母小写
     *
     * @param name
     * @return
     */
    public static String firstLetterLower(String name, int index)
    {
        final int LowerIndex = index;
        return name.substring(0, LowerIndex).toLowerCase() + name.substring(LowerIndex);
    }

    /**
     * 首字母大写
     *
     * @param name
     * @return
     */
    public static String firstLetterUpper(String name, int index)
    {
        final int upperIndex = index;
        return name.substring(0, upperIndex).toUpperCase() + name.substring(upperIndex);
    }

    /**
     * 得到>= x的2次幂
     *
     * @param x
     * @return
     */
    public static int intToMaxTowPower(int x)
    {
        //第一种方法,  不停的向右移位. 直到==0为止
        int y = x;
        int count = 0;
        while (y > 0)
        {
            count++;
            y >>= 1;
        }
        return 1 << count;
    }

    public static void main(String[] args)
    {
        System.out.println(intToMaxTowPower(0b111));
    }

}
