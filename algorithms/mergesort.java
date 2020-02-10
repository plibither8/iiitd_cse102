import java.util.*;

public class Solution {
	private static void merge(long[] arr, int l, int m, int r) {
		int n1=m-l+1,
			n2=r-m,
			i=0,j=0,k=l;
		long[] L = Arrays.copyOfRange(arr,l,m+1);
		long[] R = Arrays.copyOfRange(arr,m+1,r+1);
		while(i<n1&&j<n2){
			if(L[i]<=R[j]) arr[k]=L[i++];
			else arr[k++]=R[j++];
		}
		while(i<n1) arr[k++] = L[i++];
		while(j<n2) arr[k++] = R[j++];
	}
	private static void sort(long[] arr, int l, int r) {
		if (l>=r) return;
		int m = (l+r)/2;
		sort(arr,l,m);
		sort(arr,m+1,r);
		merge(arr,l,m,r);
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n=in.nextInt();
		long[] arr=new long[n];
		for (int i=0;i<n;i++) arr[i]=in.nextLong();
		sort(arr,0,n-1);
		for (int i=0;i<n;i++) System.out.println(arr[i]);
	}
}
