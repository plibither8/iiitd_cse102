import java.util.*;

public class Solution {
	public static int f(int a) {
		if (a == 0) return 1;
		if (a < 0) return 0;
		return f(a-1) + f(a-2) + f(a-3);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println(f(in.nextInt()));
	}
}
