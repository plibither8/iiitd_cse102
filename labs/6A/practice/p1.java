import java.util.*;

public class Solution {
	private static int part(long[] arr, int l, int h,int[] ia) {
		long piv = arr[h];
		int i=l-1;
		long temp;
		for(int j=l;j<h;j++){
			if(arr[j]<piv){
				i++;
				if(arr[i]==arr[j]) continue;
				temp=arr[i];
				arr[i]=arr[j];
				arr[j]=temp;
				temp=ia[i];
				ia[i]=ia[j];
				ia[j]=(int)temp;
			}
		}
		i++;
		if(arr[i]==arr[h]) return i;
		temp=arr[i];
		arr[i]=arr[h];
		arr[h]=temp;w
		temp=ia[i];
		ia[i]=ia[h];
		ia[h]=(int)temp;
		return i;
	}
	private static void qs(long[] arr, int l, int h, int[] ia) {
		if (l<h) {
			int piv=part(arr,l,h,ia);
			qs(arr,l,piv-1,ia);
			qs(arr,piv+1,h,ia);
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t=in.nextInt();
		for (int i=0;i<t;i++){
			int n=in.nextInt();
			long[] a=new long[n];
			int[] ai=new int[n];
			for(int j=0;j<n;j++) {
				a[j]=in.nextLong();
				ai[j]=j+1;
			}
			qs(a,0,n-1,ai);
			long ks=0;
			long ka=0;
			for(int j=0;j<n;j++){
				if (a[j]-ka<0) continue;
				long kt=(a[j]-ka)/ai[j]+1;
				ks+=kt;
				ka+=kt*ai[j];
			}
			System.out.println(ks);
		}
	}
}
