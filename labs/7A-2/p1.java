import java.util.*;
import java.util.Arrays;

public class Solution {
	public static int indexOf(long[] ino, long val) {
		for (int i=0; i<ino.length;i++) {
			if(val==ino[i]) return i;
		}
		return -1;
	}
	public static void print(long[] ino, long[] pre, int n) {
		long root=pre[0];
		int rootIndexIno = indexOf(ino,root);
		if(rootIndexIno!=0) {
			long temp1[]=Arrays.copyOfRange(pre,1,n);
			print(ino, temp1,rootIndexIno);
		}
		if(rootIndexIno!=n-1) {
			long temp1[]=Arrays.copyOfRange(ino,rootIndexIno+1,n);
			long temp2[]=Arrays.copyOfRange(pre,rootIndexIno+1,n);
			print(temp2, temp1,n-rootIndexIno-1);
		}
		System.out.print(root+" ");
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		long pre[] = new long[n];
		long ino[] = new long[n];
		for(int i=0;i<n;i++) pre[i]=in.nextLong();
		for(int i=0;i<n;i++) ino[i]=in.nextLong();
		print(ino, pre,n);
		for(int i=0;i<n-1;i++) {
			if(ino[i]>ino[i+1]) {
				System.out.println("\nNO");
				return;
			}
		}
		System.out.println("\nYES");
	}
}
