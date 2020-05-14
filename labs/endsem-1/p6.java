import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class Solution {
	static Node[] adj;
	static boolean escaped=false;
	static ArrayList<Long> distances;

	public static void main(String[] args) throws IOException {
		Reader in = new Reader(System.in);
		int n = in.nextInt();
		int s = in.nextInt();
		int q = in.nextInt();
		int e = in.nextInt();

		adj = new Node[n+1];
		for (int i=1;i<=n;i++) adj[i]=new Node();

		for(int i=1;i<n;i++){
			int u = in.nextInt();
			int v = in.nextInt();
			long w = in.nextLong();
			adj[u].addNeighbour(v, w, i);
			adj[v].addNeighbour(u, w, i);
		}

		adj[e].exit=true;

		for (int i=0;i<s;i++) {
			adj[in.nextInt()].supply=true;
		}

		for(int i=0;i<q;i++){
			boolean[] visited = new boolean[n+1];
			escaped=false;
			distances=new ArrayList<Long>();

			int t=in.nextInt();
			int r=in.nextInt();

			Queue queue=new Queue();
			queue.front=queue.rear=null;
			queue.count=0;

			visited[r]=true;
			queue.enqueue(r);

			while(queue.front!=null&&!escaped){
				int f = queue.front.data;
				if(f==e) {
					escaped=true;
					break;
				}
				queue.dequeue();

				for (Nbr nbr : adj[f].nbrs) {
					int u=nbr.v;
					if(visited[u]||nbr.i==t) continue;
					visited[u]=true;
					queue.enqueue(u);
				}
			}

			if (escaped) {
				System.out.println("escaped");
				continue;
			}

			visited = new boolean[n+1];

			supply_dfs(r, t, 0, 0);
			if (distances.size() == 0) {
				System.out.println("oo");
			} else {
				long min = Long.MAX_VALUE;
				for (long l : distances) min = l < min ? l : min;
				System.out.println(min);
			}
		}
	}

	public static void supply_dfs(int s, int t, long dist, int p) {
		Node currNode = adj[s];
		if (currNode == null) return;

		if (currNode.supply) {
			distances.add(dist);
			return;
		}

		for (Nbr nbr : currNode.nbrs) {
			if (nbr.i != t && nbr.v != p) supply_dfs(nbr.v, t, dist+nbr.w, s);
		}
	}
}

class Nbr {
	int v;
	long w;
	int i;
	Nbr(int v, long w, int i){
		this.v=v;
		this.w=w;
		this.i=i;
	}
}

class Node {
	ArrayList<Nbr> nbrs = new ArrayList<Nbr>();
	boolean exit=false;
	boolean supply=false;
	void addNeighbour(int v, long w, int i){
		nbrs.add(new Nbr(v, w, i));
	}
}

class Queue {
	static Item front, rear;
	static int count = 0;
	static void enqueue(int a) {
		Item newItem = new Item(a);
		if (rear == null) {
			front = rear = newItem;
		} else {
			rear.next = newItem;
			rear = newItem;
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
	static void print()  {
		Item next=front;
		while(next!=null){
			System.out.print(next.data+" ");
			next=next.next;
		}
		System.out.println();
	}
}

class Item {
	int data;
	Item next;
	Item(int d) {
		data = d;
		next = null;
	}
}

class Reader {
	static BufferedReader reader;
	static StringTokenizer tokenizer;

	Reader(InputStream input) {
		reader = new BufferedReader(new InputStreamReader(input));
		tokenizer = new StringTokenizer("");
	}

	static String next() throws IOException {
		while (!tokenizer.hasMoreTokens()) {
			tokenizer = new StringTokenizer(reader.readLine());
		}
		return tokenizer.nextToken();
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}

	static long nextLong() throws IOException {
		return Long.parseLong(next());
	}
}

