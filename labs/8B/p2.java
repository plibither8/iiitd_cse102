import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MinHeap {
	static class Teacher {
		int d, l, s;
		Teacher(int d, int l, int s) {
			this.d = d;
			this.l = l;
			this.s = s;
		}
	}

	static Teacher[] Heap;
	static int size;
	static int maxSize;

	MinHeap(int maxSize) {
		this.maxSize = maxSize;
		this.size = -1;
		Heap = new Teacher[maxSize];
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
		return Heap[leftChild(pos)].s < Heap[pos].s ||
			Heap[rightChild(pos)].s < Heap[pos].s;
	}

	static void swap(int a, int b) {
		Teacher temp = Heap[b];
		Heap[b] = Heap[a];
		Heap[a] = temp;
	}

	static void heapify(int pos) {
		if (isLeafNode(pos) || hasSingleChild(pos)) return;

		int leftIndex = leftChild(pos);
		int rightIndex = rightChild(pos);

		if (isViolation(pos)) {
			if (Heap[leftIndex].s < Heap[rightIndex].s) {
				swap(leftIndex, pos);
				heapify(leftIndex);
			} else {
				swap(rightIndex, pos);
				heapify(rightIndex);
			}
		}
	}

	static void insert(int d, int l, int s) {
		Heap[++size] = new Teacher(d, l, s);

		int index = size;
		while(index > 0  && Heap[parent(index)].s > Heap[index].s) {
			swap(parent(index), index);
			index = parent(index);
		}
	}

	static void print() {
		for (Teacher t : Heap) System.out.print(t.s+" ");
		System.out.println();
	}

	public static void main(String[] args) throws IOException {
		Reader in = new Reader(System.in);
		int n = in.nextInt(), d = in.nextInt();
		MinHeap minHeap = new MinHeap(n);

		for (int i =0;i<n;i++) {
			minHeap.insert(in.nextInt(), in.nextInt(), in.nextInt());
		}

		for(int i=1;i<=d;i++) {
			while () {

			}
		}

		// for (int i=1;i<=d;i++) {
		// 	int maxS = 0;
		// 	int maxSIndex = -1;
		// 	for (int j=0; j<n;j++) {
		// 		if(ds[j]<=i && ts[j]>0) {
		// 			if (maxS < ss[j]) {
		// 				maxS = ss[j];
		// 				maxSIndex = j;
		// 			}
		// 		}
		// 	}
		// 	if (maxSIndex == -1) continue;
		// 	ts[maxSIndex]--;
		// }

		// int s=0;
		// for(int i=0;i<n;i++){
		// 	s+=ss[i]*ts[i];
		// }
		// System.out.println(s);
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
