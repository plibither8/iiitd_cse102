import java.util.*;

public class Solution {
	public static int f(int a) {
		if (a == 1) return 0;
		if (a == 2 || a == 3) return 1;
		return f(a-1) + f(a-2) + f(a-3);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		System.out.println(f(n));
	}
}
