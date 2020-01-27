import java.util.*;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n=in.nextInt();
		int m=in.nextInt();
		int i;
		int[] gn=new int[n];
		int[] gm=new int[n];
		for (i=0;i<n;i++) {
			gn[i]=in.nextInt();
			gm[i]=1;
			m--;
		};
		Arrays.sort(gn);
		double s=Arrays.stream(gn).sum();
		for (i=n-1;i>=0;i--){
			if (m==0) break;
			if (i==n-1) {
				m--;
				gm[i]++;
			} else {
				
			}
			System.out.println((double)gn[i]/s);
		}
	}
}
