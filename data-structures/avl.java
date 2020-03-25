import java.util.*;

public class Tree {
	class Node {
		long data, height;
		Node left, right;

		Node(long d) {
			data = d;
			height = 1;
		}
	}

	Node root;

	long height(Node n) {
		return n == null ? 0 : n.height;
	}

	void updateHeight(Node n) {
		n.height = Math.max(height(n.right), height(n.left)) + 1;
	}

	Node rightRotate(Node y) {
		Node x = y.left;
		Node temp = x.right;

		x.right = y;
		y.left = temp;

		updateHeight(y);
		updateHeight(x);

		return x;
	}

	Node leftRotate(Node x) {
		Node y = x.right;
		Node temp = y.left;

		y.left = x;
		x.right = temp;

		updateHeight(x);
		updateHeight(y);

		return y;
	}

	long checkBalance(Node n) {
		return n == null ? 0 : height(n.left) - height(n.right);
	}

	Node insert(Node root, long val) {
		if (root == null) {
			return new Node(val);
		}

		if (val < root.data) {
			root.left = insert(root.left, val);
		} else if (val > root.data) {
			root.right = insert(root.right, val);
		} else {
			return root;
		}

		updateHeight(root);

		long check = checkBalance(root);

		if (check > 1 && val < root.left.data) {
			return rightRotate(root);
		}

		if (check < -1 && val > root.right.data) {
			return leftRotate(root);
		}

		if (check > 1 && val > root.left.data) {
			root.left = leftRotate(root.left);
			return rightRotate(root);
		}

		if (check < -1 && val < root.right.data) {
			root.right = rightRotate(root.right);
			return leftRotate(root);
		}

		return root;
	}

	public static void main(String[] args) {
		Tree tree = new Tree();
		Scanner in = new Scanner(System.in);
	}
}