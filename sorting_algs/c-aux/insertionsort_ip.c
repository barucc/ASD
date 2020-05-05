#include "sorting.h"
#include <stdlib.h>
#include <stdio.h>

void print_array(int* S, int n){
	int i = 0;
	printf("{ ");
	while(i<n){
		printf("%d ", S[i]);
		i++;
	}
	printf("}\n");
}

void insertionSort_ip(int* S, int n){
	int i = 1;
	int c = 0;
	int tmp = 0;
	while(i<n){
		tmp = S[i];
		if(tmp<S[i-1]){
			c = i;
			while(c>=0){
				if(c==0) S[c] = tmp;
				else if(S[c-1]>tmp){
					S[c] = S[c-1];
				}
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
