import java.util.LinkedList;
import java.util.List;
import java.io.*;



public class Graph<V> {

	public enum STATUS { UNEXPLORED, EXPLORED, EXPLORING }

	private List<GraphNode<V>> nodes;
	private List<Edge<V>> edges;

    public Graph () {
		this.nodes = new LinkedList<GraphNode<V>>();
		this.edges = new LinkedList<Edge<V>>();
	}

	public List<Edge<V>> getEdges(){
		return this.edges;
	}
	
    public List<GraphNode<V>> getNodes() {
        return this.nodes;
    }

    public List<Edge<V>> getNeighbors(GraphNode<V> n){
        return n.out_edges;
    }

    public GraphNode addNode(V value) {
		GraphNode<V> n = new GraphNode(value);
		this.nodes.add(n);
        return n;
    }

    public void addEdge(GraphNode<V> s, GraphNode<V> t) {
		Edge e = new Edge(this, s, t);
		s.out_edges.add(e);
		t.out_edges.add(e);
		this.edges.add(e);
        return;
    }

    public V getNodeValue(GraphNode<V> n) {
        return n.value;

    }

	private Edge getEdge(GraphNode<V> v1, GraphNode<V> v2){
		List<Edge<V>> le = this.edges;
		int n = le.size();
		Edge e;
		for(int i = 0; i<n; i++){
			e = le.get(i);
			if((e.node1==v1 && e.node2==v2) || (e.node1==v2 && e.node2==v1)){
				return e;
			}
		}
		return null;
	}

    public void removeEdge(GraphNode<V> v1, GraphNode<V> v2){
		Edge e = this.getEdge(v1, v2);
		this.edges.remove(e);
		v1.out_edges.remove(e);
		v2.out_edges.remove(e);
		return;
    }
    
    public void removeEdge(Edge e){
		this.edges.remove(e);
		e.edges_node1.remove(e);
		e.edges_node2.remove(e);
		return;
    }

    public void removeNode(GraphNode<V> v){
		for(int i = 0; i<v.out_edges.size(); i++){
			this.removeEdge(v.out_edges.get(i));
		}
		this.nodes.remove(v);
    }

    public static <V> Graph<V> readFF(File input){
		Graph g = new Graph();
		try{
			FileReader fr = new FileReader(input);  
			BufferedReader br=new BufferedReader(fr); 
			String line = br.readLine();  
			
			int n = Integer.parseInt(line.substring(0,1));
			for(int x = 1; x<=n; x++){
				g.addNode(x);
			}
			while((line = br.readLine())!=null)  {  
				n = Integer.parseInt(line.substring(0,1));
				GraphNode gn1 = (GraphNode)g.getNodes().get(n-1);
				n = Integer.parseInt(line.substring(2,3));
				GraphNode gn2 = (GraphNode)g.getNodes().get(n-1);
				g.addEdge(gn1, gn2); 
			}  
			fr.close(); 
		}catch(IOException e)  {  
			e.printStackTrace();  
		}       
		return g;
    }

    public String printAdj() {
		String s = "";
		for(int i = 0; i<this.nodes.size(); i++){
			GraphNode gn = this.nodes.get(i);
			s += this.getNodeValue(gn) + " -> " + this.getNeighbors(gn) + "\n";
		}
		return s;
    }

    @Override
    public String toString(){
		String s = this.nodes.size() + " " + this.edges.size() +"\n";
		Edge e;
		for(int i = 0; i<this.edges.size(); i++){
			e = this.edges.get(i);
			s+=e+"\n";
		}
		return s;
    }

	private void DFS(GraphNode<V> gn, int timestamp, List<GraphNode<V>> nds){
		nds.add(gn);
		gn.state = STATUS.EXPLORED;
		gn.timestamp = timestamp;
		System.out.print(gn + " -- ");
		int n = gn.out_edges.size();
		for(int i = 0; i<n; i++){
			Edge e = gn.out_edges.get(i);
			GraphNode gn1 = (e.node2!=gn) ? e.node2 : e.node1;
			if(gn1.state == STATUS.UNEXPLORED){
				this.DFS(gn1, timestamp+1, nds);
			}
		}
		return;
	}

    public int nConComp(){
		return this.getConComp().size();
    }

    public List<Graph<V>> getConComp(){
		List<Graph<V>> g = new LinkedList<Graph<V>>();
		int n = this.nodes.size();
		for(int i = 0; i<n; i++){
			GraphNode gn = this.nodes.get(i);
			if(gn.state == STATUS.UNEXPLORED){
				Graph<V> g1 = new Graph();
				List<GraphNode<V>> nds = g1.getNodes();
				this.DFS(gn, 1, nds);
				g.add(g1);
				System.out.println("\n");
			}
			
		}
		return g;
 
    }

    public static class GraphNode<V> {
		
		protected V value;
		protected List<Edge<V>> out_edges;
		protected STATUS state;
		protected int timestamp;
		
		protected GraphNode(V val){
			this.value = val;
			this.out_edges = new LinkedList<Edge<V>>();
			this.state = STATUS.UNEXPLORED;
			this.timestamp = 0;
		}
		
		@Override
		public String toString(){
			String s = "";
			s+= this.value;
			return s;
		}
	}
	
	public static class Edge<V> {
		protected int weight;
		protected GraphNode node1, node2;
		protected List<Edge<V>> edges_node1, edges_node2;
		protected List<Edge<V>> edges;
		
		protected Edge(Graph g, GraphNode n1, GraphNode n2){
			this.weight = 1;
			this.node1 = n1;
			this.node2 = n2;
			this.edges_node1 = n1.out_edges;
			this.edges_node2 = n2.out_edges;
			this.edges = g.getEdges();
		}
		
		@Override
		public String toString(){
			String s = "";
			s += this.node1 + "" + this.node2 + "  " + this.weight;
			return s;
		}
		
	}
}
