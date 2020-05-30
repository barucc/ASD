public class Main {
	
	private Main() {}
	
	public static void main(String[] args) {
		Graph<String> gra = new Graph<String>();
	
		Node<String> a = new Node<String>(new String("a"));
		Node<String> b = new Node<String>(new String("b"));		
		Node<String> c = new Node<String>(new String("c"));		
		Node<String> d = new Node<String>(new String("d"));
		Node<String> e = new Node<String>(new String("e"));
		Node<String> f = new Node<String>(new String("f"));
		Node<String> g = new Node<String>(new String("g"));
		Node<String> h = new Node<String>(new String("h"));
		Node<String> i = new Node<String>(new String("i"));
		Node<String> j = new Node<String>(new String("j"));
		Node<String> k = new Node<String>(new String("k"));
		Node<String> l = new Node<String>(new String("l"));
		Node<String> m = new Node<String>(new String("m"));
		Node<String> n = new Node<String>(new String("n"));
		Node<String> o = new Node<String>(new String("o"));
		Node<String> p = new Node<String>(new String("p"));
		Node<String> q = new Node<String>(new String("q"));
		Node<String> r = new Node<String>(new String("r"));
		
		gra.insertNode(a); 		
		gra.insertNode(b); 		
		gra.insertNode(c); 		
		gra.insertNode(d); 		
		gra.insertNode(e); 		
		gra.insertNode(f); 		
		gra.insertNode(g); 		
		gra.insertNode(h); 		
		gra.insertNode(i); 		
		gra.insertNode(j); 		
		gra.insertNode(k); 		
		gra.insertNode(l);
		gra.insertNode(m); 		
		gra.insertNode(n); 		
		gra.insertNode(o); 		
		gra.insertNode(p); 		
		gra.insertNode(q); 		
		gra.insertNode(r); 
		
		System.out.println("Nodi inseriti"); 
		System.out.println("---"); 
		
		gra.insertEdge(a, b, 2); gra.insertEdge(b, a, 2);
		gra.insertEdge(a, h, 1); gra.insertEdge(h, a, 1);
		gra.insertEdge(b, f, 1); gra.insertEdge(f, b, 1);
		gra.insertEdge(b, c, 1); gra.insertEdge(c, b, 1);
		gra.insertEdge(c, d, 1); gra.insertEdge(d, c, 1);
		gra.insertEdge(d, e, 1); gra.insertEdge(e, d, 1);
		gra.insertEdge(d, k, 2); gra.insertEdge(k, d, 2);
		gra.insertEdge(e, i, 1); gra.insertEdge(i, e, 1); 	
		gra.insertEdge(e, f, 1); gra.insertEdge(f, e, 1); 
		gra.insertEdge(f, g, 1); gra.insertEdge(g, f, 1); 		
		gra.insertEdge(g, j, 1); gra.insertEdge(j, g, 1); 
		gra.insertEdge(g, h, 1); gra.insertEdge(h, g, 1); 
		gra.insertEdge(h, o, 2); gra.insertEdge(o, h, 2); 
		gra.insertEdge(i, j, 2); gra.insertEdge(j, i, 2); 
		gra.insertEdge(i, l, 1); gra.insertEdge(l, i, 1); 
		gra.insertEdge(j, n, 1); gra.insertEdge(n, j, 1); 
		gra.insertEdge(k, l, 1); gra.insertEdge(l, k, 1); 
		gra.insertEdge(k, p, 1); gra.insertEdge(p, k, 1); 		
		gra.insertEdge(l, m, 1); gra.insertEdge(m, l, 1); 
		gra.insertEdge(m, n, 1); gra.insertEdge(n, m, 1); 		
		gra.insertEdge(m, q, 1); gra.insertEdge(q, m, 1); 
		gra.insertEdge(n, o, 1); gra.insertEdge(o, n, 1); 
		gra.insertEdge(o, r, 1); gra.insertEdge(r, o, 1); 
		gra.insertEdge(p, q, 2); gra.insertEdge(q, p, 2); 
		gra.insertEdge(q, r, 2); gra.insertEdge(r, q, 2); 
		
		System.out.println("---"); 
		System.out.println("Archi inseriti"); 
		System.out.println("---");
		
		System.out.println("Grafo:");
		System.out.println(gra);
		
		//~ // Test per SSSP
		System.out.println("LP dal nodo '"+a+"' al nodo '"+p+"'");
		GraphServices.lp(gra, a, p);
		System.out.println("");
		
	}
}
