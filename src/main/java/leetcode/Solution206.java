package leetcode;

/**
 * @Description 206   逆序, 使用双指针
 * @Author zhangfan
 * @Date 2020/11/20 16:58
 * @Version 1.0
 */
public class Solution206
{
    public ListNode reverseList(ListNode head)
    {
        if (head == null || head.next == null)
        {
            return head;
        }

        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp;
        while (cur != null)
        {
            tmp = cur;
            cur = cur.next;

            tmp.next = pre;
            pre = tmp;
        }
        return pre;
    }

    private static class ListNode
    {
        int val;
        ListNode next;

        ListNode(int x)
        {
            val = x;
        }
    }
}


