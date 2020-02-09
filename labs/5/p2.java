import java.util.*;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		int n=in.nextInt();
		for(int i=0; i<t;i++) {
			int e=in.nextInt();
			int b=0;
			for (int j=0;j<e;j++){
				if(in.nextInt()%n==0&&b==0) b=1;
			}
			System.out.println(b==0?"False":"True");
		}
	}
}
