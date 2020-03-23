import java.util.*;

public class Solution {
	static class Node {
		long data, sum;
		Node left, right;

		Node(long d) {
			data = d;
			sum = 0;
		}
	}

	static class Tree {
		Node root;

		private Node insert(Node n, long data) {
			if (n == null) {
				return new Node(data);
			}

			if (data <= n.data) n.left = insert(n.left, data);
			else n.right = insert(n.right, data);

			return n;
		}

		public Node insert(long data) {
			if (root == null) {
				root = new Node(data);
				return root;
			}

			return insert(root, data);
		}

		public long sum(Node l) {
			if (l == null) {
				return 0;
			}
			l.sum = sum(l.left) + sum(l.right);
			return l.sum + l.data;
		}
		public void ps(Node l){
			if(l!=null) {
				ps(l.left);
				System.out.println(l.sum);
				ps(l.right);
			}
		}
	}
	public static void main(String[] args) {
		Tree tree = new Tree();

		Scanner in = new Scanner(System.in);
		long n = in.nextLong();
		for(int i=0;i<n;i++) tree.insert(in.nextLong());

		tree.sum(tree.root);
		tree.ps(tree.root);
	}
}
