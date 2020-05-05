#include <stdlib.h>
#include <stdio.h>
#include "tree.h"

struct tree {
	int key;
    void * value;
    struct tree * left;
    struct tree * right;
};

tree * build_tree_1() {

	tree * n6 = calloc(sizeof(tree), 1);
	n6->key = 6;

	tree * n3 = calloc(sizeof(tree), 1);
	n3->key = 3;

	tree * n12 = calloc(sizeof(tree), 1);
	n12->key = 12;

	tree * n1 = calloc(sizeof(tree), 1);
	n1->key = 1;

	tree * n5 = calloc(sizeof(tree), 1);
	n5->key = 5;

	tree * n7 = calloc(sizeof(tree), 1);
	n7->key = 7;

	tree * n15 = calloc(sizeof(tree), 1);
	n15->key = 15;

	n6->left = n3;
	n6->right = n12;

	n3->left = n1;
	n3->right = n5;

	n12->left = n7;
	n12->right = n15;

	return n6;
}

tree * build_tree_2() {

	tree * n6 = calloc(sizeof(tree), 1);
	n6->key = 6;

	tree * n3 = calloc(sizeof(tree), 1);
	n3->key = 3;

	tree * n12 = calloc(sizeof(tree), 1);
	n12->key = 12;

	tree * n1 = calloc(sizeof(tree), 1);
	n1->key = 1;

	tree * n5 = calloc(sizeof(tree), 1);
	n5->key = 5;

	tree * n7 = calloc(sizeof(tree), 1);
	n7->key = 7;

	tree * n15 = calloc(sizeof(tree), 1);
	n15->key = 15;

	n6->right = n3;
	n6->left = n12;

	n3->left = n1;
	n3->right = n5;

	n12->left = n7;
	n12->right = n15;

	return n6;
}

tree * build_tree_3() {

	tree * n6 = calloc(sizeof(tree), 1);
	n6->key = 6;

	tree * n3 = calloc(sizeof(tree), 1);
	n3->key = 3;

	tree * n12 = calloc(sizeof(tree), 1);
	n12->key = 12;

	tree * n1 = calloc(sizeof(tree), 1);
	n1->key = 1;

	tree * n5 = calloc(sizeof(tree), 1);
	n5->key = 5;

	n6->left = n3;
	
	n3->left = n1;
	n3->right = n5;

	n5->right = n12;

	return n6;
}

void tree_delete(tree * tt) {

	tree * t = tt;
	if (t == NULL)
		return;

	tree_delete(t->left);
	tree_delete(t->right);
	free(t);
}



int tree_is_bst(tree * tt) {
	if(!tt)	return 1;
	
	int bool = tree_is_bst(tt->left);
	if(tt->left)
		return tt->left->key < tt->key;
	if(tt->right)
		return tt->right->key > tt->key;
		
	return bool && tree_is_bst(tt->right);
	
	
}

int h_tree(tree * tt, int h){
	if(!tt)	return h;
	return h_tree(tt->left, h+1);
	return h_tree(tt->right, h+1);
	
}

int bal_aux(tree*tt){
	if(!tt)	return 0;
	
	int hs = bal_aux(tt->left);
	int hd = bal_aux(tt->right);
	
	if(hs<0 || hd<0)	
		return -1;
	
	int fdb = hs>=hd ? hs-hd : hd-hs;
	if(fdb <= 1)
		return (hs>=hd ? hs : hd) +1;
	
	return -1;
	
}

int tree_is_balanced(tree * tt) {
	
	int r = bal_aux(tt);
	if(r>0)
		return 1;
	else
		return 0;
}

int is_avl_aux(tree * tt){
	if(!tt)	return 0;
	
	int hs = bal_aux(tt->left);
	if(tt->left)
		if(tt->left->key > tt->key)
			return -1;
	if(tt->right)
		if(tt->right->key < tt->key)
			return -1;
	int hd = bal_aux(tt->right);
	
	if(hs<0 || hd<0)	
		return -1;
	
	int fdb = hs>=hd ? hs-hd : hd-hs;
	if(fdb <= 1)
		return (hs>=hd ? hs : hd) +1;
	
	return -1;
}

int tree_is_avl(tree * tt) {
	
	int r = is_avl_aux(tt);
	if(r>0)
		return 1;
	else
		return 0;
	//~ if(!tt)	return 1;
	
	//~ int bool = tree_is_avl(tt->left);
	
	//~ int bst = 1;
	//~ if(tt->left)
		//~ bst &= tt->left->key < tt->key;
	//~ if(tt->right)
		//~ bst &= tt->right->key > tt->key;
		
	//~ int blc = 1;
	//~ int l = 0, r = 0;
	//~ if(tt->left)	
		//~ l = h_tree(tt->left, 0);
	//~ if(tt->right)
		//~ r = h_tree(tt->right, 0);
	//~ int fb = l-r;
 	//~ if(fb<0) fb*=-1;
	//~ blc &= fb <= 1;
	
	//~ return bst && blc;
	
	//~ return bool && tree_is_avl(tt->right);
}
