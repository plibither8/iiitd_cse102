import java.util.*;

public class Tree {
	public static class Node {
		long data;
		Node left, right;
		int lcount, rcount;
		Node(long d) {
			data=d;
			smallCount=0;
		}
	}
	static Node root;
	static Boolean search(Node n, long data) {
		if (n==null) return false;
		if (n.data==data) return true;
		return search(n.left, data) || search(n.right, data);
	}
	static Node insert(Node n, long data) {
		if (n == null) {
			return new Node(data);
		}
		if (data < n.data) n.left = insert(n.left, data);
		else n.right = insert(n.right, data);
		return n;
	}	
	static Node delete(Node n, long value) {
		if(n == null) return null;
		if(n.data>value) {
			n.left = delete(n.left, value);
		} else if(n.data<value) {
			n.right = delete(n.right, value);
		} else {
			if(n.left!=null && n.right!=null) {
				Node temp = n;
				Node minRight = minBST(temp.right);
				n.data = minRight.data;
				delete(n.right, minRight.data);
			} else if(n.left!=null) {
				n = n.left;
			} else if(n.right!=null) {
				n = n.right;
			} else {
				n = null;
			}
		}
		return n;
	}
	static Node minBST(Node root) {
		if(root.left == null) {
			return root;
		} else {
			return minBST(root.left);
		}
	}
	public static void main(String[] args) {
		Tree tree = new Tree();
		Scanner in = new Scanner(System.in);
		int n=in.nextInt();
		for(int i=0;i<n;i++){
			switch(in.next()){
				case "INSERT": {
					long x=in.nextLong();
					if (!tree.search(tree.root)) {
						tree.root = tree.insert(tree.root);
					}
					break;
				}
				case "DELETE": {
					long x = in.nextLong();
					bst.root = bst.delete(bst.root, x);
					break;
				}
				case "FIND": {
					int k=in.nextInt();
					
					break;
				}
				case "COUNT": {
					int count=0;
					long x=in.nextLong();
					
					break;
				}
			}
		}
	}
}
