import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.io.BufferedReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Solution {
	public static void main(String[] args) throws IOException {
		Reader in = new Reader(System.in);
		int t=in.nextInt();
		while(t-->0){
			Huffman hm=new Huffman();
			int n=in.nextInt();
			hm.size=n;
			hm.freq=new int[n];
			hm.chars=new char[n];
			for(int i=0;i<n;i++){
				char key=in.next().charAt(0);
				int f=in.nextInt();
				hm.chars[i]=key;
				hm.freq[i]=f;
			}

			String code=in.next();
			if (n==1) {
				for (int i=0;i<code.length();i++) {
					System.out.print(hm.chars[0]);
				}
				System.out.println();
				continue;
			}

			hm.createPQ();
			hm.createTree();

			Node node=hm.queue.peek();
			for(int i=0;i<code.length();i++){
				if(code.charAt(i)=='0') node=node.right;
				else node=node.left;
				if (node.isLeaf()) {
					System.out.print(node.key);
					node=hm.queue.peek();
				}
			}
			System.out.println();
		}
	}
}

class Huffman {
	static int[] freq;
	static PriorityQueue<Node> queue;
	static int size;
	static char[] chars;

	static void createPQ() {
		queue = new PriorityQueue<Node>(size, new maxHeapComparator());
		for (int i = 0; i < size; i++) {
			Node node = new Node();
			node.freq=freq[i];
			node.key=chars[i];
			queue.add(node);
		}
	}

	static void createTree() {
		while (queue.size() > 1) {
			Node sum = new Node();
			sum.left = queue.poll();
			sum.right = queue.poll();
			sum.freq = sum.left.freq + sum.right.freq;
			sum.key = (char)Math.min(sum.left.key, sum.right.key);
			queue.add(sum);
		}
	}
}

class Node {
	Node left;
	Node right;
	char key;
	int freq;
	boolean isLeaf = false;

	public boolean isLeaf() {
		assert((left == null) && (right == null)) || ((left != null) && (right != null));
		return (left == null) && (right == null);
	}
}

class maxHeapComparator implements Comparator<Node> {
	public int compare(Node x, Node y) {
		if (x.freq!=y.freq) {
			return y.freq-x.freq;
		}
		return y.key-x.key;
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
