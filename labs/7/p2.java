import java.util.*;

class CLL {
	static Node head = null;
	static Node first = null;
	static Node middle = null;
	static long count = 0;

	static class Node {
		long data;
		Node next = null;
		Node prev;

		Node(long d) {
			data = d;
			next = prev = null;
		}
	}

	static void add(long val) {
		Node newNode = new Node(val);
		count++;
		if (head == null) {
			head = first = middle = newNode;
			head.prev = null;
		} else {
			head.next = newNode;
			newNode.prev = head;
			head = newNode;
			if (count % 2 == 1) {
				middle = middle.next;
			}
		}
	}

	static void del() {
		count--;
		first.next.prev = null;
		first = first.next;
		if (count % 2 == 1) {
			middle = middle.next;
		}
	}

	static void print() {
		System.out.print(middle.data);
		if (count % 2 == 0) System.out.print(" "+middle.next.data);
		System.out.println();
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int q = in.nextInt();

		CLL cll = new CLL();
		cll.add(1);

		for (int i = 0; i < q;i++) {
			switch (in.nextInt()) {
				case 1:
					cll.add(in.nextInt());
					break;
				case 2:
					cll.del();
					break;
				case 3:
					cll.print();
					break;
			}
		}
	}
}
