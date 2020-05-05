import java.lang.*;
import java.util.*;

public class MergeSort{
	
	public void mergeSort(int S[], int f, int l){
		if(f==l)	return;
		int m = (f+l)/2;
		mergeSort(S, f, m);
		mergeSort(S, m+1, l);
		merge(S, f, m, l);
	}


	private void merge(int S[], int f, int m, int l){
		int tmp[] = new int[l-f+1];
		int i=f, j=m+1, k=0;
		while(i<=m && j<=l)
			tmp[k++] = (S[i]<S[j]) ? S[i++] : S[j++];
		while(i<=m) tmp[k++]=S[i++];
		while(j<=l) tmp[k++]=S[j++];
		int y = 0;
		for(int x=f; x<=l; x++)	S[x] = tmp[y++];
	}

	
	
}
