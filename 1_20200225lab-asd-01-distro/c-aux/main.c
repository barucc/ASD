#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <time.h>

// prototipi 
void launcher(int*, int);
int *randArray(int);
void printArray(int*, int);


//
void bubbleSort(int*, int);



int * randArray(int n) {
    
    int *v = malloc(n*sizeof(int));
    for(int i = 0; i < n; i++) v[i] = rand();
    return v;
}

void launcher(int *a, int n) {
		
    printArray(a, n);
    printf("Lancio il Bubble Sort... ");
    // prendi il tempo qui con clock
    
    clock_t start, end;
		double cpu_time_used;

		start = clock();
		bubbleSort(a, n);
		end = clock();
		cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;
    
    // prendi di nuovo il tempo qui con clock())    
    printf("fatto. Tempo: %lf msec.\n", cpu_time_used); // sostituisci a ?? %g e stampa il tempo in msec
    printArray(a, n);
}

void printArray(int *a, int n) {
    printf("L'array ha %d elementi\n", n);
    for(int i = 0; i < n; i++) printf("array[%d] = %d\n", i, a[i]);
    printf("Fine array.\n");
}


int main(int argc, char *argv[]) {
    // test
    //for (int i = 0; i < argc; i++) printf("argomento n. %d è %s\n", i, argv[i]);
    int a[] = {4, 6, 1, 9, 2, 0, 7, 5, 11, -1, 0, 13, 12, 21};
    if(argv[1]){
			if(strncmp(argv[1], "rnd", 3)==0 && argv[2]){
				int *b = randArray(atoi(argv[2]));
				launcher(b, atoi(argv[2]));
			}
			else if(strncmp(argv[1], "rnd", 3) != 0){
				int b[argc-1];
				for(int i = 1; i<=argc-1; i++)
				b[i-1] = atoi(argv[i]);
		
				launcher(b, argc-1);
			}
			else{
				launcher(a, 14);
			}
		}
		else{
			launcher(a, 14);
		}
		
		
    return 0;
}
