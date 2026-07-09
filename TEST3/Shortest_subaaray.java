import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public int shortestSubarray(int[] nums, int K) {
        long[] prefix = new long[nums.length + 1];

       
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        Deque<Integer> dq = new LinkedList<>();
        int ans = nums.length + 1;

        for (int i = 0; i < prefix.length; i++) {


            while (!dq.isEmpty() && prefix[i] - prefix[dq.peekFirst()] >= K) {
                ans = Math.min(ans, i - dq.pollFirst());
            }

          
            while (!dq.isEmpty() && prefix[i] <= prefix[dq.peekLast()]) {
                dq.pollLast();
            }

            dq.offerLast(i);
        }

        return ans == nums.length + 1 ? -1 : ans;
    }
}