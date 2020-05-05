#include "types.h"
#include <stdlib.h>
#include <stdio.h>

typedef struct node{
	int val;
	struct node*next;
} node;

typedef struct linked_list{
	node*head;
	node*tail;
	int size;
}	linked_list;

linked_list * linked_list_new() {
    linked_list *ptr = (linked_list *) malloc(sizeof(linked_list));
    ptr->head = NULL;
    ptr->tail = NULL;
    ptr->size = 0;
    return (linked_list *) ptr;
}

void linked_list_add(linked_list* ll, int x){
	linked_list *tmp = ll;
	node*added = (node*) malloc(sizeof(node));
	added->next = NULL;
	added->val = x;
	if(tmp->tail != NULL)
		tmp->tail->next = added;
	if(tmp->head == NULL)
		tmp->head = added;
	tmp->tail = added;
	
	
	ll->size++;
}

void linked_list_delete(linked_list *ll) {
	if (ll == NULL) {
		return ;
	}
	node *ptr = ll->head;
	int i;
  for (i = 0; i < ll->size; i++) {
		if(i==ll->size-1){
			free(ptr);
			break;
		}
    ll->head = ptr->next;
    free(ptr);
    ptr = ll->head;
  }
  free(ll);
  return ;
}


void bucketSort_(int*S, int size, int max){
	linked_list**a = (linked_list**)malloc(sizeof(linked_list*)*(max+1));
	for(int i = 0; i<size; i++){
		if(!a[S[i]])
			a[S[i]] = linked_list_new();
			
		linked_list_add(a[S[i]], S[i]);
	}
	int k = 0;
	for(int j = 0; j<max+1; j++){
		if(a[j]){
			node*ptr = a[j]->head;
			while(ptr->next){
				S[k++] = ptr->val;
				ptr = ptr->next;
			} 
			S[k++] = ptr->val;
		}
	}
	
	
	for(int l = 0; l<max+1; l++)
		linked_list_delete(a[l]);
	free(a);
	
}
