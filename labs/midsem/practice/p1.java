import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
    private static long[] arr2 = new long[465];
	private static int before;
	
	public static void binarySearch(long[] arr, long num) {
		int start = 0, end = 465;
		int mid;
		while(start<=end) {
			mid = (start+end)/2;
			if(arr[mid]>num) {
				end = mid - 1;
			} else if(arr[mid]<=num){
				start = mid + 1;
				before = mid;
			}
		}
		return;
	}
	
	public static void initialise(long arr[]) {
		int i,j,k=0;
		for(i = 0; i<=30; i++) {
			for(j = i+1; j<=30; j++) {
				arr[k++] = (long)Math.pow(2, i) + (long)Math.pow(2, j);
			}
		}
		Arrays.sort(arr);
	}
	

	public static void main(String[] args) {
		initialise(arr2);
		Scanner in = new Scanner(System.in);
		long t = in.nextLong();
		for(int i=0;i<t;i++) {
			long x = in.nextLong();
			binarySearch(arr2,x);
			System.out.println(Math.min(Math.abs(arr2[(before+1)]-x), Math.abs(x-arr2[before])));
		}
		in.close();
	}
}