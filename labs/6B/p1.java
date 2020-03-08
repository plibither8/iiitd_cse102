import java.util.*;

public class Queue {
	Node front, rear;
	static class Node {
		Node next;
		long t;
		Node(long ts){
			t=ts;
			next=null;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long tasks[] = new long[in.nextInt()];
		long q = in.nextLong();
		Queue queue=new Queue();
		for (int i=0;i<q;i++){
			if(in.nextInt()==1){
				Node task=new Node(in.nextLong(), in.nextLong());
				if(queue.front==null){
					queue.front=queue.rear=task;
				}else{
					queue.rear.next=task;
					queue.rear=task;
				}
			}else {
				int p=in.nextInt();
				while(queue.front!=null){
					if(i<queue.front.t) {
						tasks[p]++;
						queue.front=queue.front.next;
						break;
					}
					queue.front=queue.front.next;
				}
			}
		}
		for(long c:tasks) System.out.println(c);
	}
}
