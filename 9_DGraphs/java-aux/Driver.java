import java.io.File;

public class Driver {

    public static void print() {
        System.out.println("Richiesto argomento: {graph, sweep, top_sort, strong_cc}");
    }

    public static void main(String[] argv) {

        if (argv.length < 1) {
            print();
            return;
        }
        
        if (argv[0].equals("graph")) {
            Graph<String> graph = new Graph<String>();

            Graph.GraphNode<String> a = graph.addNode("a");
            Graph.GraphNode<String> b = graph.addNode("b");
            Graph.GraphNode<String> c = graph.addNode("c");
            Graph.GraphNode<String> d = graph.addNode("d");
            Graph.GraphNode<String> e = graph.addNode("e");
            Graph.GraphNode<String> f = graph.addNode("f");
            Graph.GraphNode<String> g = graph.addNode("g");
            Graph.GraphNode<String> h = graph.addNode("h");

            graph.addEdge(a, b);
            graph.addEdge(a, f);
            graph.addEdge(b, c);
            graph.addEdge(b, f);
            graph.addEdge(c, d);
            graph.addEdge(d, b);
            graph.addEdge(e, f);
            graph.addEdge(f, c);
            graph.addEdge(g, h);

            System.out.println("Grafo iniziale");
            System.out.println(graph);

            System.out.println("Rimozione " + f + "," + c);
            graph.removeEdge(f,c);
            System.out.println(graph);

            System.out.println("Rimozione " + c + "," + b);
            graph.removeEdge(c,b);
            System.out.println(graph);

            System.out.println("Rimozione " + d + "," + c);
            graph.removeEdge(d,c);
            System.out.println(graph);

            System.out.println("Aggiunta " + c + "," + f);
            graph.addEdge(c,f);
            System.out.println(graph);

            System.out.println("Aggiunta " + d + "," + c);
            graph.removeEdge(d,c);
            System.out.println(graph);

            System.out.println("Rimozione nodo" + d);
            graph.removeNode(d);
            System.out.println(graph);

            System.out.println("Rimozione nodo" + f);
            graph.removeNode(f);
            System.out.println(graph);

            System.out.println("Rimozione nodo" + c);
            graph.removeNode(c);
            System.out.println(graph);
        }
        else if (argv[0].equals("sweep")) {
        	Graph<String> graph = new Graph<String>();

            Graph.GraphNode<String> a = graph.addNode("a");
            Graph.GraphNode<String> b = graph.addNode("b");
            Graph.GraphNode<String> c = graph.addNode("c");
            Graph.GraphNode<String> d = graph.addNode("d");
            Graph.GraphNode<String> e = graph.addNode("e");
            Graph.GraphNode<String> f = graph.addNode("f");
            Graph.GraphNode<String> g = graph.addNode("g");
            Graph.GraphNode<String> h = graph.addNode("h");

            graph.addEdge(a, b);
            graph.addEdge(a, f);
            graph.addEdge(b, c);
            graph.addEdge(b, f);
            graph.addEdge(c, d);
            graph.addEdge(d, b);
            graph.addEdge(e, f);
            graph.addEdge(f, c);
            graph.addEdge(g, h);
            
            System.out.println("Grafo:");
            System.out.println(graph);
            GraphServices.sweep(graph);
        }
        else if (argv[0].equals("top_sort")) {
        	Graph<String> graph = new Graph<String>();

            Graph.GraphNode<String> a = graph.addNode("a");
            Graph.GraphNode<String> b = graph.addNode("b");
            Graph.GraphNode<String> c = graph.addNode("c");
            Graph.GraphNode<String> d = graph.addNode("d");
            Graph.GraphNode<String> e = graph.addNode("e");
            Graph.GraphNode<String> f = graph.addNode("f");
            Graph.GraphNode<String> g = graph.addNode("g");
            Graph.GraphNode<String> h = graph.addNode("h");

            graph.addEdge(a, b);
            graph.addEdge(a, f);
            graph.addEdge(b, c);
            graph.addEdge(b, f);
            graph.addEdge(c, d);
            graph.addEdge(d, b);
            graph.addEdge(e, f);
            graph.addEdge(f, c);
            graph.addEdge(g, h);
            
            System.out.println("Grafo:");
            System.out.println(graph);
            
            System.out.println("Primo tentativo: (dovrebbe fallire)");
            GraphServices.topologicalSort(graph);
            
            System.out.println("Rimozione arco (d,b)");
            graph.removeEdge(d, b);
            
            System.out.println("Primo tentativo: (dovrebbe riuscire)");
            GraphServices.topologicalSort(graph);
        }
        else if (argv[0].equals("strong_cc")) {
        	Graph<String> graph = new Graph<String>();

            Graph.GraphNode<String> a = graph.addNode("a");
            Graph.GraphNode<String> b = graph.addNode("b");
            Graph.GraphNode<String> c = graph.addNode("c");
            Graph.GraphNode<String> d = graph.addNode("d");
            Graph.GraphNode<String> e = graph.addNode("e");
            Graph.GraphNode<String> f = graph.addNode("f");
            Graph.GraphNode<String> g = graph.addNode("g");
            Graph.GraphNode<String> h = graph.addNode("h");

            graph.addEdge(a, b);
            graph.addEdge(a, f);
            graph.addEdge(b, c);
            graph.addEdge(b, f);
            graph.addEdge(c, d);
            graph.addEdge(d, b);
            graph.addEdge(e, f);
            graph.addEdge(f, c);
            graph.addEdge(g, h);
            
            System.out.println("Grafo:");
            System.out.println(graph);
            
            GraphServices.strongConnectedComponents(graph);
        }
    } //main
} // class
