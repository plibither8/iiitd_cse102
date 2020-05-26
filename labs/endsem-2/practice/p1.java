import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
	static void pt(Object l){ System.out.println(l); };
	static boolean possible=false;
	static Node[] nodes;
	static ArrayList<Integer> list=new ArrayList<Integer>();
	public static void main(String[] args) throws IOException {
		Reader in = new Reader(System.in);
		int v=in.nextInt();
		int e=in.nextInt();
		nodes = new Node[v+1];
		int[] inDegree = new int[v+1];
		for(int i=1;i<=v;i++)nodes[i]=new Node();
		for(int i=0;i<e;i++){
			int a=in.nextInt(),b=in.nextInt();
			inDegree[b]++;
			nodes[a].nbrs.add(b);
		}
		for(int i=1;i<=v;i++){
			if(inDegree[i]==0){
				possible=true;
				inDegree[i]++;
				dfs(i);
				if(!possible)break;
			}
		}
		pt(possible?1:0);
	}
	static void dfs(int i){
		if(possible||nodes[i].state==2) return;
		if(nodes[i].state==1){
			possible=false;
			return;
		}
		nodes[i].state=1;
		for(int j : nodes[i].nbrs){
			dfs(j);
		}
		nodes[i].state=2;
		list.add(i);
	}
}

class Node {
	ArrayList<Integer> nbrs=new ArrayList<Integer>();
	int state=0;
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
