### LCA in a Binary Tree

https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/?envType=study-plan-v2&envId=top-interview-150
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/description/
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/description/
https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/description/


https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/description/

https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/description/

#### 31. **Find LCA in a Binary Tree** (good)

- Use recursion to find the lowest common ancestor by exploring left and right subtrees.

```java
    public void path(TreeNode node, TreeNode target, List<TreeNode> list, List<TreeNode> ans){
        if(node == null){
            return;
        }
        if(node == target){
            list.add(node);
            // ans = new ArrayList<TreeNode>(list);
            for(TreeNode n : list){
                ans.add(n);
            }
            return;
        }
        list.add(node);
        path(node.left, target, list, ans);
        path(node.right, target, list, ans);
        list.remove(node);
    }


    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> ans1 = new ArrayList<>();
        List<TreeNode> ans2 = new ArrayList<>();

        path(root, p, new ArrayList<>(), ans1);
  
        path(root, q, new ArrayList<>(), ans2);
  
  
        System.out.print(ans1.size());
        for(int i = ans1.size()-1 ; i >= 0; i--){
            System.out.println("check " + ans1.get(i).val);
            if(ans2.contains(ans1.get(i))){
                return ans1.get(i);
            }
        }

        System.out.println("ans root ");
        return root;

    }
```

https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/description/
https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/description/


### 32. **Find Distance Between Two Nodes in a Binary Tree** (good) (https://leetcode.com/problems/minimum-distance-between-bst-nodes/description/)

- Find LCA of the two nodes, then calculate the distance from LCA to each node.
