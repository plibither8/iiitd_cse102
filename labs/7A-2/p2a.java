import java.util.*;
public class BST {

	class Node {
		long data;
		Node left;
		Node right;
		int order;
		
		public Node(long data) {
			this.data = data;
			this.right = null;
			this.left = null;
		}
	}
	
	Node root;
	
	public Node search(Node root, long target) {
		Node current = root;
		while(current!=null) {
			if(current.data == target) {
				return current;
			} else if(current.data>target) {
				return search(current.left, target);
			} else {
				return search(current.right, target);
			}
		}
		return null;
	}
	
	public Node insert(Node root, long value) {
		if(root == null) {
			root = new Node(value);
		} else {
			if(value<root.data) {
				root.left = insert(root.left, value);
			} else {
				root.right = insert(root.right, value);
			}
		}
		return root;
	}
	
	public Node delete(Node root, long value) {
		if(root == null) {
			return null;
		}
		if(root.data>value) {
			root.left = delete(root.left, value);
		} else if(root.data<value) {
			root.right = delete(root.right, value);
		} else {
			if(root.left!=null && root.right!=null) {
				Node temp = root;
				Node minRight = minBST(temp.right);
				root.data = minRight.data;
				delete(root.right, minRight.data);
			} else if(root.left!=null) {
				root = root.left;
			} else if(root.right!=null) {
				root = root.right;
			} else {
				root = null;
			}
		}
		return root;
	}
	
	public Node minBST(Node root) {
		if(root.left == null) {
			return root;
		} else {
			return minBST(root.left);
		}
	}
	
	public Node maxBST(Node root) {
		if(root.right == null) {
			return root;
		} else {
			return maxBST(root.right);
		}
	}
	
	static int nodeCount=0;
	
	public Node countSmaller(Node root, long value) {
		if(root==null) {
			return null;
		}
		if(root.data == value) {
			return root;
		} else if(root.data < value) {
			Node temp = countSmaller(root.right, value);
			if(temp==null) {
				return root;
			} else {
				return temp;
			}
		} else if(root.data > value) {
			return countSmaller(root.left, value);
		}
		return null;
	}
	
	
	static int count = 1;
	
	public void setOrder(Node root) {
		if(root!=null) {
			setOrder(root.left);
			root.order = count;
			count++;
			setOrder(root.right);
		}
	}
	
	public long find(Node root, int k) {
		Node current = root;
		while(current!=null) {
			if(current.order == k) {
				return current.data;
			} else if(current.order>k) {
				return find(current.left, k);
			} else {
				return find(current.right, k);
			}
		}
		return -1;
	}
	
	public void display(Node root) {
		if(root!= null) {
			display(root.left);
			System.out.print(" "+ root.data);
			display(root.right);
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int q = in.nextInt();
		String[] s;
		BST bst = new BST();
		in.nextLine();
		for(int i = 0; i<q; i++) {
			//bst.display(bst.root);
			System.out.println();
			s = in.nextLine().split(" ");
			int a = Integer.parseInt(s[1]);
			if(s[0].equals("INSERT")) {
				if(bst.search(bst.root, a)==null) {
					bst.root = bst.insert(bst.root, a);
				}
			} else if(s[0].equals("DELETE")) {
				bst.root = bst.delete(bst.root, a);
			} else if(s[0].equals("FIND")) {
				bst.setOrder(bst.root);
				if(bst.maxBST(bst.root).order < a) {
					System.out.println("-1");
				} else {
					System.out.println(bst.find(bst.root, a));
				}
				count = 1;
			} else if(s[0].equals("COUNT")) {
				Node temp = bst.countSmaller(bst.root, a);
				if(temp==null) {
					System.out.println(0);
				} else {
					System.out.println(temp.order - 1);
				}
			}
		}
		in.close();
	}

}