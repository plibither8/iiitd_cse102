import java.util.*;

public class Solution {
	static int max = 0;
	static void f(int[][] g, int d, int s, int c) {
		for (int i = 0; i < 3; i++) {
			if (d==g.length) {
				if (c > max) {
					max = c;
				}
				return;
			};
			if (i==s) continue;
			int k =c+g[d][i];
			f(g, d+1, i, k);
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[][] p = new int[n][3];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 3; j++) {
				p[i][j] = in.nextInt();
			}
		}
		f(p,0,4,0);
		System.out.println(max);
	}
}
