// INCOMPLETE

import java.util.*;

public class Stack {
	Node top;
	class Node {
		Node next;
		int t;
		long n;
		String o;
		Node(String op) {
			t = 1;
			o = op;
		}
		Node(long num) {
			t = 0;
			n = num;
		}
	}
	static void push(Node n) {
		n.next=top;
		top=n;
	}
	static void pop() {
		if(top==null) return;
		top=top.next;
	}
	Stack(){ top = null }
	static boolean contains(String o) {
		for (String op : {"+", "-", "/", "*"}) {
			if (o.equals(op)) return true;
		}
		return false;
	}
	public static void main(String[] args) {
		Stack stack = new Stack();
		Scanner in = new Scanner(System.in);
		for (String ch:in.nextLine().split(" ")) {
			if(contains(ch)) nnode = Node(ch)
		}
	}
}
