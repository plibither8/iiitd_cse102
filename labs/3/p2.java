import java.util.*;

public class Solution {
	static int m, n, k, b;
	public static void f(int x, int y, int d, int e, int c) {
		if (b==1) return;
		if (c>k) return;
		if (x+d < 0 || y+e<0 || x+d>7 || y+e>7) return;
		if (x+d == m && y+e==n) {
			b=1;
			return;
		};
		if (d!=-2&&e!=-1) f(x+d,y+e,2,1,c+1);
		if (d!=-1&&e!=-2) f(x+d,y+e,1,2,c+1);
		if (d!=1&&e!=-2) f(x+d,y+e,-1,2,c+1);
		if (d!=2&&e!=-1) f(x+d,y+e,-2,1,c+1);
		if (d!=2&&e!=1) f(x+d,y+e,-2,-1,c+1);
		if (d!=1&&e!=2) f(x+d,y+e,-1,-2,c+1);
		if (d!=-2&&e!=1) f(x+d,y+e,2,-1,c+1);
		if (d!=-1&&e!=2) f(x+d,y+e,1,-2,c+1);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		m = in.nextInt() - 1;
		n = in.nextInt() - 1;
		k = in.nextInt();
		f(0, 0, 0, 0, 0);
		System.out.println(b==1?"YES":"NO");
	}
}
