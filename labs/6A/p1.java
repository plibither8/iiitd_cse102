import java.util.*;

public class Solution {
	private static int part(long[] arr, int l, int h) {
		long piv = arr[h];
		int i=l-1;
		long temp;
		for(int j=l;j<h;j++){
			if(arr[j]<piv){
				temp=arr[++i];
				arr[i]=arr[j];
				arr[j]=temp;
			}
		}
		temp=arr[++i];
		arr[i]=arr[h];
		arr[h]=temp;
		return i;
	}
	private static void qs(long[] arr, int l, int h) {
		if (l<h) {
			int piv=part(arr,l,h);
			qs(arr,l,piv-1);
			qs(arr,piv+1,h);
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n=in.nextInt();
		long a[]=new long[n];
		for (int i=0;i<n;i++) a[i]=in.nextLong();
		qs(a,0,n-1);
		long count=0;
		for (int i=0;i<n;i+=2) {
			count+=a[i+1]-a[i];
		}
		System.out.println(count);
	}
}
