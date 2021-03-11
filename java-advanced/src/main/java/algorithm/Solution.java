package algorithm;

import java.util.*;

public class Solution {
    /**
     * 1. 两数之和
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
     * https://leetcode-cn.com/problems/two-sum/
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int interval = target - nums[i];
            if (map.containsKey(interval)) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 2. 两数相加
     * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * https://leetcode-cn.com/problems/add-two-numbers/
     */
    // 通过carry作进位
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;

            int sum = n1 + n2 + carry;

            cur.next = new ListNode(sum % 10);
            cur = cur.next;

            carry = sum / 10;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (carry > 0) {
            cur.next = new ListNode(carry);
        }

        return pre.next;
    }

    /**
     * 3. 无重复字符的最长子串
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
     */
    // 不需要管left左边的字符
    public int lengthOfLongestSubstring(String s) {
        int max = 0, left = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }

            map.put(s.charAt(i), i);

            max = Math.max(max, i - left + 1);
        }

        return max;
    }

    /**
     * 4. 寻找两个正序数组的中位数
     * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
     * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        //因为数组是从索引0开始的，因此我们在这里必须+1，即索引(k+1)的数，才是第k个数。
        // 中间靠左的数字
        int left = (n + m + 1) / 2;
        // 中间靠右的数字
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getK(nums1, 0, n - 1, nums2, 0, m - 1, left)
                + getK(nums1, 0, n - 1, nums2, 0, m - 1, right))
                * 0.5;
    }

    private int getK(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return getK(nums2, start2, end2, nums1, start1, end1, k);

        if (len1 == 0) return nums2[start2 + k - 1];

        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len1, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getK(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        } else {
            return getK(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }

    /**
     * 5. 最长回文子串
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     * https://leetcode-cn.com/problems/longest-palindromic-substring/
     */
    public String longestPalindrome(String s) {
        int n = s.length();

        boolean[][] dp = new boolean[n][n];

        String ans = "";

        for (int len = 0; len < n; ++len) {
            for (int i = 0; i + len < n; ++i) {
                int j = i + len;
                if (len == 0) {
                    dp[i][j] = true;
                } else if (len == 1) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
                }
                if (dp[i][j] && len + 1 > ans.length()) {
                    ans = s.substring(i, j + 1);
                }
            }
        }
        return ans;
    }

    /**
     * 11. 盛最多水的容器
     * https://leetcode-cn.com/problems/container-with-most-water/
     */
    public int maxArea(int[] height) {
        int ans = 0;
        int l = 0;
        int r = height.length - 1;

        while (l < r) {
            ans = Math.max(Math.min(height[l], height[r]) * (r - l), ans);
            if (height[l] < height[r]) {
                l = l + 1;
            } else if (height[l] > height[r]) {
                r = r - 1;
            } else {
                l = l + 1;
                r = r - 1;
            }
        }
        return ans;
    }

    /**
     * 15. 三数之和
     * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组
     * https://leetcode-cn.com/problems/3sum/
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }

        Arrays.sort(nums);

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break;

            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int l = i + 1;
            int r = nums.length - 1;

            while (l < r) {
                int tmp = nums[i] + nums[l] + nums[r];

                if (tmp > 0) {
                    r = r - 1;
                } else if (tmp < 0) {
                    l = l + 1;
                } else {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));

                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;

                    l++;
                    r--;
                }
            }
        }

        return res;
    }

    /**
     * 电话号码的字母组合
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
     */
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack17(combinations, phoneMap, digits, 0, new StringBuilder());

        return combinations;
    }

    private void backtrack17(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuilder combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack17(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyNode = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummyNode;

        for (int i = 0; i < n; i++) {
            first = first.next;
        }

        while (first != null) {
            second = second.next;
            first = first.next;
        }

        second.next = second.next.next;
        return dummyNode.next;
    }


    /**
     * 20. 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     */
    public boolean isValid(String s) {
        if (s.length() % 2 == 1) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put('}', '{');
            put(']', '[');
        }};

        Deque<Character> stack = new LinkedList<>();

        for (char c : s.toCharArray()) {
            if (pairs.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != pairs.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    /**
     * 21. 合并两个有序链表
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * https://leetcode-cn.com/problems/merge-two-sorted-lists/
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        tail.next = l1 != null ? l1 : l2;
        return dummyHead.next;
    }

    /**
     * 22. 括号生成
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * https://leetcode-cn.com/problems/generate-parentheses/solution/pei-yang-chou-xiang-si-wei-hui-su-jie-fa-7dwu/
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack22(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    private void backtrack22(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }

        if (open < max) {
            cur.append("(");
            backtrack22(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }

        if (close < open) {
            cur.append(")");
            backtrack22(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    /**
     * 31. 下一个排列
     * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     * 必须 原地 修改，只允许使用额外常数空间。
     * https://leetcode-cn.com/problems/next-permutation/solution/miao-dong-xi-lie-100-cong-xia-yi-ge-pai-gog8j/
     */
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;

        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    /**
     * 33. 搜索旋转排序数组
     * 升序排列的整数数组 nums 在预先未知的某个点上进行了旋转（例如， [0,1,2,4,5,6,7] 经旋转后可能变为[4,5,6,7,0,1,2] ）。
     * 请你在数组中搜索target ，如果数组中存在这个目标值，则返回它的索引，否则返回-1。
     * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
     */
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < nums[high]) {
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else {
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 34. 在排序数组中查找元素的第一个和最后一个位置
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * 如果数组中不存在目标值 target，返回[-1, -1]。
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }

        int first = findFirstNum(nums, target);
        if (first == -1) {
            return new int[]{-1, -1};
        }
        int last = findLastNum(nums, target);
        return new int[]{first, last};
    }

    private int findFirstNum(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                right = mid;
            }
        }
        return nums[left] == target ? left : -1;
    }

    private int findLastNum(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + ((right - left + 1) >> 1);
            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return left < nums.length && nums[left] == target ? left : -1;
    }

    /**
     * 39. 组合总和
     * 给定一个无重复元素的数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
     * candidates中的数字可以无限制重复被选取。
     * https://leetcode-cn.com/problems/combination-sum/
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        dfs(candidates, target, 0, ans, combine);
        return ans;
    }

    private void dfs(int[] candidates, int target, int idx, List<List<Integer>> ans, List<Integer> combine) {
        if (candidates.length == idx) {
            return;
        }

        if (target == 0) {
            ans.add(new ArrayList<>(combine));
            return;
        }

        dfs(candidates, target, idx + 1, ans, combine);

        if (target - candidates[idx] >= 0) {
            combine.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], idx, ans, combine);
            combine.remove(combine.size() - 1);
        }
    }

    /**
     * 48. 旋转图像
     * 给定一个 n×n 的二维矩阵matrix 表示一个图像。请你将图像顺时针旋转 90 度。
     * <p>
     * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
     * https://leetcode-cn.com/problems/rotate-image/solution/yuan-di-xuan-zhuan-jian-ji-dai-ma-ji-bai-soi1/
     */
    public void rotate(int[][] matrix) {
        int n = matrix[0].length;

        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - i][j];
                matrix[n - 1 - i][j] = tmp;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i + 1; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }

    /**
     * 49. 字母异位词分组
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     * https://leetcode-cn.com/problems/group-anagrams/
     */
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 53. 最大子序和
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * https://leetcode-cn.com/problems/maximum-subarray/
     */
    public int maxSubArray(int[] nums) {
        int pre = 0;
        int max = nums[0];
        for (int num : nums) {
            pre = Math.max(num + pre, num);
            max = Math.max(pre, max);
        }
        return max;
    }

    /**
     * 70. 爬楼梯
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * <p>
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * <p>
     * 注意：给定 n 是一个正整数。
     */
    public int climbStairs(int n) {
        if (n < 3) {
            return n;
        }
        int p1 = 1, p2 = 2, p3 = 0;
        for (int i = 3; i <= n; i++) {
            p3 = p1 + p2;
            p1 = p2;
            p2 = p3;
        }
        return p3;
    }

    /**
     * 101. 对称二叉树
     * 给定一个二叉树，检查它是否是镜像对称的。
     */
    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    private boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val &&
                check(p.left, q.right) &&
                check(p.right, q.left);
    }

    /**
     * 104. 二叉树的最大深度
     * 给定一个二叉树，找出其最大深度。
     * <p>
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 121. 买卖股票的最佳时机
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
     */
    public int maxProfit(int[] prices) {
        int p1 = 0, max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[p1] < prices[i]) {
                max = Math.max(max, prices[i] - prices[p1]);
            } else {
                p1 = i;
            }
        }
        return max;
    }

    /**
     * 136. 只出现一次的数字
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 说明：
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     * https://leetcode-cn.com/problems/single-number/
     */
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single = single ^ num;
        }
        return single;
    }

    /**
     * 141. 环形链表
     * https://leetcode-cn.com/problems/linked-list-cycle/
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    /**
     * 155. 最小栈
     * https://leetcode-cn.com/problems/min-stack/
     * 两个栈，或存差值
     */

    /**
     * 226. 翻转二叉树
     * https://leetcode-cn.com/problems/invert-binary-tree/
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;

        return root;
    }

    /**
     * 160. 相交链表
     * 编写一个程序，找到两个单链表相交的起始节点。
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode ha = headA, hb = headB;
        while (ha != hb) {
            ha = ha != null ? ha.next : headB;
            hb = hb != null ? hb.next : headA;
        }
        return ha;
    }

    /**
     * 169. 多数元素
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于⌊ n/2 ⌋的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     */
    public int majorityElement(int[] nums) {
//        Arrays.sort(nums);
//        return nums[nums.length / 2];
        int count = 0;
        int candidate = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }

    /**
     * 206. 反转链表
     * https://leetcode-cn.com/problems/reverse-linked-list/
     */
    public ListNode reverseList(ListNode head) {
//        ListNode pre = null;
//        ListNode cur = head;
//        while (cur != null) {
//            ListNode tmp = cur.next;
//            cur.next = pre;
//            pre = cur;
//            cur = tmp;
//        }
//        return pre;
        if (head == null || head.next == null) return head;
        ListNode cur = reverseList(head.next);
        head.next.next = head;
        head.next = null;

        return cur;
    }

    /**
     * 234. 回文链表
     * https://leetcode-cn.com/problems/palindrome-linked-list/
     */
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode half = reverseList(slow.next);

        ListNode p1 = head, p2 = half;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return result;
    }

    /**
     * 283. 移动零
     * https://leetcode-cn.com/problems/move-zeroes/
     */
    public void moveZeroes(int[] nums) {
        int p1 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int tmp = nums[p1];
                nums[p1] = nums[i];
                nums[i] = tmp;
                p1++;
            }
        }
    }

    /**
     * 448. 找到所有数组中消失的数字
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int num : nums) {
            nums[Math.abs(num) - 1] = -Math.abs(nums[Math.abs(num) - 1]);
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                ans.add(i + 1);
            }
        }
        return ans;
    }

    /**
     * 461. 汉明距离
     * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
     *
     * 给出两个整数 x 和 y，计算它们之间的汉明距离。
     */
    public int hammingDistance(int x, int y) {
//        return Integer.bitCount(x ^ y);
        int xor = x ^ y;
        int distance = 0;
        while (xor != 0) {
            distance += xor & 1;
            xor = xor >> 1;
        }
        return distance;
    }

    /**
     * 543. 二叉树的直径
     * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
     */
    int ans;
    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1;
    }

    private int depth(TreeNode node) {
        if (node == null) return 0;

        int L = depth(node.left);
        int R = depth(node.right);
        ans = Math.max(ans, L + R + 1);
        return Math.max(L, R) + 1;
    }

    /**
     * 617. 合并二叉树
     * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
     * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为NULL 的节点将直接作为新二叉树的节点。
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;

        TreeNode mergeNode = new TreeNode(root1.val + root2.val);
        mergeNode.left = mergeTrees(root1.left, root2.left);
        mergeNode.right = mergeTrees(root1.right, root2.right);
        return mergeNode;
    }
}
