import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.PriorityQueue;

public class Solution {
	static ArrayList<HashMap<Long, Boolean>> patrolTimes;

	public static void main(String[] args) throws IOException {
		Reader in = new Reader(System.in);
		int n=in.nextInt(),m=in.nextInt();

		ArrayList<ArrayList<Edge>> nodes = new ArrayList<ArrayList<Edge>>(n+1);
		for(int i=0;i<=n;i++) nodes.add(new ArrayList<Edge>());

		for(int i=0;i<m;i++){
			int a=in.nextInt();
			int b=in.nextInt();
			long c=in.nextLong();
			nodes.get(a).add(new Edge(b, c));
			nodes.get(b).add(new Edge(a, c));
		}

		patrolTimes = new ArrayList<HashMap<Long, Boolean>>(n+1);
		patrolTimes.add(new HashMap<Long, Boolean>());
		for(int i=0;i<n;i++){
			HashMap<Long, Boolean> curr=new HashMap<Long, Boolean>();
			int k=in.nextInt();
			while(k-->0){
				long x=in.nextLong();
				curr.put(i == n-1 ? (long)-1 : x, true);
			}
			patrolTimes.add(curr);
		}

		boolean[] done=new boolean[n+1];
		PriorityQueue<Edge> pq=new PriorityQueue<Edge>(n, new Edge());
		long[] distance=new long[n+1];
		for(int i=1;i<=n;i++)distance[i]=Long.MAX_VALUE;

		long initialWait = waitTime(1, 0);

		pq.offer(new Edge(1, initialWait));
		distance[1] = initialWait;

		while(pq.size()!=0){
			Edge currEdge = pq.poll();
			int currIndex = currEdge.target;

			done[currIndex]=true;
			if(currIndex==n) break;

			if(distance[currIndex] < currEdge.time) continue;

			for(Edge e : nodes.get(currIndex)){
				int to = e.target;

				if(done[to]){
					continue;
				}

				long time = waitTime(to, e.time + distance[currIndex]);

				if(time < distance[to]) {
					distance[to] = time;
					pq.add(new Edge(to, time));
				}
			}
		}

		System.out.println((!done[n]||distance[n]==Long.MAX_VALUE ) ? -1 : distance[n]);
	}

	static long waitTime(int uni, long time){
		if (patrolTimes.get(uni).get(time)!=null){
			return waitTime(uni, time+1);
		}
		return time;
	}
}

class Edge implements Comparator<Edge> {
	int target;
	long time;
	Edge(int t, long w){
		this.target=t;
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
