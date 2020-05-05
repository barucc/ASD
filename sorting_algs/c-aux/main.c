#include "sorting.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>


int * randArray(int n) {
    srand(time(NULL));
    int *v = malloc(n*sizeof(int));
    for(int i = 0; i < n; i++) v[i] = rand()%5000;
    return v;
}


int* copy_array(int* S, int n){
	int *v = malloc(n*sizeof(int));
  for(int i = 0; i < n; i++) v[i] = S[i];
  return v;
}


int main(){
	
	srand(time(NULL));
	int n = 10000000;//rand()%100000;
	int* S = randArray(n);
	int* a = copy_array(S, n);
	int* v = copy_array(S, n);
	int* b = copy_array(S, n);
	int* o = copy_array(S, n);
	printf("min = %d\n", S[min(S,n)]);
	printf("Sequenza iniziale di %d elementi \n", n);
	//print_array(S,n);
	printf("\n");
	
	clock_t start, end;
	double cpu_time_used;

	//~ start = clock();
	//~ bubbleSort(S, n);
	//~ end = clock();
	//~ cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;
  
  //~ double t = cpu_time_used;
  //~ printf("bubbleSort fatto. Tempo: %lf sec.\n", t);
    
	//~ start = clock();
	//~ insertionSort_ip(a, n);
	//~ end = clock();
	
	//~ cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;
    
  //~ double t1 = cpu_time_used;  
  //~ printf("insertionSort fatto. Tempo: %lf sec.\n", t1);
  
  //~ start = clock();
	//~ selectionSort_ip(v, n);
	//~ end = clock();
	//~ cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;
  
  //~ double t2 = cpu_time_used;
  //~ printf("selectionSort fatto. Tempo: %lf sec.\n", t2);
  
  start = clock();
	mergeSort(b, 0, n-1);
	end = clock();
	cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;
  
  double t3 = cpu_time_used;
  printf("mergeSort fatto. Tempo: %lf sec.\n", t3);
  
  
  start = clock();
  int* array = (int*) malloc(sizeof(int)*n);
	mergeSort_op(o, 0, n-1, array);
	free(array);
	end = clock();
	cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;
  
  double t4 = cpu_time_used;
  printf("mergeSort_op fatto. Tempo: %lf sec.\n", t4);
    
	//printf("\nSequenza ordinata: ");
	//print_array(b,n);
	
	
}
