package math.skillSearch;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/9/24 14:53
 * @Version 1.0
 */
public class Rectangle
{
    public static void main(String[] args)
    {
        int rotation = 45 + 180 + 90;

        double v = rotation * Math.PI / 180;  //rotation角度的弧度
        System.out.println(v);

        System.out.println(Math.cos(v)); // 45度弧度的cos = 1/根号(2)
        System.out.println(Math.sin(v)); // 45度弧度的sin = 1/根号(2)
    }
}
