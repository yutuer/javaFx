package helloworld.uml;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * @Description 一个显示条目
 * @Author zhangfan
 * @Date 2020/8/5 10:10
 * @Version 1.0
 */
public class ShowEntry extends HBox
{
    /**
     * 显示key
     */
    private String key;

    /**
     * 显示value
     */
    private String value;

    public ShowEntry(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    public void init()
    {
        Label label = new Label(key);

    }
}
