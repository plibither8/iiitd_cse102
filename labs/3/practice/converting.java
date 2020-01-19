import java.util.*;

public class Solution {
	static int count;

	public static void f(int b, int c, int a) {
		if (a == b) {
			return;
		}

		count++;

		if (a < b) {
			f(b, c, a*c);
			return;
		}

		if (a - b == 1) {
			f(b, c, a-1);
			return;
		}

		f(b, c, a-2);
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
			count = 0;
			f(tests[i][1], tests[i][2], tests[i][0]);
			System.out.println(count);
		}
	}
}
