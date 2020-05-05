#include "types.h"
#include <stdlib.h>
#include <stdio.h>

void mergeSort_(int*S, int f, int l){
	if(f==l)	return;
	int m = (f+l)/2;
	mergeSort_(S, f, m);
	mergeSort_(S, m+1, l);
	merge(S, f, m, l);
}

void merge(int*S, int f, int m, int l){
	int* tmp = malloc(sizeof(int)*(l-f+1));
	int i=f, j=m+1, k=0;
	while(i<=m && j<=l)
		tmp[k++] = (S[i]<S[j]) ? S[i++] : S[j++];
	while(i<=m) tmp[k++]=S[i++];
	while(j<=l) tmp[k++]=S[j++];
	int y = 0;
	for(int x=f; x<=l; x++)	S[x] = tmp[y++];
	free(tmp);
}



void mergeSort_op(int*S, int f, int l, int*array){
	if(f==l)	return;
	int m = (f+l)/2;
	mergeSort_op(S, f, m, array);
	mergeSort_op(S, m+1, l, array);
	merge_op(S, f, m, l, array);
	//~ int curr_size;  // For current size of subarrays to be merged 
                   //~ // curr_size varies from 1 to n/2 
  //~ int left_start; // For picking starting index of left subarray 
                   //~ // to be merged 
  
   //~ // Merge subarrays in bottom up manner.  First merge subarrays of 
   //~ // size 1 to create sorted subarrays of size 2, then merge subarrays 
   //~ // of size 2 to create sorted subarrays of size 4, and so on. 
   //~ for (curr_size=1; curr_size<=n-1; curr_size = 2*curr_size) 
   //~ { 
       //~ // Pick starting point of different subarrays of current size 
       //~ for (left_start=0; left_start<n-1; left_start += 2*curr_size) 
       //~ { 
           //~ // Find ending point of left subarray. mid+1 is starting  
           //~ // point of right 
           //~ int mid = _min_(left_start + curr_size - 1, n-1); 
  
           //~ int right_end = _min_(left_start + 2*curr_size - 1, n-1); 
  
           //~ // Merge Subarrays arr[left_start...mid] & arr[mid+1...right_end] 
           //~ merge_op(S, left_start, mid, right_end, array); 
       //~ } 
   //~ } 
	
}

void merge_op(int*S, int f, int m, int l, int * array){
	int i=f, j=m+1, k=0;
	while(i<=m && j<=l)
		array[k++] = (S[i]<S[j]) ? S[i++] : S[j++];
	while(i<=m) array[k++]=S[i++];
	while(j<=l) array[k++]=S[j++];
	int y = 0;
	for(int x=f; x<=l; x++)	S[x] = array[y++];
	
}

int _min_(int x, int y)
{
	return (x < y) ? x : y;
}
