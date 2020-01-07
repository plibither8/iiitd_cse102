import java.util.*;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		in.nextLine();
		for (int i = 0; i < n; i++) {
			int sum = 0;
			int m = in.nextInt();
			in.nextLine();
			String[] cs = in.nextLine().split(" ");
			for (int j = 0; j < m; j++) {
				sum += Integer.parseInt(cs[j]) / 3;
			}
			System.out.println(sum);
		}
	}
}