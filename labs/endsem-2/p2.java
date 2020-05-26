import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.PriorityQueue;

public class Solution {
	public static void main(String[] args) throws IOException {
		Reader in = new Reader(System.in);
		int n=in.nextInt(),m=in.nextInt();

		ArrayList<ArrayList<Edge>> nodes=new ArrayList<ArrayList<Edge>>(n+1);
		for(int i=0;i<=n;i++)nodes.add(new ArrayList<Edge>());

		for(int i=0;i<m;i++){
			int a=in.nextInt();
			int b=in.nextInt();
			long c=in.nextLong();
			nodes.get(a).add(new Edge(b, c));
			nodes.get(b).add(new Edge(a, c));
		}

		for(int i=0;i<n;i++)in.nextInt();

		boolean[] done=new boolean[n+1];
		PriorityQueue<Edge> pq=new PriorityQueue<Edge>(n, new Edge());
		long[] distance=new long[n+1];
		for(int i=1;i<=n;i++)distance[i]=Long.MAX_VALUE;

		pq.offer(new Edge(1, 0));
		distance[1] = 0;

		while(pq.size()!=0){
			Edge currEdge = pq.poll();
			int currIndex=currEdge.t;

			if(done[currIndex])continue;
			done[currIndex]=true;

			for(Edge e : nodes.get(currIndex)){
				int to = e.t;
				long weight = e.w;
				if(distance[currIndex] + weight < distance[to]) {
					distance[to] = distance[currIndex] + weight;
					pq.add(new Edge(to, distance[to]));
				}
			}
		}

		System.out.println(!done[n]||distance[n]==Long.MAX_VALUE?-1:distance[n]);
	}
}

class Edge implements Comparator<Edge> {
	int t;
	long w;
	Edge(int t, long w){
		this.t=t;
		this.w=w;
	}
	Edge(){}
	@Override
	public int compare(Edge a, Edge b){
		if(a.w<b.w) return -1;
		if(a.w>b.w)return 1;
		return 0;
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

	static int nextInt() throws IOException { return Integer.parseInt(next()); }

	static double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}

	static long nextLong() throws IOException { return Long.parseLong(next()); }
}
