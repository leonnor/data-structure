import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {

    public int[] nextLargerNodes(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        while(head != null){
            list.add(head.val);
            head = head.next;
        }
        int[] ans = new int[list.size()];
        for (int i = list.size() - 1; i >= 0; i--){
            if (stack.isEmpty() || list.get(i) >= stack.peek()){
                stack.push(list.get(i));
                ans[i] = 0;
            } else {
                if (!stack.isEmpty() && list.get(i) < stack.peek()){
                    int val = stack.pop();
                    stack.push(list.get(i));
                    stack.push(val);
                    ans[i] = stack.peek();
                }
            }
        }
        return ans;
    }
}