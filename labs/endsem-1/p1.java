import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		Reader in = new Reader(System.in);
		String str = in.next();
		int c=str.length();
		Stack stk=new Stack();
		int i=0;
		while(i<c){
			char k=str.charAt(i++);
			if(stk.top == null) {
				stk.push(k);
				continue;
			}
			if(stk.top.c==k) {
				stk.pop();
			} else {
				stk.push(k);
			}
		}
		if(stk.top==null) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}
}

class Node {
	Node next;
	char c;
	Node(char c){
		this.c=c;
	}
}

class Stack {
	Node top;
	void push(char c) {
		Node nn = new Node(c);
		nn.next=top;
		top=nn;
	}
	void pop() {
		top=top.next;
	}
}

class Reader {
	static BufferedReader reader;
	static StringTokenizer tokenizer;

	Reader(InputStream input) {
		reader = new BufferedReader(new InputStreamReader(input));
		tokenizer = new StringTokenizer("");
	}

	static String next() throws IOException {
		while (!tokenizer.hasMoreTokens()) {
			tokenizer = new StringTokenizer(reader.readLine());
		}
		return tokenizer.nextToken();
	}

	static int nextInt() throws IOException {
		return Integer.parseInt(next());
	}

	static double nextDouble() throws IOException {
		return Double.parseDouble(next());
	}

	static long nextLong() throws IOException {
		return Long.parseLong(next());
	}
}
