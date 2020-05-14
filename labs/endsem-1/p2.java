import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Math;
import java.util.*;
import java.util.StringTokenizer;

public class Solution {
	public static class MaxHeap {
		MaxHeap(ArrayList<Det> heap) {
			for(int i=heap.size()/2-1;i>=0;i--) {
				heapify(heap, heap.size(), i);
			}
		}

		static int parent(int pos) {
			return (pos-1)/ 2;
		}

		static int leftChild(int pos) {
			return pos * 2 + 1;
		}

		static int rightChild(int pos) {
			return pos * 2 + 2;
		}

		static void heapify(ArrayList<Det> heap, int n, int pos) {
			int l = leftChild(pos);
			int r = rightChild(pos);
			int largest = pos;

			if(l < n && heap.get(l).skill > heap.get(largest).skill) {
				largest = l;
			}

			if(r < n && heap.get(r).skill > heap.get(largest).skill) {
				largest = r;
			}

			if(largest != pos) {
				Det temp = heap.get(pos);
				heap.set(pos, heap.get(largest));
				heap.set(largest, temp);
				heapify(heap, n, largest);
			}

			heap.size();
		}

		static void print(ArrayList<Det> heap) {
			for(Det d: heap)System.out.print(d.skill+" ");
			System.out.println();
		}
	}
	private static int part(Det[] arr, int l, int h) {
		double piv = arr[h].ratio;
		int i=l-1;
		Det temp;
		for(int j=l;j<h;j++){
			if(arr[j].ratio<piv){
				temp=arr[++i];
				arr[i]=arr[j];
				arr[j]=temp;
			}
		}
		temp=arr[++i];
		arr[i]=arr[h];
		arr[h]=temp;
		return i;
	}
	private static void qs(Det[] arr, int l, int h) {
		if (l<h) {
			int piv=part(arr,l,h);
			qs(arr,l,piv-1);
			qs(arr,piv+1,h);
		}
	}
	public static int sum(ArrayList<Det> heap){
		int s=0;
		for(Det d:heap)s+=d.skill;
		return s;
	}
	public static void main(String[] args) throws IOException {
		Reader in = new Reader(System.in);
		int n=in.nextInt();
		int k=in.nextInt();
		Det[] dets = new Det[n];
		for (int i=0;i<n;i++){
			Det d = new Det();
			d.skill=in.nextInt();
			dets[i]=d;
		}
		for (int i=0;i<n;i++){
			int x=in.nextInt();
			dets[i].amount=x;
			dets[i].ratio=(double)x/(double)dets[i].skill;
		}
		qs(dets,0,n-1);

		ArrayList<Det> heap = new ArrayList<Det>(k-1);
		for(int i=0;i<k-1;i++) heap.add(dets[i]);
		MaxHeap mh = new MaxHeap(heap);

		int skillsum=sum(heap);
		double ans=Integer.MAX_VALUE;
		for(int i=k-1;i<n;i++){
			double cost=skillsum*dets[i].ratio+dets[i].amount;
			if(cost<ans)ans=cost;
			if(heap.get(0).skill>dets[i].skill){
				heap.set(0,dets[i]);
				mh.heapify(heap, heap.size(), 0);
			}
			skillsum=sum(heap);
		}
		System.out.println((int)Math.ceil(ans));
	}
}

class Det {
	int amount;
	int skill;
	double ratio;
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
