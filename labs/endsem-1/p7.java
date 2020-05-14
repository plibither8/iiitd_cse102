import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class Solution {
	static Node[] nodes;
	static boolean escaped=false;
	static int e;

	public static void main(String[] args) throws IOException {
		Reader in = new Reader(System.in);
		int n = in.nextInt();
		int s = in.nextInt();
		int q = in.nextInt();
		e = in.nextInt();

		nodes = new Node[n+1];
		for (int i=1;i<=n;i++) nodes[i]=new Node(i);
		int[][] tuns=new int[2*n][2];

		for(int i=1;i<n;i++){
			int u = in.nextInt();
			int v = in.nextInt();in.next();
			tuns[i]=new int[]{u,nodes[u].nbrs.size()};
			tuns[i+1]=new int[]{v,nodes[v].nbrs.size()};
			nodes[u].nbrs.add(nodes[v]);
			nodes[v].nbrs.add(nodes[u]);
		}

		for (int i=0;i<s;i++) {
			in.next();
		}

		for(int i=0;i<q;i++){
			boolean[] visited = new boolean[n+1];
			escaped=false;

			int t=in.nextInt();
			int r=in.nextInt();

			nodes[tuns[t][0]].nbrs.set(tuns[t][1],null);
			nodes[tuns[t+1][0]].nbrs.set(tuns[t+1][1],null);
			Node subtree=nodes[r];

			dfs(visited,subtree);

			if (escaped) {
				System.out.println("escaped");
				continue;
			}

			System.out.println(0);
		}
	}

	static void dfs(boolean[] visited, Node n){
		if(n==null)return;
		if(escaped)return;
		if(visited[n.b])return;
		visited[n.b]=true;
		if(n.b==e){
			escaped=true;
			return;
		}

		for(Node nbr:nodes[n.b].nbrs){
			dfs(visited,nbr);
		}
	}
}

class Node {
	int b;
	ArrayList<Node> nbrs=new ArrayList<Node>();
	Node(int b){
		this.b=b;
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

