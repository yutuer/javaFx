package sceneFlow.dict;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/10/16 10:18
 * @Version 1.0
 */
public class Dict
{

    public static DictCondition getDictCondition(int condId)
    {
        return new DictCondition(condId);
    }

    public static DictAction getDictAction(int actionId)
    {
        return new DictAction(actionId);
    }
}
