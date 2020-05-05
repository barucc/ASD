#include <stdio.h>
#include <stdlib.h>
#include "types.h"

void mergeSort(array *a) {
    //~ fprintf(stdout, "mergeSort currently not implemented.\n");
    mergeSort_(a->arr, 0, a->size-1);
    return;
}

void heapSort(array *a) {
    //~ fprintf(stdout, "heapSort currently not implemented.\n");
    heap_sort(a->arr, a->size);
    return;
}

void insertionSort(array *a) {
    //~ fprintf(stdout, "insertionSort currently not implemented.\n");
    insertionSort_ip(a->arr, a->size);
    return;
}


void selectionSort(array *a) {
    //~ fprintf(stdout, "selectionSort currently not implemented.\n");
    selectionSort_ip(a->arr, a->size);
    return;
}

void quickSort(array *a) {
    //~ fprintf(stdout, "quickSort currently not implemented.\n");
    quickSort_ip(a->arr, 0, a->size-1);
    return;
}

void radixSort(array *a) {
    //~ fprintf(stdout, "radixSort currently not implemented.\n");
    
    return;
}

void bucketSort(array *a) {
    //~ fprintf(stdout, "bucketSort currently not implemented.\n");
    int max = 0;
    
    for(int i = 0; i<a->size; i++){
			if(a->arr[i]>max){
				max = a->arr[i];
			}
		}
    bucketSort_(a->arr, a->size, max);
    return;
}

