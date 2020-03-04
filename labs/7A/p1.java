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
	static void dequeue() {
		if (front==null){
			return;
		}
		front = front.next;
		count--;
		if (front==null) rear=null;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n=in.nextInt();
		Queue q=new Queue();
		for (int i=0;i<n;i++){
			switch(in.next()) {
				case "enqueue": {
					long a = in.nextLong();
					q.enqueue(a);
					break;
				}
				case "dequeue": {
					q.dequeue();
					break;
				}
				case "inc": {
					int x=in.nextInt();
					long d=in.nextLong();
					Node temp=front;
					int j=0;
					while(temp!=null){
						if(j<count-x) {
							temp=temp.next;
							j++;
							continue;
						};
						temp.data+=d;
						temp = temp.next;
						j++;
					}
					break;
				}
			}
			System.out.println(front==null?"EMPTY":front.data);
		}
	}
	Queue(){
		front = rear = null;
	}
}
