package behaviorTree.builder;

import behaviorTree.nodeBuilder.IBehaviourNodeBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 行为树节点builder, 为了不在BehaviourNode接口里面暴露set方法.
 * 原理查看builder模式.(参考下Protobuf)
 * @Author zhangfan
 * @Date 2020/11/23 10:19
 * @Version 1.0
 */
public abstract class BehaviourNodeBuilder<T> implements IBehaviourNodeBuilder<T>
{
    private List<BehaviourNodeBuilder<T>> childs = new ArrayList<>();


    /**
     * 这个接口如果不写在这里, 就要写在BehaviourNode里面, 所以这就是builder模式, 使用这个来构建, 可以避免在原对象上暴露接口
     */
    public void addChild(BehaviourNodeBuilder<T> nodeBuilder)
    {
        childs.add(nodeBuilder);
    }

//    /**
//     * 构造一个InvertBuilder
//     *
//     * @param tip
//     * @param child
//     * @param <T>
//     * @return
//     */
//    public static <T> BehaviourNodeBuilder<T> invertNodeBuilder(String tip, BehaviourTreeBuilder<T> child)
//    {
//        return new BehaviourNodeBuilder<T>()
//        {
//            @Override
//            public IBehaviourNode<T> build()
//            {
//                return new InvertNode<>(tip, child.build());
//            }
//        }
//    }
}
