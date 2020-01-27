import java.util.*;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] k = new int[n];
		long[] t = new long[n];
		int i;
		for (i=0;i<n;i++) {
			k[i]=in.nextInt();
			t[i]=in.nextLong();
		}
		for(i=0;i<n;i++) {
			double y=(double)t[i]/(double)k[i];
			long start=0;
			long end=Long.MAX_VALUE;
			long mid=0;
			int q=0;
			while(true){
				if(start>end) break;
				q=0;
				mid=(start+end)/2;
				double z=mid*Math.log10(mid);
				if(z<y){
					start=mid+1;
				}else if(z>y){
					end=mid-1;
					q=1;
				}
			}
			System.out.println(mid-q);
		}
	}
}
