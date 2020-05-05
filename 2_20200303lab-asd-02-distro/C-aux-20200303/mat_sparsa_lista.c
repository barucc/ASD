#include "mat_sparsa_lista.h"
#include <stdlib.h>
#include <stdio.h>

typedef struct elem {
	int i, j, x;
	struct elem *next;
} elem;

struct matrice_sparsa {
	int m, n;
	elem* head;
};

matrice_sparsa* matrice_sparsa_new(int m, int n) {
	// TODO: Implement here
	matrice_sparsa *out = (matrice_sparsa*) malloc(sizeof(matrice_sparsa));
	out->m = m;
	out->n = n;
	return out;
}

void matrice_sparsa_delete(matrice_sparsa* mat, int i, int j) {
	// TODO: Implement here
	if(!mat->head)	return;
	elem *e = mat->head;
	elem *c = NULL;
	elem *tmp = NULL;
	if(e->i==i && e->j==j){
		mat->head = e->next;
		free(e);
		return;
	}
	while(e->next){
			
		if(e->i == i && e->j == j){
			tmp = e;
			c->next = e->next;
			//free(tmp);
			return;
		}
		c = e;
		e = e->next;
	}
	if(e->i == i && e->j == j){
		tmp = e;
		c->next = e->next;
		//free(tmp);	
		return;
	}
	
}

int get_num_row(matrice_sparsa* mat) {
	// TODO: Implement here
	return mat->m;
}

int get_num_col(matrice_sparsa* mat) {
	// TODO: Implement here
	return mat->n;
}

void mat_set(matrice_sparsa* mat, int i, int j, int x) {
	// TODO: Implement here
	if(get_num_row(mat)<=i || get_num_col(mat)<=j){
		printf("Exceeds bounds\n");
		return;
	}
	if(x==0){
		matrice_sparsa_delete(mat, i, j);
		return;
	}
	if(!mat->head){
		mat->head = (elem*) malloc(sizeof(elem));
		elem *e = mat->head;
		e->i = i; e->j = j; e-> x = x;
	}
	else if(!mat->head->next){
		elem *e = mat->head;
		if(e->i == i && e->j == j){
			e->x = x;
			return;
		}
		else{
			e->next = (elem*) malloc(sizeof(elem));
			e = e->next;
			e->i = i; e->j = j; e->x = x;
			return;
		}
	}
	else{
		elem *e = mat->head;
		while(e->next){
			if(e->i == i && e->j == j){
				e->x = x;
				return;
			}
			e = e->next;
		}
		if(e->i == i && e->j == j){
			e->x = x;	
			printf("last");
			return;
		}
		else{
			e->next = (elem*) malloc(sizeof(elem));
			e = e->next;
			e->i = i; e->j = j; e->x = x;
		}
		
		
	}
	
}

int mat_get(matrice_sparsa* mat, int i, int j) {
	// TODO: Implement here
	int x = 0;
	elem *e = mat->head;
	if(e==NULL)	return x;
	else{
		if(e->i == i && e->j == j)	return e->x;
	}
	
	while(e->next){
		if(e->i == i && e->j == j)	return e->x;
		e = e->next;
	}
	if(e->i == i && e->j == j)	x = e->x;
	return x;
	
}

void mat_print(matrice_sparsa* mat) {
	// TODO: Implement here
	if(!mat->head)	return;
	elem *e = mat->head;
	int r = get_num_row(mat); 
	int c = get_num_col(mat);
	for(int m=0; m<r; m++){
		for(int n=0; n<c; n++){
			printf("%d ", mat_get(mat, m,n)); 
		}
		printf("\n");
	}
	printf("\n");
	while(e->next){
		printf("%d ", e->x);
		e=e->next;
	}
	printf("%d \n", e->x);
	
}


matrice_sparsa* mat_add(matrice_sparsa* mat1, matrice_sparsa* mat2){
			int n = 0;int val=0;

	int r = mat1->m; int c = mat1->n; int v = 0;
	if(r!=mat2->m || c!=mat2->n) return NULL;
				
	printf("op.n ");

	matrice_sparsa * add = matrice_sparsa_new(r, c);
	elem *test = mat1->head;
							//printf("op.n %d",n);

	for (int i = 0; i < r; i++) {
		for (int j = 0; j < c; j++) {
			//printf("%d ", i);
			val = mat_get(mat1, i,j);
			mat_set(add, i, j, val);
		}
	}
	/*while(test->next){
		n++;
						//printf("op.n %d",n);

		mat_set(add, test->i, test->j, test->x);
		test=test->next;

	}
	printf("%d i %d j %d x", test->i, test->j, test->x);
	mat_set(add, test->i, test->j, test->x);*/
	
	elem *e1 = add->head; elem *e2 = mat2->head;
	n = 0;
	while(e1->next){
		e2 = mat2->head;
		n++;
		while(e2->next){
			if(e1->i==e2->i && e1->j==e2->j){
				e1->x+=e2->x;
			}
			e2 = e2->next;
		}
		if(e1->i==e2->i && e1->j==e2->j){
			e1->x+=e2->x;
		}
		else{
			mat_set(add, e2->i, e2->j, e2->x);
		}
		//printf("op.n %d",n);
		e1 = e1->next;
	}
	while(e2->next){
		if(e1->i==e2->i && e1->j==e2->j){
			e1->x+=e2->x;
		}
		e2 = e2->next;
	}
	if(e1->i==e2->i && e1->j==e2->j){
		e1->x+=e2->x;
	}
	else{
		mat_set(add, e2->i, e2->j, e2->x);
	}
	return add;
	
	
}
