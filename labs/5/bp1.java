import java.util.*;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t=in.nextInt();
		for (int i=0;i<t;i++){
			int n=in.nextInt();
			long[] a=new long[n];
			int mi=0;
			long max=0;
			for(int j=0;j<n;j++){
				long k=a[j]=in.nextLong();
				if (k<a[mi]) mi=j;
				if (k>max) max=k;
			}
			System.out.println(max/(mi+1)+1);
		}
	}
}
