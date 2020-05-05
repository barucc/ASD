import java.util.Random;
import java.lang.*;

public class Main{
	
	
	
	public static int[] randArray(int n) {
		int arr2[] = new int[n];
    Random r = new Random();
		for(int i=0; i<n; i++)
			arr2[i] = r.nextInt()%50;//Random();
    return arr2;
	}


	public static int[] copy_array(int S[]){
		int v[] = new int[S.length];
		for(int i = 0; i < S.length; i++) v[i] = S[i];
		return v;
	}
	
	public static void print_array(int arr[]) { 
    int n = arr.length; 
    System.out.print("{ ");
    for (int i=0; i<n; ++i) 
			System.out.print(arr[i] + " "); 
		System.out.println("}"); 
   }
	
	public static void main(String[] args){
		
	int n = 100000;
	int S[] = randArray(n);
	int a[] = copy_array(S);
	int v[] = copy_array(S);
	int b[] = copy_array(S);
	//printf("min = %d\n", S[min(S,n)]);
	System.out.println("Sequenza iniziale di "+n+" elementi ");
	//print_array(S);
	
	System.out.println();
   
   
  long timestamp =  System.currentTimeMillis();
  
  BubbleSort ob = new BubbleSort();
  ob.bubbleSort(S);
  
  System.out.println("Current Time Bubble Sort = "+ (System.currentTimeMillis() - timestamp) +" msec");  
  
  System.out.println();
  
  timestamp =  System.currentTimeMillis();
  
  InsertionSort ob1 = new InsertionSort();
  ob1.insertionSort_ip(a);
  
  System.out.println("Current Time Insertion Sort = "+ (System.currentTimeMillis() - timestamp) +" msec");  
  
  System.out.println();
  
  timestamp =  System.currentTimeMillis();
  
  SelectionSort ob2 = new SelectionSort();
  ob2.selectionSort_ip(v);
  
  System.out.println("Current Time Selection Sort = "+ (System.currentTimeMillis() - timestamp) +" msec");  
	
	System.out.println();
	
	timestamp =  System.currentTimeMillis();
	
	MergeSort ob3 = new MergeSort();
  ob3.mergeSort(b, 0, b.length-1);
  
  System.out.println("Current Time Merge Sort = "+ (System.currentTimeMillis() - timestamp) +" msec");  
	
	System.out.println();
	
	System.out.println("Sequenza ordinata ");
	//print_array(b);
		
	}
	
	
	
	
	
}
