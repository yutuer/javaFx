package sceneFlow;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/10/15 11:33
 * @Version 1.0
 */
public interface IData
{

    /**
     * 将第几个参数转化成bool. 转型错误会抛出异常
     *
     * @param index
     * @return
     */
    boolean getBoolParam(int index);

    /**
     * 将第几个参数转化成int. 转型错误会抛出异常
     *
     * @param index
     * @return
     */
    int getIntParam(int index);

    /**
     * 将第几个参数转化成int[]. 转型错误会抛出异常
     * 按照;拆分
     *
     * @param index
     * @return
     */
    int[] getIntArrayParam(int index);

}
