import java.util.*;

public class GraphServices {

	private static <V> void bfsAux(Graph<V> g, Node<V> n){
		LinkedList<Node<V>> list = new LinkedList<Node<V>>();
		list.addLast(n);
		System.out.println(n.getElement());
		n.stato = Node.Stato.EXPLORED;
		Node<V> v = null, w = null;
		List<Edge<V>> edges = null;
		while(list.size() != 0){
			v = list.removeFirst();
			edges = (List<Edge<V>>)g.getOutEdges(v);
			for(Edge<V> e: edges){
				w = e.getTarget();
				if(w.stato == Node.Stato.UNEXPLORED){
					System.out.println(w.getElement());
					w.stato = Node.Stato.EXPLORED;
					list.addLast(w);
				}
			}
		}
	}
	
	public static <V> void bfs(Graph<V> g) {
		for(Node<V> n : g.getNodes()){
			if(n.stato == Node.Stato.UNEXPLORED){
				bfsAux(g, n);
				System.out.println("");
			}
			
		} 
	}

	private static <V> int maxWeight(Graph<V> g){
		int ctr = 0;
		int max = 0;
		int w = 0;
		for(Edge<V> e : g.getEdges()){
			w = e.getWeight();
			max = (max<w) ? w : max;
			ctr++;
		}
		return ctr*max;
	}

	public static <V> void sssp(Graph<V> g, Node<V> source) {
		int inf = maxWeight(g) + 1;
		HashMap<Node<V>, Integer> D = new HashMap<Node<V>, Integer>();
		HashMap<Node<V>, Node<V>> prev = new HashMap<Node<V>, Node<V>>();
		MinHeap<Node<V>> Q = new MinHeap<Node<V>>();
		D.put(source, 0);
		prev.put(source, null);
		for(Node<V> n : g.getNodes()){
			if(n!=source){
				D.put(n,inf);
				prev.put(n, null);	
			}
			Q.insert(D.get(n), n);
		}
		int alt = 0;
		Node<V> u = null, v = null;
		List<Edge<V>> edges = null;
		HeapEntry entry = null;
		while(Q.isEmpty()!=true){
			u = Q.removeMin().getValue();
			System.out.println(u + " -> "+ D.get(u));
			edges = (List<Edge<V>>)g.getOutEdges(u);
			for(Edge<V> e : edges){
				v = e.getTarget();
				entry = Q.get(v);
				if(entry != null){
					alt = D.get(u) + e.getWeight();
					if(alt<D.get(v)){
						D.replace(v, alt);
						prev.replace(v, u);
						Q.replaceKey(entry, alt); 
					}
				}
			}
		}
		System.out.println("--");
		System.out.println(prev);
		System.out.println(g);
		 
	}
	
	public static <V> void mst(Graph<V> G) {
		
	}
}




