//import java.util.*;

public class MaxHeap2 {

	static int parent(int pos) {
		return (pos-1)/2;
	}

	static int left(int pos) {
		return pos*2 + 1;
	}

	static int right(int pos) {
		return pos*2 + 2;
	}

	void maxHeapify(long[] A, int n, int pos) {
		int l = left(pos);
		int r = right(pos);
		int largest = pos;
		
		if(l < n && A[l] > A[pos]) {
			largest = l;
		} 
		if(r < n && A[r] > A[largest]) {
			largest = r;
		}
		if(largest != pos) {
			
			long temp = A[pos];
			A[pos] = A[largest];
			A[largest] = temp;
			
			maxHeapify(A, n, largest);
		}
	}

	public void buildMaxHeap(long[] A) {
		for(int i = A.length/2 - 1; i >= 0; i--) {
			maxHeapify(A, A.length, i);
		}
	}

	public void heapSort(long[] A) {
		buildMaxHeap(A);

		for(int i = A.length - 1; i >= 0; i--) {
			long temp = A[0];
			A[0] = A[i];
			A[i] = temp;
			
			maxHeapify(A,i,0);
		}
	}

	public void printArray(long[] A) {
		for(long a: A) {
			System.out.print(a + " ");
		}
		System.out.println();
	}
	
	public void insert (long[] A, int pos, long val) {
		A[pos] = val;
		int current = ++pos;
		while(A[current] > A[parent(current)]) {
			long temp = A[current];
			A[current] = A[parent(current)];
			A[parent(current)] = temp;
			
		}
	}
	
	public long[] merge(long[] A, long[] B) {
		int n = A.length + B.length;
		long[] C = new long[n];
		int k = 0;
		for(int i = 0; i < A.length; i++) {
			C[k] = A[i];
			k++;
		}
		for(int i = 0; i < B.length; i++) {
			C[k] = B[i];
			k++;
		}
		buildMaxHeap(C);
		return C;
	}

	public static void main(String args[]) {
		long[] A = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
		MaxHeap2 heap = new MaxHeap2();
		heap.heapSort(A);
		heap.printArray(A);

	}

}