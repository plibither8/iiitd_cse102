import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Huffman {
	static int[] freq;
	static PriorityQueue<Node> queue;
	static String[] encoded;
	static int size;

	static void encode() {
		encoded = new String[size];
		createPQ();
		createTree();
		generateCode(queue.peek(), "");
	}

	static void createPQ() {
		queue = new PriorityQueue<Node>(size, new minHeapComparator());
		for (int i = 0; i < size; i++) {
			Node node = new Node(freq[i], i);
			node.isLeaf = true;
			queue.add(node);
		}
	}

	static void createTree() {
		while (queue.size() > 1) {
			Node sum = new Node();
			sum.left = queue.poll();
			sum.right = queue.poll();
			sum.freq = sum.left.freq + sum.right.freq;
			queue.add(sum);
		}
	}

	static void generateCode(Node node, String code) {
		if (node == null) return;

		if (node.isLeaf) {
			encoded[node.key] = code;
		} else {
			generateCode(node.left, code + "0");
			generateCode(node.right, code + "1");
		}
	}

	// public static void main(String[] args) throws IOException {
	// 	size = 122 - 97 + 1; // a=97; z=122 (char values)
	// 	freq = new int[size];

	// 	Reader in = new Reader(System.in);
	// 	String input = in.next();

	// 	for (int i=0;i<input.length();i++){
	// 		int ch = (int)input.charAt(i);
	// 		freq[ch-97]++;
	// 	}

	// 	encode();
	// 	for (String s:encoded) System.out.println(s);
	// }
}

class Node {
	Node left;
	Node right;
	int key = -1;
	int freq = -1;
	boolean isLeaf = false;

	Node(){}

	Node(int freq, int i) {
		this.key = i;
		this.freq = freq;
	}
}

class minHeapComparator implements Comparator<Node> {
	public int compare(Node x, Node y) {
		return x.freq - y.freq;
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
