import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Graph<V> {

    private LinkedList<GraphNode<V>> nodes;
    private int n_nodes;
    private int n_edges;

    public Graph () {
        this.nodes = new LinkedList<GraphNode<V>>();
    }

    @SuppressWarnings("unchecked")
	public List<GraphNode<V>> getNodes() {
    	List<GraphNode<V>> ret = new LinkedList<>();
    	for(GraphNode<V> n : this.nodes) {
    		try {
				ret.add((GraphNode<V>) n.clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
    	}
        return (List<GraphNode<V>>) ret;
    }

    @SuppressWarnings("unchecked")
	public List<GraphNode<V>> getNeighbors(GraphNode<V> n){
    	List<GraphNode<V>> ret = new LinkedList<>();
    	for(GraphNode<V> edge : n.outEdges) {
    		try {
				ret.add((GraphNode<V>) edge.clone());
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
    	}
        return (List<GraphNode<V>>) ret;
    }

    public GraphNode<V> addNode(V value) {
        GraphNode<V> n = new GraphNode<V>();
        n.value = value;
        n.outEdges = new LinkedList<GraphNode<V>>();
        n.state = GraphNode.Status.UNEXPLORED;
        n.timestamp = 0;
        for(GraphNode<V> node : this.nodes) {
        	if(node.value.equals(value))
        		return node;
        }
        this.nodes.add(n);
        this.n_nodes++;
        return n;
    }

    public void addEdge(GraphNode<V> s, GraphNode<V> t) {
        s.outEdges.add(t);
        this.n_edges++;
    }

    public V getNodeValue(GraphNode<V> n) {
        return n.value;
    }
    
    public void removeEdge(GraphNode<V> v1, GraphNode<V> v2){
    	removeEdgeAux(v1,v2);
		this.n_edges--;
    }
    
    private void removeEdgeAux(GraphNode<V> v1, GraphNode<V> v2) {
    	Iterator<GraphNode<V>> it = this.nodes.iterator();
    	while(it.hasNext()) {
    		GraphNode<V> n1 = it.next();
    		Iterator<GraphNode<V>> it2 = n1.outEdges.iterator();
    		if(n1.equals(v1)) {
	    		while(it2.hasNext()) {
	    			GraphNode<V> n2 = it2.next();
	    			if(n2.equals(v2)) { 
	    				it2.remove();
	    				return;
	    			}
	    		}
    		}
    	}
    }

    public void removeNode(GraphNode<V> v){
    	if(this.nodes.contains(v)) {
	    	for(GraphNode<V> e : v.outEdges) {
	    		this.removeEdgeAux(v, e);
	    		this.n_edges--;
	    	}
    	}
    	this.nodes.remove(v);
    }
    
    @Override
    public String toString(){
    	HashMap<GraphNode<V>, Graph.GraphNode.Status> savedStatus = new HashMap<>();
    	for(GraphNode<V> node : this.nodes) {
    		savedStatus.put(node, node.state);
    		node.state = Graph.GraphNode.Status.UNEXPLORED;
    	}
    	
    	StringBuffer toRet = new StringBuffer();
    	toRet.append(this.n_nodes + " " + this.n_edges + "\n");
    	for(GraphNode<V> node : this.nodes) {
    		if(node.state == Graph.GraphNode.Status.UNEXPLORED)
    			DFSprintEdges(node, toRet);
    	}
    	
    	for(GraphNode<V> node : this.nodes) {
    		node.state = savedStatus.get(node);
    	}
    	return toRet.toString();
    }

    private void DFSprintEdges(GraphNode<V> node, StringBuffer str) {
    	if(node.state != GraphNode.Status.UNEXPLORED)
    		return;
    	node.state = GraphNode.Status.EXPLORING;
    	for(GraphNode<V> e : node.outEdges) {
    		str.append(node.value + " " + e.value + "\n");
    	}
    	for(GraphNode<V> e : node.outEdges) {
    		if(e.state == GraphNode.Status.UNEXPLORED)
    			DFSprintEdges(e, str);
    	}
    	node.state = GraphNode.Status.EXPLORED;
	}

	public static class GraphNode<V> implements Cloneable{
        public enum Status {UNEXPLORED, EXPLORED, EXPLORING}

        protected V value;
        protected LinkedList<GraphNode<V>> outEdges;

        // keep track status
        protected Status state;
        protected int timestamp;

		@Override
		public String toString() {
			return "GraphNode [value=" + value + ", state=" + state + "]";
		}

		@Override
		protected Object clone() throws CloneNotSupportedException {
			return (GraphNode<V>) this;
		}
    }
}
