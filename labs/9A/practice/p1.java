import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		Reader in = new Reader(System.in);
		int n=in.nextInt();
		MaxHeap max=new MaxHeap(n);
		MinHeap min=new MinHeap(n);
		for (int i=0;i<n;i++){
			long x=in.nextLong();
			max.insert(x);
			min.insert(x);
		}

		int q=in.nextInt();
		while(q-->0){
			int k=in.nextInt();
			String w=in.next().trim();
			if(w.equals("S")) {
				MinHeapX pq=new MinHeapX(k);
				pq.insert(min.Heap[0],0);
				for(int i=0;i<k-1;i++){
					int j=(int)pq.Heap[0][1];
					pq.swap(0,pq.size--);
					pq.heapify(0);
					int l=min.leftChild(j);
					int r=min.rightChild(j);
					if(l<=min.size) pq.insert(min.Heap[l],l);
					if(r<=min.size) pq.insert(min.Heap[r],r);
				}
				System.out.println(pq.Heap[0][0]);
			}
			else {
				MaxHeapX pq=new MaxHeapX(k);
				pq.insert(max.Heap[0],0);
				for(int i=0;i<k-1;i++){
					int j=(int)pq.Heap[0][1];
					pq.swap(0,pq.size--);
					pq.heapify(0);
					int l=max.leftChild(j);
					int r=max.rightChild(j);
					if(l<=max.size) pq.insert(max.Heap[l],l);
					if(r<=max.size) pq.insert(max.Heap[r],r);
				}
				System.out.println(pq.Heap[0][0]);
			};
		}
	}
}

class MaxHeapX {
	static long[][] Heap;
	static int size;
	static int maxSize;

	MaxHeapX(int maxSize) {
		this.maxSize = maxSize;
		this.size = -1;
		Heap = new long[maxSize][2];
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
		return Heap[leftChild(pos)][0] > Heap[pos][0] ||
			Heap[rightChild(pos)][0] > Heap[pos][0];
	}

	static void swap(int a, int b) {
		long temp = Heap[b][0];
		Heap[b][0] = Heap[a][0];
		Heap[a][0] = temp;
	}

	static void heapify(int pos) {
		if (isLeafNode(pos) || hasSingleChild(pos)) return;

		int leftIndex = leftChild(pos);
		int rightIndex = rightChild(pos);

		if (isViolation(pos)) {
			if (Heap[leftIndex][0] > Heap[rightIndex][0]) {
				swap(leftIndex, pos);
				heapify(leftIndex);
			} else {
				swap(rightIndex, pos);
				heapify(rightIndex);
			}
		}
	}

	static void printDirect() {
		for (int i = 0; i < maxSize; i++) System.out.print(Heap[i][0]+":"+Heap[i][1]+" ");
		System.out.println();
	}

	static void insert(long val,int i) {
		Heap[++size][0] = val;
		Heap[size][1]=i;

		int index = size;
		while(index > 0  && Heap[parent(index)][0] < Heap[index][0]) {
			swap(parent(index), index);
			index = parent(index);
		}
	}
}

class MinHeapX {
	static long[][] Heap;
	static int size;
	static int maxSize;

	MinHeapX(int maxSize) {
		this.maxSize = maxSize;
		this.size = -1;
		Heap = new long[maxSize][2];
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
		return Heap[leftChild(pos)][0] > Heap[pos][0] ||
			Heap[rightChild(pos)][0] > Heap[pos][0];
	}

	static void swap(int a, int b) {
		long temp = Heap[b][0];
		Heap[b][0] = Heap[a][0];
		Heap[a][0] = temp;
	}

	static void heapify(int pos) {
		if (isLeafNode(pos) || hasSingleChild(pos)) return;

		int leftIndex = leftChild(pos);
		int rightIndex = rightChild(pos);

		if (isViolation(pos)) {
			if (Heap[leftIndex][0] < Heap[rightIndex][0]) {
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

	static void insert(long val,int i) {
		Heap[++size][0] = val;
		Heap[size][1]=i;

		int index = size;
		while(index > 0  && Heap[parent(index)][0] > Heap[index][0]) {
			swap(parent(index), index);
			index = parent(index);
		}
	}
}

class MaxHeap {
	static long[] Heap;
	static int size;
	static int maxSize;

	MaxHeap(int maxSize) {
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
		long temp = Heap[b];
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

	static void insert(long val) {
		Heap[++size] = val;

		int index = size;
		while(index > 0  && Heap[parent(index)] < Heap[index]) {
			swap(parent(index), index);
			index = parent(index);
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

	static void printDirect() {
		for (int i = 0; i < maxSize; i++) System.out.print(Heap[i]+" ");
		System.out.println();
	}

	static void insert(long val) {
		Heap[++size] = val;

		int index = size;
		while(index > 0  && Heap[parent(index)] > Heap[index]) {
			swap(parent(index), index);
			index = parent(index);
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
