#ifndef TYPES_H
#define	TYPES_H

#ifdef	__cplusplus
extern "C" {
#endif

typedef struct {
    int *arr;
    int size;
} array;


void swap(int *, int *);
void sortAlgCaller(void (*sort_alg)(array*), array *a);

/* sorting algs - aggiungerne nuovi se utile*/

void insertionSort_ip(int* S, int n);
void selectionSort_ip(int*S, int n);
void mergeSort_(int*S, int f, int l);
void merge(int*S, int f, int m, int l);
void mergeSort_op(int*S, int f, int l, int*a);
void merge_op(int*S, int f, int m, int l, int*a);
void heap_sort(int*S, int size);
void quickSort_ip(int*S, int f, int l);
void bucketSort_(int*S, int size, int max);


void mergeSort(array*);
void heapSort(array*);
void insertionSort(array*);
void selectionSort(array*);
void quickSort(array*);
void radixSort(array*);
void bucketSort(array*);
void bubbleSort(array*);
void purposelyWrongBubbleSort(array*);


#ifdef	__cplusplus
}
#endif

#endif	/* TYPES_H */

