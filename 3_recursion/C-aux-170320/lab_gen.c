#include <stdio.h>
#include <assert.h>
#include <time.h>
#include <stdlib.h>

int main(int argc, char** argv){
	
	int n = atoi(argv[1]), p = 0;
	
	char ** mat = (char**)malloc(sizeof(char*)*n);
	
	for(int i=0; i<n; i++)
		mat[i] = (char*)malloc(sizeof(char)*n);
	srand(time(NULL));	
	for(int i = 0; i<n; i++)
		for(int j = 0; j<n; j++){
			
			p = rand() % 2;
			mat[i][j] = (p == 0) ? '.' : '#';
		}
		
	FILE *fptr = fopen("lab4.in", "w");
	if(!fptr){printf("Could not open file\n"); return 0;}
	
	fprintf(fptr, "%d\n", n);
	
	for(int i = 0; i<n; i++){
		for(int j = 0; j<n; j++){
			fprintf(fptr, "%c", mat[i][j]);
		}
		fprintf(fptr, "\n");
	}
	
	fclose(fptr);
	
	return 0;
		
	
}
