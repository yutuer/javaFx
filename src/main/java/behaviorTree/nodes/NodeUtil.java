package behaviorTree.nodes;

import behaviorTree.nodes.behavior.FuryAttack;
import behaviorTree.nodes.behavior.NormalAttack;
import behaviorTree.nodes.condition.IsInFury;
import behaviorTree.nodes.condition.RangeFindPlayer;
import behaviorTree.nodes.condition.TargetIsPlayer;
import behaviorTree.nodes.decorate.InvertNode;
import behaviorTree.impl.ActionNode;
import behaviorTree.impl.ConditionNode;
import behaviorTree.impl.DecoratorNode;
import behaviorTree.impl.SelectorNode;
import behaviorTree.impl.SequenceNode;

public class NodeUtil
{

    /**
     * 攻击玩家
     */
    public static final SelectorNode AttackPlayer = new SelectorNode();

    /**
     * 常规攻击
     */
    public static final SequenceNode NormalAttackPlayer = new SequenceNode();

    /**
     * 视野内发现玩家
     */
    public static final ConditionNode RangeFindPlayer = new RangeFindPlayer();

    /**
     * 目标是玩家
     */
    public static final ConditionNode TargetIsPlayer = new TargetIsPlayer();

    /**
     * 普通攻击
     */
    public static final ActionNode NormalAttack = new NormalAttack();

    /**
     * 报复攻击
     */
    public static final SequenceNode BAOFUAttack = new SequenceNode();

    /**
     * 发狂状态
     */
    public static final ConditionNode IsInFury = new IsInFury();

    /**
     * 疯狂攻击
     */
    public static final ActionNode FuryAttack = new FuryAttack();

    /**
     * 反转结果的修饰节点
     */
    public static final DecoratorNode InvertNode = new InvertNode();

    static
    {
//		AttackPlayer.setTip("攻击玩家行为树判断");

        NormalAttackPlayer.setTip("普通攻击判断!!!");
        BAOFUAttack.setTip("报复攻击判断!!!");

        {
            // 普通攻击
            AttackPlayer.addChild(NormalAttackPlayer);

            NormalAttackPlayer.addChild(RangeFindPlayer);
            NormalAttackPlayer.addChild(TargetIsPlayer);

            InvertNode.wrapNode(IsInFury);
            NormalAttackPlayer.addChild(InvertNode);
            NormalAttackPlayer.addChild(NormalAttack);
        }

        {
            // 狂暴攻击
            AttackPlayer.addChild(BAOFUAttack);

            BAOFUAttack.addChild(RangeFindPlayer);
            BAOFUAttack.addChild(TargetIsPlayer);
            BAOFUAttack.addChild(IsInFury);
            BAOFUAttack.addChild(FuryAttack);
        }

    }
}
