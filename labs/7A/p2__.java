import java.util.*;

public class DLL {
	static Node front,rear;
	static int count = 0;
	static class Node {
		long data;
		Node prev;
		Node(long d) {
			data = d;
			prev = null;
		}
	}
	static void add(long a) {
		Node newNode = new Node(a);
		if (rear == null) {
			front = rear = newNode;
			count=1;
			return;
		}
		newNode.prev=rear;
		rear=newNode;
		count++;
		while(rear.prev!=null&&rear.prev.data<rear.data){
			rear.prev=rear.prev.prev;
			count--;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n=in.nextInt();
		DLL q=new DLL();
		for(int i=0;i<n;i++) {
			long newd=in.nextLong();
			q.add(newd);
			// q.del();
			System.out.println(q.count);
		}
	}
	DLL(){
		front = rear = null;
	}
}
