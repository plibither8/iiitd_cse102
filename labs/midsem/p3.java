import java.util.*;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n=in.nextInt();
		long t=in.nextLong();
		int[] arr=new int[n];
		for(int i=0;i<n;i++)arr[i]=in.nextInt();
		long l,h,m,d;
		l=d=0;
		h=1000000000;
		while(l<=h){
			m=(l+h)/2;
			long s=0;
			for(int i=0;i<n;i++)s+=m/arr[i];
			if(s>t) {
				h=m-1;
			} else {
				l=m+1;
				d=m;
			}
		}
		System.out.println(d);
	}
}
