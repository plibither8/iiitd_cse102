import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
	static int part(Edge[] arr, int l, int h) {
		double piv = arr[h].w;
		int i = l - 1;
		Edge temp;
		for (int j = l; j < h; j++) {
			if (arr[j].w < piv) {
				temp = arr[++i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		temp = arr[++i];
		arr[i] = arr[h];
		arr[h] = temp;
		return i;
	}
	static void qs(Edge[] arr, int l, int h) {
		if (l < h) {
			int piv = part(arr, l, h);
			qs(arr, l, piv - 1);
			qs(arr, piv + 1, h);
		}
	}
	public static void main(String[] args) throws IOException {
		Reader in = new Reader(System.in);
		int t=in.nextInt();
		for(int i=0;i<t;i++){
			int n=in.nextInt(),l=in.nextInt(),k=in.nextInt();
			Loc[] locs=new Loc[n+1];
			int l_count=0;

			for(int j=0;j<l;j++){
				int u=in.nextInt();
				int v=in.nextInt();
				if(locs[u]==null&&locs[v]==null){
					locs[u]=locs[v]=new Loc();
					l_count++;
				}else if(locs[u]==null){
					locs[u]=locs[v];
				}else{
					locs[v]=locs[u];
				}
			}

			for(int j=1;j<=n;j++){
				if(locs[j]==null){
					locs[j]=new Loc();
					l_count++;
				}
			}

			Edge[] edges = new Edge[k];
			for(int j=0;j<k;j++){
				edges[j]=new Edge(in.nextInt(),in.nextInt(),in.nextInt());
			}
			qs(edges,0,k-1);

			Set[] sets=new Set[n+1];
			for(int j=1;j<=n;j++)sets[j]=new Set(j);

			Edge[] mst=new Edge[n-1];

			int j=0,e=0;
			while(e<n-1){
				Edge next=edges[j++];
				int a=UnF.find(sets,next.a);
				int b=UnF.find(sets,next.b);
				if(a!=b){
					mst[e++]=next;
					UnF.union(sets,a,b);
				}
			}

			for(Edge c:mst)System.out.println(c.a+"-"+c.b+"="+c.w);

			long days=0;
			for(j=0;j<n-1;j++){
				Edge c=mst[j];
				if(locs[c.a]!=locs[c.b]&&locs[c.a].open+locs[c.b].open>0) {
					days+=c.w;
					l_count-=locs[c.a].open+locs[c.b].open;
					locs[c.a].open=locs[c.b].open=0;
					if(l_count<=0)break;
				}
			}
			System.out.println(days);
		}
	}
}

class Loc{
	int open=1;
}

class UnF{
	static int find(Set[] sets, int a) {
		if(sets[a].parent==a)return a;
		int p=find(sets,sets[a].parent);
		sets[a].parent=p;
		return p;
	}
	static void union(Set[] sets, int a, int b){
		Set A = sets[a];
		Set B = sets[b];
		int rankA = A.rank;
		int rankB = B.rank;
		if (rankA < rankB) {
			A.parent=b;
		} else {
			B.parent=a;
			if (rankA == rankB) {
				A.rank=rankA+1;
			}
		}
	}
}

class Edge {
	int a;
	int b;
	int w;
	Edge(int a,int b, int w){
		this.a=a;
		this.b=b;
		this.w=w;
	}
}

class Set {
	int parent;
	int rank=0;
	Set(int p){
		this.parent=p;
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
