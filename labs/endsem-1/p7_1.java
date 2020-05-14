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

	static long[] intime;
	static long[] outtime;
	static long time=0;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		Reader in = new Reader(System.in);
		int n = in.nextInt();
		int s = in.nextInt();
		int q = in.nextInt();
		int e = in.nextInt();

		adj = new Node[n+1];
		for (int i=1;i<=n;i++) adj[i]=new Node();
		int[][] tuns=new int[n][2];

		for(int i=1;i<n;i++){
			int u = in.nextInt();
			int v = in.nextInt();in.next();
			tuns[i]=new int[]{u,v};
			adj[u].nbrs.add(new Nbr(v, i));
			adj[v].nbrs.add(new Nbr(u, i));
		}

		for (int i=0;i<s;i++) in.nextInt();

		visited= new boolean[n+1];
		time=0;
		intime=new long[n+1];
		outtime=new long[n+1];
		exit_dfs(visited, e);

		for(int i=0;i<q;i++){
			int t=in.nextInt();
			int r=in.nextInt();

			int x;
			if(subset(tuns[t][0], tuns[t][1])){
				x=tuns[t][0];
			} else {
				x=tuns[t][1];
			}

			if (x==r) {
				System.out.println(0);
			}

			else {
				if (subset(r,x)&&subset(x,e)){
					System.out.println(0);
				} else {
					System.out.println("escaped");
				}
			}
		}
	}

	static boolean subset(int a, int b){
		return intime[a]>intime[b]&&outtime[a]<outtime[b];
	}

	public static void exit_dfs(boolean[] visited, int s) {
		if (visited[s]) return;
		time+=1;
		visited[s] = true;
		intime[s]=time;

		Node currNode = adj[s];
		if (currNode == null) return;

		for (Nbr nbr : currNode.nbrs) {
			if (!visited[nbr.v]) exit_dfs(visited, nbr.v);
		}
		time+=1;
		outtime[s]=time;
	}
}

class Nbr {
	int v;
	int i;
	Nbr(int v, int i){
		this.v=v;
		this.i=i;
	}
}

class Node {
	ArrayList<Nbr> nbrs = new ArrayList<Nbr>();
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

