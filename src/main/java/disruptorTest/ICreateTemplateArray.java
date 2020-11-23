package disruptorTest;

/**
 * 模板创建数组的接口
 * Created by wangqiang on 2018/6/21.
 */
public interface ICreateTemplateArray<T>
{
    /**
     * 创建数组
     * @param capacity
     * @return
     */
    T[] create(int capacity);
}
