import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MinHeap {
	static int[] Heap;
	static int size;
	static int maxSize;

	MinHeap(int maxSize) {
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
		int temp = Heap[b];
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

	static void insert(int val) {
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

	public static void main(String[] args) throws IOException {
		Reader in = new Reader(System.in);
		int t=in.nextInt();
		for (int i=0;i<t;i++){
			int n=in.nextInt(),k=in.nextInt();
			int[] a = new int[n];
			MinHeap sumHeap = new MinHeap(k);
			for (int j=0;j<n;j++) a[j]=in.nextInt();
			int[] firstSums=new int[n];
			firstSums[0]=a[0];
			for(int j=1;j<n;j++) firstSums[j]=firstSums[j-1]+a[j];
			// for(int p:firstSums)System.out.print(p+" ");System.out.println();
			int count = 0;
			int j;
			int m;
			boolean flag=false;
			for (j=n-1;j>=0;j--) {
				for (m=-1;m<j;m++) {
					int sum=firstSums[j]-(m==-1?0:firstSums[m]);
					if (count < k) {
						sumHeap.insert(sum);
						count++;
					} else {
						if(sum>sumHeap.Heap[0]){
							sumHeap.Heap[0]=sum;
							sumHeap.heapify(0);
						}else{
							flag=(m==-1);
							break;
						}
					}
				}
				if (flag) break;
			}
			sumHeap.heapSort();
			sumHeap.printDirect();
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
}
