package forfun;

public class LowestCommonAncestor {
	public TreeNode findLCA(TreeNode root, int v1, int v2) {
		if (root == null) {
			return null;
		}

		if (root.val == v1 || root.val == v2) {
			return root;
		}

		TreeNode left = findLCA(root.left, v1, v2);
		TreeNode right = findLCA(root.right, v1, v2);

		if (left != null && right != null) {
			return root;
		}

		return (left != null) ? left : right;
	}

	public static void main(String[] args) {

	}
}

class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int v) {
		this.val = v;
		this.left = null;
		this.right = null;
	}
}