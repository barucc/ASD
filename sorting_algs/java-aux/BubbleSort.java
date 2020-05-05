
// Implementazione del Bubble Sort in Java 

import java.lang.*;


class BubbleSort { 
	
	
    void bubbleSort(int a[]){ 
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
  
        
} 

