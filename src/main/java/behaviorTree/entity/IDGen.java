package behaviorTree.entity;

/**
 * @Description id生成器
 * @Author zhangfan
 * @Date 2020/11/23 10:38
 * @Version 1.0
 */
public class IDGen
{

    private static int curId;

    public IDGen()
    {
        this(1);
    }

    public IDGen(int curId)
    {
        this.curId = curId;
    }

    public static int getCurId()
    {
        return ++curId;
    }
}
