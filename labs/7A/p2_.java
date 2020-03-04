import java.util.*;

public class Queue {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n=in.nextInt();
		long[] q=new long[n];
		long count=0;
		for (int i=0;i<n;i++) {
			long k=in.nextLong();
			q[i]=k;
			count++;
			for(int j=0;j<i;j++) {
				if(q[j]>0&&q[j]<k){
					q[j]=0;
					count--;
				}
			}
			System.out.println(count);
		}
	}
}
