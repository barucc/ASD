#include <stdio.h>
#include <stdlib.h>
#include "types.h"
#include <time.h>

//~ void swap(int * a, int * b){
	//~ int tmp = *a;
	//~ *a = *b;
	//~ *b = tmp;
//~ }

int partition_ip(int *S, int left, int right){
	int l = left+1;
	int r = right;
	int i = rand()%(right+1-left)+left;
	int p = S[i];
	swap(&S[i], &S[left]);
	while(l<r){
		while(l<r && S[l]<=p)	l++;
		while(S[r]>p) r--;
		if(l<r)	
			swap(&S[l], &S[r]);	
	}
	if(S[left]>S[r])	swap(&S[left], &S[r]);
	return r;
}

void quickSort_ip(int * S, int l, int r){
	if(l>=r)
		return;
	int p = partition_ip(S, l, r);
	quickSort_ip(S, l, p-1);
	quickSort_ip(S, p+1, r);
}


