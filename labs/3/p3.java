import java.util.*;

public class Solution {
	public static int f(long a, int b) {
		int c = (int)a % 10;
		if (a / 10 == 0) return (int)Math.pow(c,b);
		return (int)Math.pow(c,b) + f(a/10,b);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		long t[] = new long[n];
		for (int i=0;i<n;i++) t[i] = in.nextInt();
		for (int i=0;i<n;i++) {
			System.out.println((t[i]==f(t[i],String.valueOf(t[i]).length())) ? "YES":"NO");
		}
	}
}
