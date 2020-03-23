import java.util.*;

public class BST {
	class Node {
		int data;
		Node left;
		Node right;

		public Node(int d) {
			data = d;
		}
	}

	Node root;

	int pin = 0;
	public Node treer(int ino[], int pre[], int low, int high) {
		if(low>high) return null;

		Node cur = new Node(pre[pin]);
		pin++;

		if(low==high) return cur;
		else {
			int inoI = indexOf(cur.data, ino, low, high);
			cur.left = treer(ino, pre, low, inoI-1);
			cur.right = treer(ino, pre, inoI+1, high);
		}

		return cur;
	}

	public int indexOf(int val, int a[], int low, int high) {
		for(int i = low;i<=high; i++) {
			if(a[i]==val) return i;
		}
		return -1;
	}

	public void postOrder(Node n) {
		if(n!=null) {
			postOrder(n.left);
			postOrder(n.right);
			System.out.print(n.data+" ");
		}
	}

	public static boolean check(Node n, int min, int max) {
		if(n == null) return true;
		return(n.data>min && n.data<max && check(n.left, min, n.data) && check(n.right, n.data, max));
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] pre = new int[n];
		int[] ino = new int[n];
		for(int i = 0; i<n; i++) pre[i] = in.nextInt();
		for(int i = 0; i<n; i++) ino[i] = in.nextInt();
		BST tree = new BST();
		tree.root = tree.treer(ino, pre, 0, n-1);
		tree.postOrder(tree.root);
		System.out.println();
		if(check(tree.root, Integer.MIN_VALUE, Integer.MAX_VALUE)) System.out.println("YES");
		else System.out.println("NO");
	}
}
