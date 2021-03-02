package disruptorTest;

import disruptorTest.dis.My_ConsumerInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2021/1/22 15:28
 * @Version 1.0
 */
public class My_ConsumerRepository implements Iterable<My_ConsumerInfo>
{
    /**
     * 消费者信息列表
     */
    private final Collection<My_ConsumerInfo> consumerInfos = new ArrayList<>();

    @Override
    public Iterator<My_ConsumerInfo> iterator()
    {
        return null;
    }

    @Override
    public void forEach(Consumer<? super My_ConsumerInfo> action)
    {

    }

    @Override
    public Spliterator<My_ConsumerInfo> spliterator()
    {
        return null;
    }
}
