
// Implementazione del Bubble Sort in Java 

import java.util.Random;
import java.lang.*;


class BubbleSort { 
	
	
    void bubbleSort(int a[]) 
    { 
        int n = a.length; 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
				// Se a[j] > a[j+1] allora scambia
                if (a[j] > a[j+1]) { 
                    int temp = a[j]; 
                    a[j] = a[j+1]; 
                    a[j+1] = temp; 
                } 
    } 
  
    /* Stampa array */
    void printArray(int arr[]) { 
        int n = arr.length; 
        for (int i=0; i<n; ++i) 
            System.out.print(arr[i] + " "); 
        System.out.println(); 
    } 
  
    // Driver per provare la classe 
    public static void main(String args[]) { 
		
        BubbleSort ob = new BubbleSort(); 
        int arr[]= {64, 34, 25, 12, 22, 11, 90};
        if (args.length>0){
					if(args[0].equals("rnd") && args.length==2){
						Random r = new Random();
						int dim = Integer.parseInt(args[1]);
						int arr2[] = new int[dim];
						for(int i=0; i<dim; i++)
							arr2[i] = r.nextInt();//Random();
						ob.bubbleSort(arr2);
						System.out.println("Sorted array"); 
						ob.printArray(arr2);	
					}
					else if(args[0].equals("rnd")!=true){
						int arr2[] = new int[args.length];
						for(int i=0; i<args.length; i++)
							arr2[i] = Integer.parseInt(args[i]);
						ob.bubbleSort(arr2);
						System.out.println("Sorted array"); 
						ob.printArray(arr2); 
					}
					else{
						ob.bubbleSort(arr);
						System.out.println("Sorted array"); 
						ob.printArray(arr); 
					}
					
				}
				else{
					ob.bubbleSort(arr); 
					System.out.println("Sorted array"); 
					ob.printArray(arr); 
				}
				
				System.out.println("Current Time = "+System.currentTimeMillis()+" ms");
         
        
    } 
} 

