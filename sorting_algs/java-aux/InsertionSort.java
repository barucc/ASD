import java.lang.*;

public class InsertionSort{
	
	public void insertionSort_ip(int S[]){
		int i = 1;
		int c = 0;
		int tmp = 0;
		int n = S.length;
		while(i<n){
			tmp = S[i];
			if(tmp<S[i-1]){
				c = i;
				while(c>=0){
					if(c==0) S[c] = tmp;
					else if(S[c-1]>tmp)	S[c] = S[c-1];
					else{
						S[c] = tmp;
						break;
					}
					c--;
				}
			
			}
			i++;
		}
		
	}
	
	
}

