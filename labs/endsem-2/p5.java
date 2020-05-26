import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		Reader in = new Reader(System.in);
		int n = in.nextInt();
		int q = in.nextInt();

		UnF unf = new UnF(n);

		long[] vals = new long[n+1];
		boolean[] locked = new boolean[n+1];

		for (int i = 1; i <= n; i++) {
			vals[i] = in.nextLong();
			unf.sets[i] = new Set(i);
			unf.nodes.add(new ArrayList<Integer>());
		}

		while(q-->0){
			switch(in.nextInt()){
				case 1: {
					int i = in.nextInt();
					vals[i] = in.nextLong();
					break;
				}
				case 2: {
					int i = in.nextInt();
					int j = in.nextInt();
					unf.union(i, j, locked);
					break;
				}
				case 3: {
					int i = in.nextInt();
					int j = in.nextInt();
					long w = in.nextLong();

					int a = unf.find(i);
					int b = unf.find(j);
					if (a!=b || locked[a]) System.out.println(0);

					else {
						long numer=vals[i]*w;
						long denom=vals[j];
						long g=gcd(numer, denom);
						if (!unf.isLengthEven(i, j)) {
							numer*=-1;
						}
						numer/=g;
						denom/=g;
						System.out.println(numer+"/"+denom);
					}
					break;
				}
			}
		}
	}

	static long gcd (long a, long b){
		if (a == 0) return b;
		return gcd(b%a, a);
	}
}

class UnF {
	static int n;
	static Set[] sets;
	static ArrayList<ArrayList<Integer>> nodes;
	static long requiredLength;

	static int find(int x) {
		while(x != sets[x].link) {
			x = sets[x].link;
		}
		return x;
	}

	static void union(int a, int b, boolean[] locked) {
		nodes.get(a).add(b);
		nodes.get(b).add(a);

		int ra = find(a);
		int rb = find(b);

		if (ra == rb) {
			locked[ra] = locked[ra] || isLengthEven(a, b);
			return;
		}

		if (sets[ra].size <= sets[rb].size) {
			sets[ra].link = rb;
			sets[rb].size += sets[ra].size;
		} else {
			sets[rb].link = ra;
			sets[ra].size += sets[rb].size;
		}

		locked[ra] = locked[rb] = locked[ra] || locked[rb];
	}

	static boolean isLengthEven(int a, int b) {
		requiredLength = -1;
		dfs(a, b, new boolean[n+1], 0);
		return requiredLength % 2 == 0;
	}

	static void dfs(int s, int f, boolean[] visited, long length) {
		if (requiredLength != -1) return;
		visited[s] = true;

		if (s == f) {
			requiredLength = length;
			return;
		}

		for (int t : nodes.get(s)) {
			if (!visited[t]) {
				dfs(t, f, visited, length + 1);
			}
		}
	}

	UnF(int n) {
		this.n = n;
		this.sets = new Set[n+1];
		this.nodes = new ArrayList<ArrayList<Integer>>(n+1);
		this.nodes.add(new ArrayList<Integer>());
	}
}

class Set {
	int link;
	int size = 1;
	Set(int i){
		this.link=i;
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