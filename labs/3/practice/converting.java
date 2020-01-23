import java.util.*;
import java.math.*;

public class Solution {
	static long min;

	public static void f(long a, long b, long c, long k) {
		if (a==b) {
			if (k<min) {
				min=k;
			}
			return;
		}
		if (k>min) return;
		if (a < b) f(a*c,b,c,k+1);
		f(a-1,b,c,k+1);
		f(a-2,b,c,k+1);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long n = in.nextLong();
		long[][] tests = new long[(int)n][3];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 3; j++) {
				tests[i][j] = in.nextLong();
			}
		}
		for (int i = 0; i < n; i++) {
			min = Long.MAX_VALUE;
			f(tests[i][0], tests[i][1], tests[i][2], 0);
			System.out.println(min);
		}
	}
}
