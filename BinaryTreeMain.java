import java.util.Stack;

class Node {
    char data;
    Node left, right;

    public Node(char data) {
        this.data = data;
        left = right = null;
    }
}

class BinaryTree {
    Node root;
    int index;

    // 通过先根序列创建二叉树
    public Node createTree(char[] arr) {
        Node node = null;
        if (index < arr.length && arr[index] != '^') {
            node = new Node(arr[index]);
            index++;
            node.left = createTree(arr);
            index++;
            node.right = createTree(arr);
        }
        return node;
    }

    // 前序遍历
    public void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }


    //中序遍历
    public void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.data + " ");
            inOrderTraversal(node.right);
        }
    }
//后序遍历
    public void postOrderTraversal(Node node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.print(node.data + " ");
        }
    }

    // 计算二叉树的结点数
    public int countNodes(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.left) + countNodes(node.right);
    }
    //计算树的高度
    public int height(Node node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }
    // 计算二叉树中度为 1 的结点数
    public int countDegree1Nodes(Node node) {
        if (node == null) {
            return 0;
        }
        int count = 0;
        if (node.left != null && node.right != null) {
            count = countDegree1Nodes(node.left) + countDegree1Nodes(node.right);
        } else if (node.left != null || node.right != null) {
            count = 1 + countDegree1Nodes(node.left) + countDegree1Nodes(node.right);
        }
        return count;
    }

    // 计算二叉树中度为 2 的结点数
    public int countDegree2Nodes(Node node) {
        if (node == null) {
            return 0;
        }
        int count = 0;
        if (node.left != null && node.right != null) {
            count = 1 + countDegree2Nodes(node.left) + countDegree2Nodes(node.right);
        } else {
            count = countDegree2Nodes(node.left) + countDegree2Nodes(node.right);
        }
        return count;
    }

    // 计算二叉树中叶子数
    public int countLeaves(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        return countLeaves(node.left) + countLeaves(node.right);
    }

    int find(char[] inorder, int start, int end, char val) {
        for (int i = start; i <= end; i++) {
            if (inorder[i] == val) {
                return i;
            }
        }
        return -1;
    }



    public Node createTree(char[] preorder, char[] inorder) {
        return createTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private Node createTree(char[] preorder, int preStart, int preEnd, char[] inorder, int inStart, int inEnd) {
        // 如果先根遍历序列或中序遍历序列为空，则返回 null
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        // 创建当前结点，其值为先根遍历序列的第一个元素
        Node node = new Node(preorder[preStart]);
        // 查找中序遍历序列中当前结点的位置
        int inIndex = find(inorder, inStart, inEnd, preorder[preStart]);
        // 递归地构造左子树
        node.left = createTree(preorder, preStart + 1, preStart + inIndex - inStart, inorder, inStart, inIndex - 1);
        // 递归地构造右子树
        node.right = createTree(preorder, preStart + inIndex - inStart + 1, preEnd, inorder, inIndex + 1, inEnd);
        return node;
    }



}



public class BinaryTreeMain {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        char[] arr = {'A', 'B', '^', 'D', '^', '^', 'C', '^', '^'};
        tree.root = tree.createTree(arr);
        System.out.print("前序遍历");
        tree.preOrderTraversal(tree.root);
        System.out.print("\n中序遍历");
        tree.inOrderTraversal(tree.root);
        System.out.print("\n后序遍历");
        tree.postOrderTraversal(tree.root);
        System.out.print("\n结点数为"+tree.countNodes(tree.root));
        System.out.print("\n树的高度"+tree.height(tree.root));
        System.out.print("\n度为 1 的结点数: " + tree.countDegree1Nodes(tree.root));
        System.out.print("\n度为 2 的结点数: " + tree.countDegree2Nodes(tree.root));
        System.out.print("\n叶子数: " + tree.countLeaves(tree.root));
        //最后一题
        char[] preorder = {'A', 'B', 'H', 'F', 'D', 'E', 'C', 'K', 'G'};
        char[] inorder = {'H', 'B', 'D', 'F', 'A', 'E', 'C', 'K', 'G'};
        tree.root = tree.createTree(preorder, inorder);

    }
}
