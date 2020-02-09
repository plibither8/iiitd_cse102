import java.util.*;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n=in.nextInt();
		int[][] g=new int[n][2];
		for (int i=0;i<n;i++) {
			g[i][0]=in.nextInt();
			g[i][1]=in.nextInt();
		}
		double max=0;
		for (int i=0;i<n;i++) {
			for (int j=i;j<n;j++) {
				double a=(double)Math.pow(g[i][0]-g[j][0],2)+(double)Math.pow(g[i][1]-g[j][1],2);
				if(a>max) {
					max=a;
				}
			}
		}
		System.out.println(Math.round(Math.sqrt(max)*100)/100.0);
	}
}
