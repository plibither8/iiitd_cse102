import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		Reader in = new Reader(System.in);
		int n=in.nextInt();
		int q=in.nextInt();

		long[] vals=new long[n+1];
		boolean[] bad=new boolean[n+1];
		Set[] sets=new Set[n+1];

		for(int i=1;i<=n;i++){
			vals[i]=in.nextLong();
			sets[i]=new Set(i);
		}

		UnF unf = new UnF(sets);

		while(q-->0){
			switch(in.nextInt()){
				case 1: {
					int i=in.nextInt();
					vals[i]=in.nextLong();
					break;
				}
				case 2: {
					int i=in.nextInt();
					int j=in.nextInt();
					unf.union(i,j,bad);
					break;
				}
				case 3: {
					int i=in.nextInt();
					int j=in.nextInt();
					long w=in.nextLong();
					int a=unf.find(i);
					int b=unf.find(j);
					if(a!=b || bad[a] || bad[b]) System.out.println("0");
					else {
					// for(int f=1;f<=n;f++)System.out.print((sets[f].parity?1:-1)+" ");
					// System.out.println();
						long numer=vals[i]*w;
						long denom=vals[j];
						long g=gcd(numer, denom);
						if(unf.parity(i,bad)!=unf.parity(j,bad)){
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

	static long gcd(long a, long b){
		if(a==0) return b;
		return gcd(b%a, a);
	}
}

class UnF {
	static Set[] sets;

	static int find(int x) {
		while(x != sets[x].link) {
			x = sets[x].link;
		}
		return x;
	}

	static boolean same(int a, int b){
		return find(a) == find(b);
	}

	static void swap(int a, int b){
		Set temp = sets[a];
		sets[a] = sets[b];
		sets[b]=temp;
	}

	static boolean parity(int x, boolean[] bad){
		boolean finalParity=true;
		while(x != sets[x].link) {
			finalParity = !finalParity^sets[x].parity;
			x=sets[x].link;
		}
		return finalParity;
	}

	static void union(int a, int b,boolean[] bad) {
		int ra = find(a);
		int rb = find(b);
		boolean pa=parity(a,bad);
		boolean pb=parity(b,bad);

		if(ra==rb){
			bad[ra] |= pa==pb;
			return;
		}

		if(pa==pb){
			if(sets[ra].size<sets[rb].size){
				sets[ra].parity=!sets[ra].parity;
				sets[ra].link = rb;
				sets[rb].size += sets[ra].size;
			}else{
				sets[rb].parity=!sets[rb].parity;
				sets[ra].size += sets[rb].size;
				sets[rb].link = ra;
			}
		}else{
			if(sets[ra].size<sets[rb].size){
				sets[ra].link = rb;
				sets[rb].size += sets[ra].size;
			}else{
				sets[ra].size += sets[rb].size;
				sets[rb].link = ra;
			}
		}
	}

	UnF(Set[] sets) {
		this.sets = sets;
	}
}

class Set {
	int link;
	int size=1;
	boolean parity=true;
	Set(int i){
		this.link=i;
	}
}
/**
4 10
6 8 10 13
3 1 2 2
2 1 2
3 1 2 3
2 2 3
1 1 7
3 1 3 10
2 3 1
3 1 3 2
2 1 4
3 1 4 6



*/

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