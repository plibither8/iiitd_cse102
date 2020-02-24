import java.util.*;

public class Solution {
	public static int part(long arr[], int l, int h) {
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
	public static void sort(long arr[], int l, int h) {
		if (l<h) {
			int piv=part(arr,l,h);
			sort(arr,l,piv-1);
			sort(arr,piv+1,h);
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int q = in.nextInt();
		long[] arr = new long[n];
		for (int i=0;i<n;i++)arr[i]=in.nextLong();
		sort(arr,0,n-1);
		for (int i =0;i<q;i++){
			int x=in.nextInt()-1;
			System.out.println(arr[n-x-1]);
		}
	}
}
