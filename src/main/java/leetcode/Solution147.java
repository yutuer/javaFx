package leetcode;

/**
 * @Description TODO
 * @Author zhangfan
 * @Date 2020/11/20 16:58
 * @Version 1.0
 */
public class Solution147
{
    public ListNode insertionSortList(ListNode head)
    {
        if (head == null || head.next == null)
        {
            return head;
        }

        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        head = dummy;

        ListNode cur = head.next;
        while (cur != null)
        {

        }
        return head.next;
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


