#include <stdlib.h>
#include <stdio.h>
#include "heap.h"
#include "_heap.h"

heap * heap_new(HEAP_TYPE is_min_heap, int capacity) {
	_heap * hh = (_heap*) malloc(sizeof(_heap));
	hh->is_min_heap = is_min_heap;
	hh->size = capacity;
	hh->used = 0;
	hh->array = (_heap_entry**) malloc(sizeof(_heap_entry*)*capacity);
	for(int i=0; i<capacity; i++)
		hh->array[i] = (_heap_entry*) malloc(sizeof(_heap_entry));
	return hh;
}

HEAP_TYPE heap_type(heap * hh) {
	_heap *h = hh;
	return h->is_min_heap;
}

int heap_peek(heap * hh) {
	_heap* h = hh;
	if(!h->array)	
		return -1;
	else
		return h->array[0]->key;
}

static void swap_entry(_heap_entry*f, _heap_entry*p){
	int k_f = f->key;
	f->key = p->key;
	p->key = k_f;
}

static heap_entry * upheap(_heap* h, _heap_entry**array, int pos){
	if(h->is_min_heap){
		if(pos == 0)
			return array[pos];
		if(array[pos]->key >= array[(pos-1)/2]->key){
			return array[pos];
		}
		else{
			swap_entry(array[pos], array[(pos-1)/2]);
			return upheap(h, array, (pos-1)/2);
		}
	}
	else{
		if(array[pos]->key <= array[(pos-1)/2]->key){
			return array[pos];
		}
		else{
			swap_entry(array[pos], array[(pos-1)/2]);
			return upheap(h, array, (pos-1)/2);
		}
	}
}

heap_entry * heap_add(heap * hh, int key) {
	_heap * h = hh;
	if(heap_peek(h) == -1){
		return NULL;
	}
	if(h->used == h->size){
		h->size *= 2;
		_heap_entry** tmp = realloc(h->array, sizeof(_heap_entry*)*h->size);
		free(h->array);
		h->array = tmp;
	}
	if(h->used < h->size){
		h->array[h->used]->key = key;
		h->array[h->used]->position = h->used;
		h->used++;
		heap_entry * ret = upheap(h, h->array, h->used-1);
		return ret;
	}
	return NULL;
}

int get_key_entry(heap_entry * ee) {
	_heap_entry * e = ee;
	return e->key;
}

int heap_size(heap * hh) {
	_heap * h = hh;
	return h->used;
}

void downheap(_heap* h, int pos){
	if(h->is_min_heap){
		
		if(2*pos+1 < h->used && 2*pos+2 < h->used){	//se esistono i figli
			if(h->array[pos]->key > h->array[2*pos + 1]->key //se è piu grande del f sinistro
				&& h->array[2*pos + 1]->key <= h->array[2*pos + 2]->key){ //se il figlio sinistro è piu piccolo del destro
					
				swap_entry(h->array[2*pos+1], h->array[pos]); //scambia col figlio sinistro
				downheap(h, 2*pos+1);
			}
			else if(h->array[pos]->key > h->array[2*pos + 2]->key //se è piu grande del figlio destro
				&& h->array[2*pos + 1]->key >= h->array[2*pos + 2]->key){ //se il figlio destro è piu piccolo del sinistro
					
				swap_entry(h->array[2*pos+2], h->array[pos]); //scambia col figlio destro
				downheap(h, 2*pos+2);
			}
		}
		else if(2*pos+1 < h->used && 2*pos+2 >= h->used){ //se esiste solo il figlio sinistro
			if(h->array[pos]->key > h->array[2*pos + 1]->key){ //se è piu grande del figlio sinistro
					
				swap_entry(h->array[2*pos+1], h->array[pos]);
				downheap(h, 2*pos+1);
			}
		}
	}
	else{
		if(2*pos+1 < h->used && 2*pos+2 < h->used){	
			if(h->array[pos]->key < h->array[2*pos + 1]->key 
				&& h->array[2*pos + 1]->key >= h->array[2*pos + 2]->key){
					
				swap_entry(h->array[2*pos+1], h->array[pos]);
				downheap(h, 2*pos+1);
			}
			else if(h->array[pos]->key < h->array[2*pos + 2]->key 
				&& h->array[2*pos + 1]->key <= h->array[2*pos + 2]->key){
					
				swap_entry(h->array[2*pos+2], h->array[pos]);
				downheap(h, 2*pos+2);
			}
		}
		else if(2*pos+1 < h->used && 2*pos+2 >= h->used){
			if(h->array[pos]->key < h->array[2*pos + 1]->key){
					
				swap_entry(h->array[2*pos+1], h->array[pos]);
				downheap(h, 2*pos+1);
			}
		}
	}
	return;
}

int heap_poll(heap * hh) {
	_heap * h = hh;
	int min = heap_peek(hh);
	swap_entry(h->array[0], h->array[h->used-1]);
	free(h->array[h->used-1]); 
	h->array[h->used-1] = malloc(sizeof(_heap_entry*));
	h->used--;
	downheap(h, 0);
	return min;
}

void heap_delete(heap * hh) {
	_heap * h = hh;
	for(int i = 0; i < h->size; i++){
		free(h->array[i]);
	}
	free(h->array);
	free(h);
	return;
}

heap * array2heap(int * array, int size, HEAP_TYPE is_min_heap) {
	_heap * h = heap_new(is_min_heap, size);
	
	for(int i = size-1; i>=0; i--){
		h->array[i]->key = array[i];
		h->array[i]->position = i;
		h->used++;
	}
	
	for(int j = size-1; j>=0; j--){
		downheap(h, (j-1)/2);
	}
	
	return h;
}

void heap_print(heap * hh) {
	_heap* h = hh;
	for(int i = 0; i<h->used; i++)
		printf("%d ",h->array[i]->key);
	printf("\n");
	return;
}

static void heap_sort_r(_heap*h, int size){
	if(size==0)	return;
	else{
		h->used--;
		swap_entry(h->array[0], h->array[size-1]);
		downheap(h,0);
		heap_sort_r(h, size-1);
	}
}

void heap_sort(int * array, int size) {
	_heap * h = array2heap(array, size, MAX_HEAP);
	
	heap_sort_r(h, size);
	h->used=size;
	
	for(int i = 0; i<size; i++)
		array[i] = h->array[i]->key;
		
	return;
}

void heap_update_key(heap * hh, heap_entry * ee, int key) {
	_heap * h = hh;
	_heap_entry * e = ee;
	e->key = key;
	printf("\n%d\n", e->position);
	downheap(h, e->position);
	return;        
}

