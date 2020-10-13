package behaviorTree.impl;

import behaviorTree.context.IContext;
import behaviorTree.ifs.single.ILeafNode;

/**
 * @Description 叶子节点
 * @Author zhangfan
 * @Date 2020/8/28 15:19
 * @Version 1.0
 */
public abstract class LeafNode<T extends IContext> extends BaseNode<T> implements ILeafNode<T>
{
}
