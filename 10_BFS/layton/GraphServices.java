import java.util.*;

public class GraphServices {

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
	
	public static <V> void lp(Graph<V> g, Node<V> source, Node<V> target) {
		int inf = -1;
		HashMap<Node<V>, Integer> D = new HashMap<Node<V>, Integer>();
		HashMap<Node<V>, Node<V>> prev = new HashMap<Node<V>, Node<V>>();
		MyMap<Node<V>> Q = new MyMap<Node<V>>();
		D.put(source, 0);
		prev.put(source, null);
		for(Node<V> n : g.getNodes()){
			if(n!=source){
				D.put(n,inf);
				prev.put(n, null);	
			}
			Q.add(D.get(n), n);
		}
		int alt = 0;
		Node<V> u = null, v = null;
		List<Edge<V>> edges = null;
		Entry<Node<V>> entry = null;
		while(Q.isEmpty()!=true){
			u = Q.removeMax();
			System.out.println(u + " -> "+ D.get(u));
			edges = (List<Edge<V>>)g.getOutEdges(u);
			for(Edge<V> e : edges){
				v = e.getTarget();
				entry = Q.get(v);
				if(entry != null){
					alt = D.get(u) + e.getWeight();
					if(alt>D.get(v)){
						D.replace(v, alt);
						prev.replace(v, u);
						if(target == v){
							break;
						}
						Q.replaceKey(entry, alt); 
					}
				}
			}
		}
		System.out.println("--");
		
		Node<V> nn = target;
		LinkedList<Node<V>> ll = new LinkedList<Node<V>>();
		while(nn!=null){
			ll.addFirst(nn);
			nn = prev.get(nn);
		}
		System.out.println("");
		System.out.println(ll);
		
		 
	}
	
	
}




