import java.util.*;

public class Queue {
	static Node front, rear;
	static int count = 0;
	static class Node {
		long data;
		Node next;
		Node(long d) {
			data = d;
			next = null;
		}
	}
	static void enqueue(long a) {
		Node newNode = new Node(a);
		if (rear == null) {
			front = rear = newNode;
		} else {
			rear.next = newNode;
			rear = newNode;
		}
		count++;
	}
	static void del(){
		Node temp=front;
		Node prev=null;
		while(temp!=null&&temp!=rear){
			if(temp.data<rear.data){
				if(prev==null) front=temp.next;
				else prev.next=temp.next;
				count--;
			} else {
				prev=temp;
			}
			temp=temp.next;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n=in.nextInt();
		Queue q=new Queue();
		for(int i=0;i<n;i++) {
			long newd=in.nextLong();
			q.enqueue(newd);
			q.del();
			System.out.println(q.count);
		}
	}
	Queue(){
		front = rear = null;
	}
}
