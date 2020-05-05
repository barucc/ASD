#include "sorting.h"
#include <stdlib.h>
#include <stdio.h>

void swap(int *a, int i, int i1) {
    int tmp = a[i];
    a[i] = a[i1];
    a[i1] = tmp;
}

int min(int* S, int n){
	int i = 0;
	int min = i;
	while(i<n){
		if(S[i]<=S[min]) min=i;
		i++;
	}
	return min;
}

void selectionSort_ip(int* S, int n){
	int i = 0;
	int i1 = 0;
	int *tmp = NULL;
	while(i<n){
		tmp = &S[i];
		i1 = i+min(tmp, n-i);
		swap(S, i, i1);
		i++;
		
		
	}
	
}
