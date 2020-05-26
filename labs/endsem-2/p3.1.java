import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.PriorityQueue;

public class Solution {
	static ArrayList<HashSet<Long>> patrolTimes;
	static ArrayList<ArrayList<Edge>> nodes;
	static long minDist = Long.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		Reader in = new Reader(System.in);
		int n=in.nextInt();
		int m=in.nextInt();

		nodes = new ArrayList<ArrayList<Edge>>(n+1);
		for(int i=0;i<=n;i++) nodes.add(new ArrayList<Edge>());

		for(int i=0;i<m;i++){
			int a=in.nextInt();
			int b=in.nextInt();
			long c=in.nextLong();
			nodes.get(a).add(new Edge(a, b, c));
			nodes.get(b).add(new Edge(b, a, c));
		}

		patrolTimes = new ArrayList<HashSet<Long>>(n+1);
		patrolTimes.add(new HashSet<Long>());
		for(int i=0;i<n;i++){
			HashSet<Long> curr=new HashSet<Long>();
			int k=in.nextInt();
			while(k-->0){
				curr.add(in.nextLong());
			}
			patrolTimes.add(curr);
		}

		boolean[] visited = new boolean[n+1];
		dfs(1, n, 0, visited);
		System.out.println(minDist);

		// System.out.println((!done[n]||distance[n]==Long.MAX_VALUE ) ? -1 : distance[n]);
	}

	static long waitTime(int uni, long time){
		if (patrolTimes.get(uni).contains(time)){
			return waitTime(uni, time+1);
		}
		return time;
	}

	static void dfs(int s, int n, long dist, boolean[] visited) {
		visited[s] = true;

		if (dist >= minDist) {
			visited[s] = false;
			return;
		};

		if (s == n) {
			visited[s] = false;
			minDist = dist;
			return;
		}

		long newDist = dist + waitTime(s, 0);

		for (Edge nbr : nodes.get(s)) {
			if (!visited[nbr.dest]) {
				dfs(nbr.dest, n, newDist + nbr.time, visited);
			}
		}

		visited[s] = false;
	}
}

class Edge implements Comparator<Edge> {
	int src;
	int dest;
	long time;
	Edge(int s, int d, long w){
		this.src=s;
		this.dest=d;
		this.time=w;
	}
	Edge(){}
	@Override
	public int compare(Edge a, Edge b){
		if(a.time < b.time) return -1;
		if(a.time > b.time) return 1;
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

// boolean[] done=new boolean[n+1];
// PriorityQueue<Edge> pq=new PriorityQueue<Edge>(n, new Edge());
// long[] distance=new long[n+1];
// for(int i=1;i<=n;i++)distance[i]=Long.MAX_VALUE;

// pq.offer(new Edge(0, 1, initialWait));
// distance[1] = initialWait;

// while(pq.size()!=0){
// 	Edge currEdge = pq.poll();
// 	int currIndex = currEdge.dest;

// 	// System.out.println(currIndex+" : "+distance[currIndex]);
// 	// if(done[currIndex]){
// 	// 	continue;
// 	// }
// 	done[currIndex]=true;

// 	if(distance[currIndex] < waitTime(currIndex, currEdge.src)) {
// 		continue;
// 	}

// 	for(Edge e : nodes.get(currIndex)){
// 		int dest = e.dest;
// 		long time = dest == n ? e.time : waitTime(dest, e.time);
// 		System.out.println(currIndex + " -> " + dest);

// 		if(distance[currIndex] + time < distance[dest]) {
// 			distance[dest] = distance[currIndex] + time;
// 		}
// 		pq.add(new Edge(currIndex, dest, distance[currIndex]+time));
// 	}
// }