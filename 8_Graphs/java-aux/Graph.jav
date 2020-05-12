import java.util.LinkedList;
import java.util.List;
import java.io.*;



public class Graph<V> {

	public enum STATUS { UNEXPLORED, EXPLORED, EXPLORING }

	private List<GraphNode<V>> nodes;

    public Graph () {
		nodes = new LinkedList<GraphNode<V>>();
	}

    public List<GraphNode<V>> getNodes() {
        return this.nodes;
    }

    public List<GraphNode<V>> getNeighbors(GraphNode<V> n){
        return n.out_edges;
    }

    public GraphNode addNode(V value) {
		GraphNode<V> n = new GraphNode(value);
		this.nodes.add(n);
        return n;
    }

    public void addEdge(GraphNode<V> s, GraphNode<V> t) {
		s.out_edges.add(t);
		t.out_edges.add(s);
        return;
    }

    public V getNodeValue(GraphNode<V> n) {
        return n.value;
    }

    public void removeEdge(GraphNode<V> v1, GraphNode<V> v2){
		v1.out_edges.remove(v2);
		v2.out_edges.remove(v1);
		return;
    }

    public void removeNode(GraphNode<V> v){
		for(int i = 0; i<v.out_edges.size(); i++){
			this.removeEdge(v, v.out_edges.get(i));
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
		String s = this.nodes.size() + " ";
		String edges = "";
		int ctr = 0;
		for(int i = 0; i<this.nodes.size(); i++){
			GraphNode<V> gn = this.nodes.get(i);
			V val = this.getNodeValue(gn);
			List<GraphNode<V>> gnl = this.getNeighbors(gn);
			for(int j = 0; j<gnl.size(); j++){
				ctr++;
				edges += val + " " + this.getNodeValue(gnl.get(j)) + "\n";
			}
		}
		s += ctr + "\n";
		s+=edges;
		return s;
    }

	private void DFS(GraphNode<V> gn, int timestamp, List<GraphNode<V>> nds){
		nds.add(gn);
		gn.state = STATUS.EXPLORED;
		gn.timestamp = timestamp;
		System.out.print(gn + " -- ");
		int n = gn.out_edges.size();
		for(int i = 0; i<n; i++){
			GraphNode gn1 = gn.out_edges.get(i);
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
		int c = 0;
		for(int i = 0; i<n; i++){
			GraphNode gn = this.nodes.get(i);
			if(gn.state == STATUS.UNEXPLORED){
				c++;
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
		protected List<GraphNode<V>> out_edges;
		protected STATUS state;
		protected int timestamp;
		
		protected GraphNode(V val){
			this.value = val;
			this.out_edges = new LinkedList<GraphNode<V>>();
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
}
