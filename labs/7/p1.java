import java.util.*;

class CLL {
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
		if (head == null) {
			head = current = newNode;
			head.next = head;
		} else {
			newNode.next = current.next;
			current.next = newNode;
		}
	}

	static void step() {
		current = current.next;
	}

	static void print() {
		System.out.println(current.data);
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
					cll.step();
					break;
				case 3:
					cll.print();
					break;
			}
		}
	}
}
