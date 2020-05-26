import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		Reader in = new Reader(System.in);
		int t=in.nextInt();
		for(int i=0;i<t;i++){
			int n=in.nextInt(),l=in.nextInt(),k=in.nextInt();
			Loc[] locs=new Loc[n+1];

			for(int j=0;j<l;j++){
				int u=in.nextInt();
				int v=in.nextInt();
				if(locs[u]==null&&locs[v]==null){
					locs[u]=locs[v]=new Loc();
				}else if(locs[u]==null){
					locs[u]=locs[v];
				}else{
					locs[v]=locs[u];
				}
			}

			for(int j=1;j<=n;j++){
				if(locs[j]==null){
					locs[j]=new Loc();
				}
			}

			boolean[] done=new boolean[n+1];
			PriorityQueue<Edge> pq=new PriorityQueue<Edge>(n, new Edge());
			int doneCount=0;
			long[] distance=new long[n+1];
			for(int j=1;j<=n;j++)distance[j]=Integer.MAX_VALUE;

			ArrayList<ArrayList<Edge>> nodes=new ArrayList<ArrayList<Edge>>(n+1);
			for(int j=0;j<=n;j++)nodes.add(new ArrayList<Edge>());

			for(int j=0;j<k;j++){
				int a=in.nextInt();
				int b=in.nextInt();
				long c=in.nextLong();
				if(locs[a]==locs[b]) {
					nodes.get(a).add(new Edge(b, 0));
					nodes.get(b).add(new Edge(a, 0));
				}
				else {
					nodes.get(a).add(new Edge(b, c));
					nodes.get(b).add(new Edge(a, c));
				}
			}

			pq.add(new Edge(1, 0));
			distance[1] = 0;

			while(pq.size()>0){
				int target=pq.remove().t;
				if(done[target])continue;
				done[target]=true;
				doneCount++;

				for(Edge e : nodes.get(target)){
					int b=e.t;
					long w=e.w;
					if(distance[target]+w<distance[b]){
						distance[b]=distance[target]+w;
						pq.add(new Edge(b, -distance[b]));
					}
				}
			}

			long max=0;
			for(long d:distance){
				if(d>max)max=d;
			}

			System.out.println(max);
		}
	}
}

class Loc{
	int open=1;
}

class Edge implements Comparator<Edge> {
	int t;
	long w;
	Edge(int t, long w){
		this.t=t;
		this.w=w;
	}
	Edge(){}
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
