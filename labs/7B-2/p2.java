import java.util.*;

public class Tree {
	static class Node {
		long data, height;
		Node left, right;

		Node(long d) {
			data = d;
			height = 1;
		}
	}

	static Node root;

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

	Node insert(Node n, long val) {
		if (n == null) {
			return new Node(val);
		}

		if (val < n.data) {
			n.left = insert(n.left, val);
		} else if (val > n.data) {
			n.right = insert(n.right, val);
		} else {
			return n;
		}

		updateHeight(n);

		long check = checkBalance(n);

		if (check > 1 && val < n.left.data) {
			cr++;
			return rightRotate(n);
		}

		if (check < -1 && val > n.right.data) {
			cl++;
			return leftRotate(n);
		}

		if (check > 1 && val > n.left.data) {
			cl++;
			cr++;
			n.left = leftRotate(n.left);
			return rightRotate(n);
		}

		if (check < -1 && val < n.right.data) {
			cl++;
			cr++;
			n.right = rightRotate(n.right);
			return leftRotate(n);
		}

		return n;
	}

	public static int f = 0;
	public static long cl = 0;
	public static long cr = 0;

	public static void parent(long val, Node n) {
		if (f==1) return;
		if (n==null) return;
		if ((n.left != null && n.left.data == val) ||
			(n.right != null && n.right.data == val))
		{
			f=1;
			System.out.println(n == root ? 0 : n.data);
			return;
		}
		parent(val, n.left);
		parent(val, n.right);
	}

	public void preOrderDisplay(Node n) {
		if(n!=null) {
			System.out.print(" "+ n.data);
			preOrderDisplay(n.left);
			preOrderDisplay(n.right);
		}
	}

	public static void main(String[] args) {
		Tree tree = new Tree();
		Scanner in = new Scanner(System.in);
		long n=in.nextLong();
		for (long i=0;i<n;i++){
			switch(in.next()) {
				case "ADD": {
					if (tree.root == null) {
						tree.root = new Node(in.nextLong());
					} else {
						tree.insert(tree.root, in.nextLong());
					}
					break;
				}
				case "PARENT": {
					parent(in.nextLong(), tree.root);
					if (f==0) {
						System.out.println(-1);
					}
					break;
				}
				case "COUNT": {
					switch(in.next()) {
						case "R": {
							System.out.println(tree.cr);
							break;
						}
						case "L": {
							System.out.println(tree.cl);
							break;
						}
					}
					break;
				}
			}
		}
	}
}