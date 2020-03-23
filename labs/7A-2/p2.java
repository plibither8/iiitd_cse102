import java.util.*;

public class Solution {
	public static class Node {
		long data;
		Node next;
		Node(long d) {
			data=d;
		}
	}
	static Node root;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n=in.nextInt();
		for(int i=0;i<n;i++){
			switch(in.next()){
				case "INSERT": {
					if (root == null) {
						root = new Node(in.nextLong());
					} else {
						long x=in.nextLong();
						Node t=root;
						Node g=null;
						int abort=0;
						while (t!=null&&x>=t.data) {
							if (x==t.data) {
								abort=1;
								break;
							}
							g=t;
							t=t.next;
						}
						if (abort==0) {
							Node k=new Node(x);
							if(g!=null) {
								k.next=t;
								g.next=k;
							} else {
								k.next=root;
								root=k;
							}
						}
					}
					break;
				}
				case "DELETE": {
					long x = in.nextLong();
					Node t=root;
					Node p=null;
					while(t.next!=null){
						if (t.data==x) {
							if(p==null) {
								root=t.next;
							} else {
								p.next=t.next;
							}
							break;
						}
						p=t;
						t=t.next;
					}
					break;
				}
				case "FIND": {
					int k=in.nextInt();
					Node t=root;
					Node g=null;
					int b=0;
					for(int m=0;m<k;m++){
						if(t==null) {
							b=1;
							System.out.println(-1);
							break;
						}
						g=t;
						t=t.next;
					}
					if(b==0)System.out.println(g.data);
					break;
				}
				case "COUNT": {
					int count=0;
					long x=in.nextLong();
					Node t=root;
					while(t!=null&&t.data<x){
						count++;
						t=t.next;
					}
					System.out.println(count);
					break;
				}
			}
		}
	}
}
