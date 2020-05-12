import java.util.*;
public class GraphServices<V>{

	private static void DFSsweep(Graph.GraphNode gn, int timestamp){
		List<Graph.GraphNode> out_nodes = gn.outEdges;
		gn.state = Graph.GraphNode.Status.EXPLORING;
		gn.timestamp = timestamp; 
		for(Graph.GraphNode gnNext : out_nodes){
			if(gnNext.state == Graph.GraphNode.Status.UNEXPLORED){
				System.out.println(gn.value + " -> " + gnNext.value + " : TREE");
				DFSsweep(gnNext, timestamp+1);
			}
			else if(gnNext.state == Graph.GraphNode.Status.EXPLORING){
				System.out.println(gn.value + " -> " + gnNext.value + " : BACK");
			}
			else if(gnNext.state == Graph.GraphNode.Status.EXPLORED && gnNext.timestamp>timestamp){
				System.out.println(gn.value + " -> " + gnNext.value + " : FORWARD");
			}
			else{
				System.out.println(gn.value + " -> " + gnNext.value + " : CROSS");
			}
			
		}
		gn.state = Graph.GraphNode.Status.EXPLORED;
	}

	public static <V> void sweep(Graph<V> g) {
		List<Graph.GraphNode<V>> nodes = g.getNodes();
		for(Graph.GraphNode<V> gn : nodes){
			if(gn.state == Graph.GraphNode.Status.UNEXPLORED){
				DFSsweep(gn, 1);	
			}
		}
	}
  
	public static <V> void topologicalSort(Graph<V> g) {
	  
	}
  
	public static <V> void strongConnectedComponents(Graph<V> g) {
	  
	}
}
