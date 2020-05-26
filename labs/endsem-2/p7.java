import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
	static Node[] nodes;
	static boolean[] visited;
	static long maxLength = 0;
	static int far1, far2;
	static long[] dist1;
	static long[] dist2;

	public static void main(String[] args) throws IOException {
		Reader in = new Reader(System.in);
		int n = in.nextInt(),
			h = in.nextInt(),
			x = in.nextInt();

		nodes = new Node[n+1];
		for (int i = 1; i <= n; i++) {
			nodes[i] = new Node();
		}

		for (int i=0; i<h;i++) {
			nodes[in.nextInt()].hotspot = true;
		}

		for (int i=0; i<n-1;i++){
			int a=in.nextInt();
			int b=in.nextInt();
			nodes[a].nbrs.add(b);
			nodes[b].nbrs.add(a);
		}

		dist1 = new long[n+1];
		dist2 = new long[n+1];
		visited = new boolean[n+1];

		dfs1(1, 0);

		visited = new boolean[n+1];
		maxLength = 0;
		dfs2(far1, 0);

		visited = new boolean[n+1];
		dfs3(far2, 0);

		long count = 0;

		for (int i=1; i <= n;i++){
			if (dist1[i] <= x && dist2[i] <= x) {
				count++;
			}
		}

		System.out.println(count);
	}

	static void dfs1 (int s, long length) {
		visited[s] = true;
		for (int n : nodes[s].nbrs) {
			if (!visited[n]) {
				dfs1(n, length + 1);
			}
		}
		if (nodes[s].hotspot && length >= maxLength) {
			maxLength = length;
			far1 = s;
		}
	}

	static void dfs2 (int s, long length) {
		visited[s] = true;
		dist1[s] = length;
		for (int n : nodes[s].nbrs) {
			if (!visited[n]) {
				dfs2(n, length + 1);
			}
		}
		if (nodes[s].hotspot && length >= maxLength) {
			maxLength = length;
			far2 = s;
		}
	}

	static void dfs3 (int s, long length) {
		visited[s] = true;
		dist2[s] = length;
		for (int n : nodes[s].nbrs) {
			if (!visited[n]) {
				dfs3(n, length + 1);
			}
		}
	}
}

class Node {
	ArrayList<Integer> nbrs = new ArrayList<Integer>();
	boolean hotspot = false;
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
