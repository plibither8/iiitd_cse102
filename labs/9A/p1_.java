import java.io.*;
import java.util.*;
public class lab9Aa{
	static void heapifymin(HuffmanNode[] arr, int n, int k){
		int max=k;
		if(2*k+1<n && arr[2*k+1].data==arr[max].data){
			if((int)arr[2*k+1].c>(int)arr[max].c)
				max=2*k+1;
		}
		else if(2*k+1<n && arr[2*k+1].data>arr[max].data){
			max=2*k+1;
		}
		if(2*k+2<n && arr[2*k+2].data==arr[max].data){
			if((int)arr[2*k+2].c>(int)arr[max].c)
				max=2*k+2;
		}
		else if(2*k+2<n && arr[2*k+2].data>arr[max].data){
			max=2*k+2;
		}
		if(max!=k){
			HuffmanNode temp=arr[k];
			arr[k]=arr[max];
			arr[max]=temp;
			heapifymin(arr,n,max);
		}
	}
	static void buildheapmin(HuffmanNode[] arr, int n){
		int high=n/2-1;
		for(int i=high;i>=0; i--){
			heapifymin(arr,n,i);
		}
	}
	static int parent(int x) 
    { 
        return (x-1) / 2; 
    }
    static void heapifyback(int pos,HuffmanNode[] arr){
		int parent1=parent(pos);
		if(pos>0 && arr[parent1].data==arr[pos].data){
			if((int)arr[parent1].c<(int)arr[pos].c){
			HuffmanNode temp=arr[pos];
			arr[pos]=arr[parent1];
			arr[parent1]=temp;
			heapifyback(parent1,arr);
		}
		}
		if(pos>0 && arr[parent1].data<arr[pos].data){
			
			HuffmanNode temp=arr[pos];
			arr[pos]=arr[parent1];
			arr[parent1]=temp;
			heapifyback(parent1,arr);
		}
		

		return;
	}
	
    public static void decode(String S,HuffmanNode root) {
    HuffmanNode cur=root;
    for(int i=0;i<S.length();i++){
    	char ss=S.charAt(i);
    	if(ss=='1')
    		root=root.left;
    	else
    		root=root.right;
    	if(root.left==null && root.right==null){
    		System.out.print(root.c);
    		root=cur;
    	}
	}
}
    
	public static void main(String[] arg)throws IOException{

		int t=in.nextInt();
		for(int j=0;j<t;j++){
			int n=in.nextInt();
			letter[] arr=new letter[n];

			for(int i=0;i<n;i++){
				String a=in.next();
				char b=a.charAt(0);
				letter l=new letter(b,in.nextInt());
				arr[i]=l;
			}
			String s=in.next();
			if(n==1){
				for(int i=0;i<arr[0].freq;i++){
					System.out.print(arr[0].a);
				}
				
			}else{
				HuffmanNode[] queue=new HuffmanNode[n];
			int size=0;
			for (int i = 0; i < n; i++) {  
	            HuffmanNode hn = new HuffmanNode(); 
	            hn.c = arr[i].a; 
	            hn.data = arr[i].freq; 
	            hn.left = null; 
	            hn.right = null; 
	            queue[size]=hn;
	            size++;
	            heapifyback(size-1,queue); 
	             
	        }
	        HuffmanNode root = null;
	        
	        while (size > 1) { 
	            HuffmanNode x = queue[0]; 
	            HuffmanNode temp=queue[0];
	            queue[0]=queue[size-1];
	            queue[size-1]=temp;
	            size--;
	            heapifymin(queue,size,0);
	            HuffmanNode y = queue[0]; 
	            temp=queue[0];
	            queue[0]=queue[size-1];
	            queue[size-1]=temp;
	            size--;
	            heapifymin(queue,size,0);
	            HuffmanNode f = new HuffmanNode(); 
	            f.data = x.data + y.data; 
	            f.c = '~'; 
	            f.left = x; 
	            f.right = y; 
	            root = f; 
	            queue[size]=f;
	            size++;

	            heapifyback(size-1,queue);
	            
	        }
	        
	        decode(s,root);
	        System.out.println();
			}
			
			

		}
		        
        

		
	}
}
class letter{
	char a;
	int freq;
	public letter(char a,int freq){
		this.a=a;
		this.freq=freq;
	}
}

class HuffmanNode { 
  
    int data; 
    char c; 
  
    HuffmanNode left; 
    HuffmanNode right; 
}
class in{
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokenizer = new StringTokenizer("");
    static String next() throws IOException{
        while (!tokenizer.hasMoreTokens())
            tokenizer = new StringTokenizer(reader.readLine());
        return tokenizer.nextToken();
    }
    static int nextInt() throws IOException{
        return Integer.parseInt(next());
    }
    static double nextDouble() throws IOException{
        return Double.parseDouble(next());
    }
    static long nextLong() throws IOException{
        return Long.parseLong(next());
    }
    static float nextFloat() throws IOException{
    	return Float.parseFloat(next());
    }
}