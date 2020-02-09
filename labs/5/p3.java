import java.util.*;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long n=in.nextLong();
		long q=in.nextLong();
		long[] a=new long[(int)n];
		for (int i=0;i<n;i++) a[i]=in.nextLong();
		for (int i=0;i<q;i++) {
			long k=in.nextLong();
			int m=0;
			for(int j=0;j<n;j++){
				if(a[j]>k){
					System.out.println(j);
					m=1;
					break;
				}
			}
			if (m==0) System.out.println(-1);
		}
	}
}
