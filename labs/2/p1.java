import java.util.*;

public class Solution {
	public static int rec(int[][] g, int i, int j, int x, int y) {
		if (i >= g.length || j >= g[0].length) {
			return 0;
		}

		if (g[i][j] == 0) {
			return 0;
		}

		if (i > x && j < y || j > y && i < x) {
			return 0;
		}

		if (i == g.length - 1 && j == g[0].length - 1) {
			return 1;
		}

		int count = 0;
		count += rec(g, i+1, j, x, y);
		count += rec(g, i, j+1, x, y);

		return count;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int n = in.nextInt();
		int m = in.nextInt();
		int x = in.nextInt() - 1;
		int y = in.nextInt() - 1;

		in.nextLine();

		int[][] grid = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				grid[i][j] = in.nextInt();
			}
			in.nextLine();
		}

		int count = rec(grid, 0, 0, x, y);
		System.out.println(count);
	}
}
