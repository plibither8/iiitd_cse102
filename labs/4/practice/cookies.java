import java.util.*;
import java.math.*;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();
		int[] a = new int[n];
		int[] b = new int[n];
		for (int i = 0; i < n; i++) a[i] = in.nextInt();
		for (int i = 0; i < n; i++) b[i] = in.nextInt();
		int count = 0;
		while(true) {
			for (int i=0;i<n;i++) {
				if (k<0) break;
				if(b[i] >= a[i]) {
					b[i]-=a[i];
				}
				else {
					k-=a[i]-b[i];
					b[i]=0;
				}
			}
			if (k<0) break;
			count++;
		}
		System.out.println(count);
	}
}
