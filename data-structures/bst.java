import java.util.*;

class Soln {
	static class Node {
		long data;
		Node left, right, parent;
		int children = 0;

		Node(long d) {
			data = d;
		}

		Node(long d, Node p) {
			data = d;
			parent = p;
		}
	}

	static class Tree {
		Node root;

		private Node insert(Node n, long data, Node p) {
			if (n == null) {
				return new Node(data, p);
			}

			if (data < n.data) n.left = insert(n.left, data, n);
			else n.right = insert(n.right, data, n);

			n.children++;
			return n;
		}

		public Node insert(long data) {
			return insert(root, data, root);
		}

		public void print(Node l) {
			System.out.print("(");
			if (l.left!=null) {
				if (l.left.children > 0) print(l.left);
				else System.out.print(l.left.data);
			}
			else System.out.print("*");
			System.out.print(","+l.data+",");
			if (l.right!=null) {
				if (l.right.children > 0) print(l.right);
				else System.out.print(l.right.data);
			}
			else System.out.print("*");
			System.out.print(")");
		}

		public delete(Node n) {
			if (n.children == 0) {
				if (n.parent.left == n) n.parent.left = null;
				else n.parent.right = null;
				return;
			}

			if (n.children == 1) {
				if (n.children.left != null) {
					if (n.parent.left == n) {
						n.parent.left = n.left;
					} else {
						n.parent.right = n.left;
					}
				} else {
					if (n.parent.left == n) {
						n.parent.left = n.right;
					} else {
						n.parent.right = n.right;
					}
				}
				return;
			}
		}

		Tree(long d) {
			root = new Node(d);
		}
	}

	public static void main(String[] args) {
		Tree origin = new Tree(5);
		long[] keys = {8,8,2,6,4,6,9,1,4,2};
		for (long key : keys) origin.insert(key);
		origin.print(origin.root);
	}
}
