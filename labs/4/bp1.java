import java.io.*;
import java.util.Scanner;
import java.lang.*;

class aps{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		for(int i=0;i<n;i++){
			arr[i] = sc.nextInt();
		}
		int q = sc.nextInt();
		for(int i=0;i<q;i++){
			int t = sc.nextInt();
			int f = binF(arr,t);
			int b = binB(arr,t);

			if(f==-1 && b==-1){
				System.out.println(-1);
			}else{
				System.out.println((n-1-f)-b +1);
			}
		}
	}
	static int binF(int[] arr,int t){
		int low = 0;
		int high = arr.length -1;
		int answer = -1;
		while(low<=high){
			int mid = (high + low )/2;
			if(arr[mid]==t){
				answer = mid;
				break;
			}if(t<arr[mid]){
				high = mid-1;
			}if(t>arr[mid]){
				low = mid +1;
			}
		}
		return answer;
	}
	static int binB(int[] arr,int t){
		int low = arr.length-1;
		int high = 0;
		int answer =-1;
		while(low>=high){
			int mid = (high + low )/2;
			if(arr[mid]==t){
				answer = mid;
				break;
			}if(t<arr[mid]){
				low =mid-1;
			}if(t>arr[mid]){
				high = mid+1;
			}
		}
		return answer;
	}
}