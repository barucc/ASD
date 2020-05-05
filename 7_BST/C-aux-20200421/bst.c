#include <stdlib.h>
#include <stdio.h>
#include "bst.h"
#include <string.h>

struct bst{
	int key;
	void * value;
	bst * right;
	bst * left;
};

bst * bst_new(int k, void * v) {
	bst *b = (bst*) malloc(sizeof(bst));
	b->value = (void*) malloc(sizeof(v));
	memcpy(&b->value, &v, sizeof(v));
	//b->value = v;
	b->key = k;
	b->right = NULL;
	b->left = NULL;
	return b;
}

void bst_insert(bst * b, int k, void * v) {
	bst*p = b;
	while(b){
		if(k == b->key)	b->value = v;
		else if(k > b->key){
			p = b;
			b = b->right;
		}
		else{
			p = b;
			b = b->left;
		}
	}	
	if(k > p->key)	p->right = bst_new(k,v);
	else 	p->left = bst_new(k,v);
	return;
}

void * bst_find(bst * b, int k) {
	while(b){
		if(b->key == k)	return b->value;
		else if(k > b->key)	b = b->right;
		else 	b = b->left;
	}
	return NULL;
}

int bst_find_min(bst * b) {
	while(b->left)
		b = b->left;
	return b->key;
}

void bst_remove_min(bst * b) {
	bst_remove(b, bst_find_min(b));
	return;
}

void remove_node1(bst * p, bst * b, int lr){
	if(lr == 0){
		if(b->left){
			p->left = b->left;
		}
		else{
			p->left = b->right;
		}
	}
	else{
		if(b->left){
			p->right = b->left;
		}
		else{
			p->right = b->right;
		}
	}
	free(b);
}

void remove_node2(bst* b){
	bst*p = b;
	bst*ptr = b->left;
	while(ptr->right){
		p = ptr;
		ptr = ptr->right;
	}
	b->key = ptr->key;
	b->value = ptr->value;
	if(ptr->left){
		remove_node1(p, ptr, 0);
		return;
	}
	if(ptr->right){
		bst_delete(p->right);
		p->right = NULL;
		return;
	}
	free(p->left);
	p->left = NULL;
	return;
}

void bst_remove(bst * b, int k) { 
	bst * p = b;
	while(b){
		if(b->key == k)	break;
		else if(k > b->key){
			p = b;
			b = b->right;
		}
		else{
			p = b;
		 	b = b->left;
		}
	}
	if(b->right && b->left){
		remove_node2(b);
		return;
	}
	if(p->right){
		if(p->right->key == k && (b->left || b->right)){
			remove_node1(p, b, 1);
			return;
		}
		else if(p->right->key == k){
			bst_delete(p->right);
			p->right = NULL;
			return;
		}		
	}
	if(p->left){
		if(p->left->key == k && (b->left || b->right)){
			remove_node1(p, b, 0);
			return;
		}
		else if(p->left->key == k){
			bst_delete(p->left);
			p->left = NULL;
			return;
		}
	}
	return;
}

void bst_delete(bst * b) {
	if(!b)	return;
	bst_delete(b->left);
	bst_delete(b->right);
	free(b);
	return;
}

void bst_print(bst * b){
	if(!b) 	return;
	bst_print(b->left);
	printf("%d: %s \n", b->key, (char*)b->value);
	bst_print(b->right);
	return;
}

int bst_predecessor(bst * b, int k) {
	
	
	//~ if(k <= b->key && b->left){
		//~ return bst_predecessor(b->left, k);
	//~ }
	//~ else if(k > b->key && b->right!=NULL && bst_find_min(b->right)<k){
		//~ return bst_predecessor(b->right, k);
	//~ }
	//~ else{
		//~ return b->key;
	//~ }
}
