import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		Reader in = new Reader(System.in);
		int n=in.nextInt();
		long[] qs=new long[n];
		MinHeap min=new MinHeap(n);
		for (int i=0;i<n;i++){
			min.insert(in.nextLong());
			if(i < 2) {
				qs[i]=-1;
				continue;
			}
			int min1, min2, min3;
			min1 = 0;
			if (min.Heap[1] < min.Heap[2]) {
				min2=1;
				min3=2;
			} else {
				min2=2;
				min3=1;
			}
			if(min.leftChild(min2)<=min.size && min.Heap[min.leftChild(min2)]<min.Heap[min3]){
				min3=min.leftChild(min2);
			}
			if(min.rightChild(min2)<=min.size && min.Heap[min.rightChild(min2)]<min.Heap[min3]){
				min3=min.rightChild(min2);
			}
			qs[i]=min.Heap[min1]^min.Heap[min2]^min.Heap[min3];
		}
		int q=in.nextInt();
		for (int i=0;i<q;i++){
			System.out.println(qs[in.nextInt()-1]);
		}
	}
}

class MinHeap {
	static long[] Heap;
	static int size;
	static int maxSize;

	MinHeap(int maxSize) {
		this.maxSize = maxSize;
		this.size = -1;
		Heap = new long[maxSize];
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
		return pos > (size - 1) / 2 && pos < size;
	}

	static boolean hasSingleChild(int pos) {
		return rightChild(pos) > size;
	}

	static boolean isViolation(int pos) {
		return Heap[leftChild(pos)] < Heap[pos] ||
			Heap[rightChild(pos)] < Heap[pos];
	}

	static void swap(int a, int b) {
		long temp = Heap[b];
		Heap[b] = Heap[a];
		Heap[a] = temp;
	}

	static void heapify(int pos) {
		if (isLeafNode(pos) || hasSingleChild(pos)) return;

		int leftIndex = leftChild(pos);
		int rightIndex = rightChild(pos);

		if (isViolation(pos)) {
			if (Heap[leftIndex] < Heap[rightIndex]) {
				swap(leftIndex, pos);
				heapify(leftIndex);
			} else {
				swap(rightIndex, pos);
				heapify(rightIndex);
			}
		}
	}

	static void insert(long val) {
		Heap[++size] = val;

		int index = size;
		while(index > 0  && Heap[parent(index)] > Heap[index]) {
			swap(parent(index), index);
			index = parent(index);
		}
	}

	static void printDirect() {
		for (int i = 0; i < maxSize; i++) System.out.print(Heap[i]+" ");
		System.out.println();
	}

	static void heapSort() {
		while (size >= 0) {
			swap(size--, 0);
			heapify(0);
		}
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
