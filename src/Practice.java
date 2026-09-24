import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Practice {
    /**
     * Returns the sum of the odd numbers in the array.
     * 
     * Returns 0 if the array is null or has no odd numbers.
     * 
     * @param nums an array of numbers
     * @return the sum of the odd numbers in the array
     */
    public static int oddSum(int[] nums) {
        if (nums == null) {
            return 0;
        }

        int sum = 0;
        for (int num : nums) {
            if (num % 2 != 0) {
                sum += num;
            }
        }
        return sum;
    }

    /**
     * Returns the shortest word in the Set.
     * 
     * If multiple words are tied for shortest, returns the one that is smallest
     * lexicographically.
     * 
     * @param words a set of words
     * @return the shortest word in the set with a lexicographic tiebreaker
     * @throws IllegalArgumentException if words is empty
     * @throws NullPointerException if words is null
     */
    public static String shortestWord(Set<String> words) {
        if (words == null) {
            throw new NullPointerException("Set cannot be null");
        }
        if (words.isEmpty()) {
            throw new IllegalArgumentException("Set cannot be empty");
        }

        String shortest = null;

        for (String word : words) {
            if (shortest == null) {
                shortest = word;
            } else if (word.length() < shortest.length()) {
                shortest = word;
            } else if (word.length() == shortest.length() && word.compareTo(shortest) < 0) {
                shortest = word;
            }
        }

        return shortest;
    }

    /**
     * Returns a set of all the names of people that are 18 years of age or older.
     * 
     * The input maps name to age in years.
     * 
     * @param ages mapping of name to age
     * @return the set of all names of people >= 18 years old
     * @throws NullPointerException if ages is null
     */
    public static Set<String> adults(Map<String, Integer> ages) {
        if (ages == null) {
            throw new NullPointerException("Map cannot be null");
        }

        Set<String> adultNames = new HashSet<>();
        for (Map.Entry<String, Integer> entry : ages.entrySet()) {
            if (entry.getValue() >= 18) {
                adultNames.add(entry.getKey());
            }
        }

        return adultNames;
    }

    /**
     * Returns the biggest number in a linked list.
     * 
     * @param head the head of the linked list
     * @return the biggest number in the list
     * @throws IllegalArgumentException if head is null
     */
    public static int biggestNumber(ListNode<Integer> head) {
        if (head == null) {
            throw new IllegalArgumentException("List cannot be empty");
        }

        int max = head.data;
        ListNode<Integer> current = head.next;

        while (current != null) {
            if (current.data > max) {
                max = current.data;
            }
            current = current.next;
        }

        return max;
    }

    /**
     * Returns a frequency map counting how frequently items appear in a linked list.
     * 
     * Example:
     *   Input: a -> x -> a -> a -> x -> y
     *   Output: {a:3, x:2, y:1}
     * 
     * Returns an empty map if head is null
     * 
     * @param <T> the type of data held by the list
     * @param head the head of the list
     * @return a frequency map of values in the list
     */
    public static <T> Map<T, Integer> frequencies(ListNode<T> head) {
        Map<T, Integer> freqMap = new HashMap<>();
        ListNode<T> current = head;

        while (current != null) {
            freqMap.put(current.data, freqMap.getOrDefault(current.data, 0) + 1);
            current = current.next;
        }

        return freqMap;
    }


    /**
     * Returns the number of levels in the tree.
     * 
     * An empty tree has 0 levels, a tree with only a root has 1 level.
     * 
     * @param root the root of the tree
     * @return the number of levels in the tree
     */
    public static int levelCount(BinaryTreeNode<?> root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = levelCount(root.left);
        int rightHeight = levelCount(root.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }


    /**
     * Returns the sum at a specified level in a binary tree.
     * 
     * For example, if the given level was 3:
     *       5
     *     /   \
     *    8     4
     *   / \   / 
     *  7  9  2
     *    /
     *   1
     * 
     * Nodes at level 3: 7, 9, and 2
     * Sum of nodes at level 3: 18 
     * 
     * The root is considered to be at level 1.
     * 
     * Returns 0 if the tree is empty or if the level is not present in the tree.
     * 
     * @param root the root of the binary tree
     * @param level the level to sum
     * @return the sum of the nodes at the given level
     */
    public static int sumAtLevel(BinaryTreeNode<Integer> root, int level) {
        if (root == null || level < 1) {
            return 0;
        }

        if (level == 1) {
            return root.data;
        }

        return sumAtLevel(root.left, level - 1) + sumAtLevel(root.right, level - 1);
    }


    /**
     * Returns true if the sum of the values in a given tree is equal to the sum
     * of the values in the given list. 
     * 
     * An empty tree or list is considered to have a sum of 0.
     * 
     * @param root The root of the binary tree
     * @param head The head of the linked list
     * @return true if the sums are equal, false otherwise
     */
    public static boolean sumMatch(BinaryTreeNode<Integer> root, ListNode<Integer> head) {
        return treeSum(root) == listSum(head);
    }

    //helper method
    private static int treeSum(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        return root.data + treeSum(root.left) + treeSum(root.right);
    }

    //helper method
    private static int listSum(ListNode<Integer> head) {
        int sum = 0;
        ListNode<Integer> current = head;
        while (current != null) {
            sum += current.data;
            current = current.next;
        }
        return sum;
    }
}