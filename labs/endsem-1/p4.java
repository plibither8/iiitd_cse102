import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		Reader in = new Reader(System.in);
		int n = in.nextInt();
		int s = in.nextInt();
		int q = in.nextInt();
		int e = in.nextInt();
		int[] tun_index = new int[n-1];
		long[] tun_dist=new long[n-1];
		for(int i=0;i<n-1;i++){
			int u = in.nextInt();
			int v = in.nextInt();
			long w = in.nextInt();
			if(v<u)u=v;
			tun_index[i]=u;
			tun_dist[u-1]=w;
		}
		int[] extra = new int[s];
		for (int i=0;i<s;i++)extra[i]=in.nextInt();
		for(int i=0;i<q;i++){
			int t_i=in.nextInt()-1;
			int t=tun_index[t_i];
			int r=in.nextInt();

			if(r==e){
				System.out.println("escaped");
				continue;
			}

			if(r < e && (t >= e || t < r)) {
				System.out.println("escaped");
				continue;
			}

			if(r > e && (t < e || t >= r)) {
				System.out.println("escaped");
				continue;
			}

			int left=-1;
			int right=n+1;

			boolean found=false;
			for(int j=0;j<s;j++){
				int x=extra[j];
				if(x==r){
					found=true;
					break;
				}
				if(x<r) {
					if (x>left) left=x;
					continue;
				}
				if(x<right)right=x;
			}

			if (found) {
				System.out.println(0);
				continue;
			};

			if(left==-1&&right==n+1) {
				System.out.println("oo");
				continue;
			}

			long dist_l=-1;
			long dist_r=-1;

			if(right!=n+1 && (t >= right || t < r)) {
				dist_r=0;
				for(int j=r;j<right;j++){
					dist_r+=tun_dist[j-1];
				}
			}

			if(left!=-1 && (t < left || t >= r)) {
				dist_l=0;
				for(int j=left;j<r;j++){
					dist_l+=tun_dist[j-1];
				}
			}

			if (dist_l==-1&&dist_r==-1) {
				System.out.println("oo");
				continue;
			}

			if (dist_l==-1) {
				System.out.println(dist_r);
				continue;
			}

			if (dist_r==-1) {
				System.out.println(dist_l);
				continue;
			}

			System.out.println(dist_l>dist_r?dist_r:dist_l);
		}
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

