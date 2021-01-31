package leetcode.P2;

import leetcode.P2.ListNode;

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ret = new ListNode(0);
        ListNode result = ret;
        int needPlus = 0;
        int current_plus = 0;

        while(l2 != null && l1 != null){
            current_plus = l1.val + l2.val + needPlus;
            if(current_plus >= 10){
                current_plus -= 10;
                needPlus = 1;
            }else{
                needPlus = 0;
            }
            ret.next = new ListNode(current_plus);
            l2 = l2.next;
            l1 = l1.next;
            ret = ret.next;
        }

        ListNode temp = (l2 == null) ? l1 : l2;
        while(temp!= null){
            current_plus = temp.val + needPlus;
            if(current_plus >= 10){
                current_plus -= 10;
                needPlus = 1;
            }else{
                needPlus = 0;
            }
            ret.next = new ListNode(current_plus);
            temp = temp.next;
            ret = ret.next;
        }
        if(needPlus == 1){
            ret.next = new ListNode(1);
        }
        return result.next;
    }
}