import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MaxHeap {
	static class Node {
		int val;
		int i;
		Node(int v, int i) {
			this.val=v;
			this.i=i;
		}
	}

	static Node[] Heap;
	static int size;
	static int maxSize;

	MaxHeap(int maxSize) {
		this.maxSize = maxSize;
		this.size = -1;
		Heap = new Node[maxSize];
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
		return Heap[leftChild(pos)].val > Heap[pos].val ||
			Heap[rightChild(pos)].val > Heap[pos].val;
	}

	static void swap(int a, int b) {
		Node temp = Heap[b];
		Heap[b] = Heap[a];
		Heap[a] = temp;
	}

	static void heapify(int pos) {
		if (isLeafNode(pos) || hasSingleChild(pos)) return;

		int leftIndex = leftChild(pos);
		int rightIndex = rightChild(pos);

		if (isViolation(pos)) {
			if (Heap[leftIndex].val > Heap[rightIndex].val) {
				swap(leftIndex, pos);
				heapify(leftIndex);
			} else {
				swap(rightIndex, pos);
				heapify(rightIndex);
			}
		}
	}

	// static void printDirect() {
	// 	for (int i = 0; i < maxSize; i++) System.out.print(Heap[i]+" ");
	// 	System.out.println();
	// }


	static void insert(Node node) {
		Heap[++size] = node;

		int index = size;
		while(index > 0  && Heap[parent(index)].val < Heap[index].val) {
			swap(parent(index), index);
			index = parent(index);
		}
	}

	public static void main(String[] args) throws IOException {
		Reader in = new Reader(System.in);
		int t=in.nextInt();
		while(t-->0){
			int n = in.nextInt(), k = in.nextInt();
			MaxHeap maxHeap = new MaxHeap(n);
			int sum=0;
			int bs=0;
			for (int i=0;i<n;i++) {
				int x=in.nextInt();
				sum+=x;
				maxHeap.insert(new Node(x,i));
			};
			int[] b=new int[n];
			for (int i=0;i<n;i++) b[i]=in.nextInt();

			while(k-->0&&maxHeap.Heap[0].val>0) {
				if (maxHeap.size == 1) {
					if (maxHeap.Heap[0].val < maxHeap.Heap[1].val) {
						maxHeap.swap(0,1);
					}
				}
				if (maxHeap.Heap[0].val==0) break;
				int a = maxHeap.Heap[0].val/2;
				sum-=a;
				bs+=a*b[maxHeap.Heap[0].i];
				maxHeap.Heap[0].val-=a;
				maxHeap.heapify(0);
			}
			System.out.println(sum+" "+bs);
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
