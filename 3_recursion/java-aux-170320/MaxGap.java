public class MaxGap {

    public static int maxGap(int[] array, int start, int end) {
      	//TODO: Da Completare
      	
      if(start>=end){
				
				return array[0];
				
				
			} 
			else{
				int mid = (end+start)/2;
				int a = maxGap(array, start, mid-1);
				int next = array[mid+1]-array[mid];
				
				int b = maxGap(array, mid, end-1);
				int prev = array[mid]-array[mid-1];
				
			
				if(next < prev)
					return prev;
				else
					return next;
      }
      
      	
			
    }
}
