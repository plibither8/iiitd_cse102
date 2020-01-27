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
			long x=1;
			while(k[i]*x*Math.log10(x)<=t[i]) {
				++x;
			}
			System.out.println(--x);
		}
	}
}
