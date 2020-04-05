import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Huffman {
	static int freq[];
	static Heap heap;
	static String encoded[];
	static int size;

	static void encode() {
		encoded = new String[size];
		createHeap();
		createTree();
		generateCode(heap.heap[0], "");
	}

	static void createHeap() {
		heap = new Heap(size);
		for (int i = 0; i < size; i++) {
			if (freq[i] == 0) continue;
			Node node = new Node(freq[i], i);
			node.isLeaf = true;
			heap.insert(node);
		}
	}

	static void createTree() {
		int n = heap.size;
		for (int i=0; i<n; i++) {
			Node sum=new Node();
			sum.left = heap.delMin();
			sum.right = heap.delMin();
			sum.freq = sum.left.freq + sum.right.freq;
			heap.insert(sum);
		}
	}

	static void generateCode(Node node, String code) {
		if (node == null) return;

		if (node.isLeaf) {
			encoded[node.key] = code;
		}
		else {
			generateCode(node.left, code + "0");
			generateCode(node.right, code + "1");
		}
	}

	public static void main(String args[]) throws IOException {
		// size = 122 - 97 + 1; // a=97; z=122 (char values)
		// size=6;
		// freq=new int[]{5,9,12,13,16,45};

		// Reader in = new Reader(System.in);
		// String input = in.next();

		// for (int i=0;i<input.length();i++){
		// 	int ch = (int)input.charAt(i);
		// 	freq[ch-97]++;
		// }

		encode();
		for (String s:encoded) System.out.println(s);
	}
}

class Node {
	Node parent;
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

class Heap {
	static Node[] heap;
	static int size = -1;

	Heap(int i) {
		heap = new Node[i];
	}

	static int parent(int pos) {
		return (pos - 1) / 2;
	}

	static int left(int pos) {
		return pos * 2 + 1;
	}

	static int right(int pos) {
		return pos * 2 + 2;
	}

	static void swap(int a, int b) {
		Node temp = heap[b];
		heap[b] = heap[a];
		heap[a] = temp;
	}

	static void heapify(int pos) {
		int l = left(pos);
		int r = right(pos);

		int smallest;

		if (r <= size) {
			smallest = heap[l].freq < heap[r].freq ? l : r;
			if (heap[pos].freq > heap[smallest].freq) {
				swap(pos, smallest);
				heapify(smallest);
			}
		}

		else if (l == size && heap[pos].freq > heap[l].freq) {
			swap(l, pos);
		}
	}

	static void insert(Node k) {
		size++;

		int index = size;
		while(index > 0  && heap[parent(index)].freq > k.freq) {
			heap[index] = heap[parent(index)];
			index = parent(index);
		}

		heap[index] = k;
	}

	static void print() {
		for(Node k:heap) System.out.print(k.freq+" ");
		System.out.println();
	}

	static Node delMin() {
		Node min = heap[0];
		heap[0] = heap[size];
		size--;
		heapify(0);
		return min;
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
