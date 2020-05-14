import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class Solution {
	static Node[] nodes;
	static boolean escaped=false;
	static int e;

	public static void main(String[] args) throws IOException {
		Reader in = new Reader(System.in);
		int n = in.nextInt();
		int s = in.nextInt();
		int q = in.nextInt();
		e = in.nextInt();

		nodes = new Node[n+1];
		DLL pending=new DLL();

		Node root=nodes[e]=new Node(e,null);
		boolean[] inMainTree=new boolean[n+1];
		inMainTree[e]=true;

		int[][] tuns=new int[n][2];

		for(int i=1;i<n;i++){
			int u = in.nextInt();
			int v = in.nextInt();in.next();

			if(!inMainTree[u]&&!inMainTree[v]){
				pending.add(u,v,i);
				continue;
			}

			if(inMainTree[v]){
				int t=u;
				u=v;
				v=t;
			}

			tuns[i]=new int[]{u,nodes[u].children.size()};
			nodes[v]=new Node(v,nodes[u]);
			nodes[u].children.add(nodes[v]);
			inMainTree[u]=true;
			inMainTree[v]=true;
		}

		Item pn=pending.top;
		while(pn!=null){
			if(!inMainTree[pn.edge[0]]&&!inMainTree[pn.edge[1]]) {
				pn=pn.next;
				if(pn==null){
					pn=pending.top;
				}
				continue;
			}

			int u,v;
			if(inMainTree[pn.edge[0]]){
				u=pn.edge[0];
				v=pn.edge[1];
			} else {
				v=pn.edge[0];
				u=pn.edge[1];
			}

			tuns[pn.edge[2]]=new int[]{u,nodes[u].children.size()};
			nodes[v]=new Node(v,nodes[u]);
			nodes[u].children.add(nodes[v]);
			inMainTree[u]=true;
			inMainTree[v]=true;

			pending.delete(pn);
			pn=pn.next;

			if(pn==null){
				pn=pending.top;
			}
		}

		for (int i=0;i<s;i++) {
			in.next();
		}

		// for(int[] k:tuns) {
		// 	System.out.println(k[0]+" "+k[1]);
		// }

		for(int i=0;i<q;i++){
			boolean[] visited = new boolean[n+1];
			escaped=false;

			int t=in.nextInt();
			int r=in.nextInt();

			nodes[tuns[t][0]].children.get(tuns[t][1]).blocked=true;

			Node parent=nodes[r];
			while(parent!=null&&!parent.blocked){
				if(parent.b==e){
					escaped=true;
					break;
				}
				parent=parent.p;
			}

			if (escaped) {
				System.out.println("escaped");
				continue;
			}

			System.out.println(0);
			nodes[tuns[t][0]].children.get(tuns[t][1]).blocked=false;
		}
	}
}

class Node {
	int b;
	boolean blocked=false;
	Node p;
	ArrayList<Node> children=new ArrayList<Node>();
	Node(int b, Node p){
		this.b=b;
		this.p=p;
	}
}

class Item {
	Item next=null;
	Item prev=null;
	int[] edge;
	Item(int u, int v, int i){
		this.edge=new int[]{u,v,i};
	}
}

class DLL {
	Item top=null;
	void add(int u, int v, int i){
		Item new_Item = new Item(u,v,i);
		new_Item.next = top;
		new_Item.prev = null;
		if (top != null) top.prev = new_Item;
		top = new_Item;
	}
	void delete(Item del){
		if (top == null || del == null) { 
			return; 
		} 
		if (top == del) { 
			top = del.next; 
		} 
		if (del.next != null) { 
			del.next.prev = del.prev; 
		} 
		if (del.prev != null) { 
			del.prev.next = del.next; 
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

