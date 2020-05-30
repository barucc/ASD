import java.util.*;
public class GraphServices<V>{

	private static <V> void DFSsweep(Graph.GraphNode<V> gn, int timestamp){
		List<Graph.GraphNode<V>> out_nodes = gn.outEdges;
		gn.state = Graph.GraphNode.Status.EXPLORING;
		gn.timestamp = timestamp; 
		for(Graph.GraphNode<V> gnNext : out_nodes){
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
	
	
	
	private static <V> boolean DFSts(Graph.GraphNode<V> gn, List<Graph.GraphNode<V>> list, boolean b){
		gn.state = Graph.GraphNode.Status.EXPLORING;
		for(Graph.GraphNode<V> gnNext : gn.outEdges){
			if(gnNext.state == Graph.GraphNode.Status.EXPLORING){
				return b&&false;
			}
			else if(gnNext.state == Graph.GraphNode.Status.UNEXPLORED){
				b = b && DFSts(gnNext,list, b);
				gn.timestamp++;
				list.add(gnNext);
			}
		}
		gn.state = Graph.GraphNode.Status.EXPLORED;
		
		return b;
	}
  
	
  
	public static <V> void topologicalSort(Graph<V> g) {
		List<Graph.GraphNode<V>> nodes = g.getNodes();
		for(Graph.GraphNode<V> gn : nodes){
			gn.state = Graph.GraphNode.Status.UNEXPLORED;
			
		}
		boolean b = true;
		LinkedList<Graph.GraphNode<V>> list = new LinkedList<Graph.GraphNode<V>>();
		for(Graph.GraphNode<V> gn : nodes){
			if(gn.state == Graph.GraphNode.Status.UNEXPLORED){
				b = DFSts(gn, list, b);
			}
		}
		if(b==true)
		for(int i = 0; i<list.size(); i++){
			System.out.print(list.removeLast().value + "-> " );
		}
		System.out.println("");
	}
	
	
	
	
  
	private static <V> Graph.GraphNode<V> DFSscc(Graph.GraphNode<V> gn, int timestamp){
		List<Graph.GraphNode<V>> out_nodes = gn.outEdges;
		gn.state = Graph.GraphNode.Status.EXPLORED;
		gn.timestamp = timestamp;
		for(Graph.GraphNode<V> gnNext : out_nodes){
			if(gnNext.state == Graph.GraphNode.Status.UNEXPLORED){
				out_nodes.remove(gnNext);
				gnNext.outEdges.add(gn);
				DFSscc(gnNext, timestamp+1);
				
			}
			
			
		}
		gn.state = Graph.GraphNode.Status.UNEXPLORED;
		return gn;
	}
  
	public static <V> void strongConnectedComponents(Graph<V> g) {
		List<Graph.GraphNode<V>> nodes = g.getNodes();
		for(Graph.GraphNode<V> gn : nodes){
			if(gn.state == Graph.GraphNode.Status.UNEXPLORED){
				System.out.println("Sottografo fortemente connesso");
				Graph.GraphNode<V> gnR = DFSscc(gn, 1);	
				Graph.GraphNode<V> gnR2 = DFSscc(gn, 1);
				if(gnR.timestamp == gnR2.timestamp){
					
				}
			}
			
		}
	}
}
