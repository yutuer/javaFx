package helloworld.uml;

import javafx.scene.layout.HBox;

/**
 * @Description 基础类
 * @Author zhangfan
 * @Date 2020/8/5 10:06
 * @Version 1.0
 */
public class Show extends HBox
{
    /**
     * 拖动事件的类
     */
    protected Class<?> showClass;

    public Show(Class<?> showClass)
    {
        this.showClass = showClass;
    }

}
