#include "graph.h"
#include <stdlib.h>
#include <stdio.h>

typedef enum {UNEXPLORED, EXPLORED, EXPLORING} STATUS;

struct graph_node{
    void * value;
    linked_list * out_edges;

    // keep track status
    STATUS state;
    int timestamp;
};

struct graph{
    linked_list * nodes;
};

graph * graph_new() {
	graph *g = (graph*) malloc(sizeof(graph));
	g->nodes = linked_list_new();
	return g;
}

linked_list * graph_get_nodes(graph * g) {
	return g->nodes;
}

linked_list * graph_get_neighbors(graph * g, graph_node * n) {
	return n->out_edges;
}

graph_node * graph_add_node(graph * g, void * value) {
	graph_node *n = (graph_node*) malloc(sizeof(graph_node));
	n->out_edges = linked_list_new();
	n->state = UNEXPLORED;
	n->timestamp = 0;
	n->value = value;
	linked_list_add(g->nodes, n);
	return n;
}

void graph_add_edge(graph * g, graph_node * v1, graph_node * v2) {
	//~ printf("size v1 = %d, size v2 = %d, pre-add\n", linked_list_size(v1->out_edges), linked_list_size(v2->out_edges));
	linked_list_add(v1->out_edges, v2);
	linked_list_add(v2->out_edges, v1);
	//~ printf("size v1 = %d, size v2 = %d, after-add\n", linked_list_size(v1->out_edges), linked_list_size(v2->out_edges));
	return;
}

void * graph_get_node_value(graph_node * n) {
	return n->value;
}

void graph_remove_edge(graph* g, graph_node* v1, graph_node* v2) {
	linked_list_remove(v1->out_edges, v2);
	linked_list_remove(v2->out_edges, v1);
}

void graph_remove_node(graph* g, graph_node* v) {
	
	linked_list*l = v->out_edges;
	if(!l->head){
		linked_list_delete(l);
		linked_list_remove(g->nodes, v);
		free(v);
		return;
	}
	linked_list_node * ptr = l->head;
	while(ptr->next){
		graph_remove_edge(g, v, ptr->value);
		ptr = ptr->next;
	}
	graph_remove_edge(g, v, ptr->value);
	linked_list_delete(l);
	linked_list_remove(g->nodes, v);
	free(v);
}

void graph_delete(graph * g) {
	linked_list*l = g->nodes;
	int n = linked_list_size(l);
	for(int i = 0; i<n; i++){
		graph_remove_node(g, linked_list_get(l, i));
	}
	
	free(l);
	free(g);
}

graph* graph_read_ff(FILE* input) {
	graph *g = graph_new(); 
	char buff[255];
	fgets(buff, 255, input);
	char c[2];
	
	int n = atoi(&buff[0]);
	graph_node* nodes[n];
	for(int i = 1; i<=n; i++){
		sprintf(c, "%d", i);
		nodes[i-1] = graph_add_node(g, c);
	}
	int nn;
	int j = 5;
	while(fgets(buff, 255, input) == buff){
		n = atoi(&buff[j]);
		nn = atoi(&buff[j+2]);
		j+=5;
		//~ graph_add_edge(g, nodes[n-1], nodes[nn-1]);
	}
	
	return g;
}

void graph_print(graph* g) {

}

void graph_print_adj(graph* g) {
	int n = linked_list_size(g->nodes);
	for(int i = 0; i<n; i++){
		graph_node * gn = linked_list_get(g->nodes, i);
		printf("%s -> [", (char*)gn->value);
		int m = linked_list_size(gn->out_edges);
		
		for(int j = 0; j<m; j++){
			graph_node *gn1 = linked_list_get(gn->out_edges, j);
			printf(" %s ", (char*)gn1->value);
		}
		printf("]\n");
	}
}

void DFS(graph_node* gn, int timestamp, linked_list * g2){
	gn->state = EXPLORED;
	gn->timestamp = timestamp;
	linked_list_add(g2, gn);
	int n = linked_list_size(gn->out_edges);
	for(int i = 0; i<n; i++){
		gn = linked_list_get(gn->out_edges, i);
		if(gn->state == UNEXPLORED){
			DFS(gn, timestamp+1, g2);
		}
	}
	
}

int graph_n_con_comp(graph * g) {
	linked_list * graph_list = graph_get_con_comp(g);
	return linked_list_size(graph_list);
}

linked_list* graph_get_con_comp(graph* g) {
	linked_list * graph_list = linked_list_new();
	int n = linked_list_size(g->nodes);
	graph_node * gn = NULL;
	linked_list * g2 = NULL;
	for(int i = 0; i<n; i++){
		gn = linked_list_get(g->nodes, i);
		if(gn->state == UNEXPLORED){
			g2 = linked_list_new();
			DFS(gn, 1, g2);
			linked_list_add(graph_list, g2);
		}
		
	}
	return graph_list;
}
