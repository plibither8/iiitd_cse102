import java.util.*;

public class MaxHeap {
	static long[] Heap;
	static int size;
	static int maxSize;

	MaxHeap(int maxSize) {
		this.maxSize = maxSize;
		this.size = -1;
		Heap = new long[maxSize];
	}

	static int parent(int pos) {
		return pos / 2;
	}

	static int leftChild(int pos) {
		return pos * 2;
	}

	static int rightChild(int pos) {
		return pos * 2 + 1;
	}

	static boolean isLeafNode(int pos) {
		return pos > (size - 1) / 2 && pos < size;
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

	static void insert(long val) {
		Heap[++size] = val;

		int index = size;
		while(index > 0  && Heap[parent(index)] < Heap[index]) {
			swap(parent(index), index);
			index = parent(index);
		}
	}

	static long extractMax() {
		long max = Heap[0];
		Heap[0] = Heap[size];
		size--;
		heapify(0);
		return max;
	}

	static void heapifyUpwards(int pos) {
		while (pos > 0 && isViolation(parent(pos))) {
			heapify(parent(pos));
			pos = parent(pos);
		}
	}

	static void change(int pos, long delta) {
		Heap[pos] += delta;
		if (delta > 0) {
			heapifyUpwards(pos);
		} else {
			heapify(pos);
		}
	}

	static void print() {
		int i = -1;
		while (!isLeafNode(++i)) {
			System.out.print("i="+i+"; ");
			System.out.print("P="+Heap[i]+"; ");
			System.out.print("L="+Heap[leftChild(i)]);
			if (!hasSingleChild(i)) System.out.print("; R="+Heap[rightChild(i)]+"\n");
			else System.out.println();
		}
	}

	static void printDirect() {
		for (int i = 0; i < maxSize; i++) System.out.print(Heap[i]+" ");
		System.out.println();
	}

	static void heapSort() {
		while(size >= 0) {
			swap(size--, 0);
			heapify(0);
		}
	}

	static void reheapify() {
		while(size < maxSize - 1) {
			long val = Heap[size+1];
			insert(val);
		}
	}

	static MaxHeap merge(MaxHeap A, MaxHeap B) {
		int totalSize = A.maxSize + B.maxSize;
		MaxHeap C = new MaxHeap(totalSize);
		for (int i = 1; i <= A.size; i++) C.insert(A.Heap[i]);
		for (int i = 1; i <= B.size; i++) C.insert(B.Heap[i]);
		return C;
	}

	public static void main(String args[]) {
		MaxHeap maxHeap = new MaxHeap(15);
		maxHeap.insert(5);
		maxHeap.insert(3);
		maxHeap.insert(17);
		maxHeap.insert(10);
		maxHeap.insert(84);
		maxHeap.insert(19);
		maxHeap.insert(6);
		maxHeap.insert(22);
		maxHeap.insert(9);
		maxHeap.printDirect();
		maxHeap.heapSort();
		maxHeap.printDirect();
		maxHeap.reheapify();
		maxHeap.printDirect();
	}
}
