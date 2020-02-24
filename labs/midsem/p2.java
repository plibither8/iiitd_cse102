import java.util.*;

public class LL {
	static Node head = null;
	static Node current = null;

	static class Node {
		long data;
		Node next;
		Node(long d) {
			data = d;
			next = null;
		}
	}

	static void add(long val) {
		Node newNode = new Node(val);
		newNode.next = current.next;
		current.next = newNode;
	}

	static void addInit(long val){
		Node newNode =  new Node(val);
		if (head == null) {
			head = current = newNode;
		} else {
			head.next = newNode;
			head = newNode;
		}
	}

	static void step() {
		current = current.next == null ? current : current.next;
	}

	static void print() {
		System.out.println(current.data);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int q = in.nextInt();
		LL list = new LL();
		for (int i=0;i<n;i++)LL.addInit(in.nextLong());
		for (int i = 0; i < q;i++) {
			switch (in.nextInt()) {
				case 1:
					list.print();
					break;
				case 2:
					list.step();
					break;
				case 3:
					list.add(in.nextLong());
					break;
			}
		}
	}
}
