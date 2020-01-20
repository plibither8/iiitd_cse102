import java.util.*;
import java.math.*;

public class Solution {
	static int min;

	public static void f(int a, int b, int c, int k) {
		if (a==b) {
			if (k<min) {
				min=k;
			}
			return;
		}
		if (k>min) return;
		if (Math.abs(b-a) > Math.abs(b-a*c)) f(a*c,b,c,k+1);
		if (a-b==1) f(a-1,b,c,k+1);
		else if (a>b) f(a-2,b,c,k+1);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[][] tests = new int[n][3];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 3; j++) {
				tests[i][j] = in.nextInt();
			}
		}
		for (int i = 0; i < n; i++) {
			min = Integer.MAX_VALUE;
			f(tests[i][0], tests[i][1], tests[i][2], 0);
			System.out.println(min);
		}
	}
}
