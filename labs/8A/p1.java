import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MaxHeap {
	static int[] Heap;
	static int size;
	static int maxSize;

	MaxHeap(int maxSize) {
		this.maxSize = maxSize;
		this.size = -1;
		Heap = new int[maxSize];
	}

	static int parent(int pos) {
		return (pos - 1) / 2;
	}

	static int leftChild(int pos) {
		return pos * 2 + 1;
	}

	static int rightChild(int pos) {
		return pos * 2 + 2;
	}

	static boolean isLeafNode(int pos) {
		return pos > size / 2 && pos < size;
	}

	static boolean hasSingleChild(int pos) {
		return rightChild(pos) > size;
	}

	static boolean isViolation(int pos) {
		return Heap[leftChild(pos)] > Heap[pos] ||
			Heap[rightChild(pos)] > Heap[pos];
	}

	static void swap(int a, int b) {
		int temp = Heap[b];
		Heap[b] = Heap[a];
		Heap[a] = temp;
	}

	static void heapify(int pos) {
		if (isLeafNode(pos) || hasSingleChild(pos)) return;

		int leftIndex = leftChild(pos);
		int rightIndex = rightChild(pos);

		if (isViolation(pos)) {
			if (Heap[leftIndex] > Heap[rightIndex]) {
				swap(leftIndex, pos);
				heapify(leftIndex);
			} else {
				swap(rightIndex, pos);
				heapify(rightIndex);
			}
		}
	}

	static void printDirect() {
		for (int i = 0; i < maxSize; i++) System.out.print(Heap[i]+" ");
		System.out.println();
	}


	static void insert(int val) {
		Heap[++size] = val;

		int index = size;
		while(index > 0  && Heap[parent(index)] < Heap[index]) {
			swap(parent(index), index);
			index = parent(index);
		}
	}
	public static void main(String[] args) throws IOException {
		Reader in = new Reader(System.in);
		int k = in.nextInt(), n = in.nextInt();
		MaxHeap maxHeap = new MaxHeap(n);
		for (int i=0;i<n;i++) maxHeap.insert(in.nextInt());

		int count = 0;
		while(k>0) {
			if (maxHeap.size == 1) {
				if (maxHeap.Heap[0] < maxHeap.Heap[1]) {
					maxHeap.swap(0,1);
				}
			}
			if (maxHeap.Heap[0]==0) break;
			count+=maxHeap.Heap[0];
			maxHeap.Heap[0]/=2;
			maxHeap.heapify(0);
			k--;
		}
		System.out.println(count);
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
}
