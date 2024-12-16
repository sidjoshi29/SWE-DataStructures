package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.disjoint_set.DisjointSetForest;
import edu.ncsu.csc316.dsa.disjoint_set.UpTreeDisjointSetForest;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.priority_queue.AdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapAdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapPriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

/**
 * MinimumSpanningTreeUtil provides a collection of behaviors for computing
 * minimum spanning trees for a given graph.
 * 
 * The MinimumSpanningTreeUtil class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Siddhant Joshi
 *
 */
public class MinimumSpanningTreeUtil {
    
    /**
     * Returns a positional list of minimum spanning tree edges for the given graph
     * using Kruskal's minimum spanning tree algorithm.
     * 
     * @param <V>   the type of data in the graph vertices
     * @param <E>   the type of data in the graph edges
     * @param g graph the graph for which to compute a minimum spanning tree
     * @return a positional list of minimum spanning tree edges
     */
    public static <V, E extends Weighted> PositionalList<Edge<E>> kruskal(Graph<V, E> g) {
    	PositionalList<Edge<E>> tT = new PositionalLinkedList<Edge<E>>();
        PriorityQueue<Integer, Edge<E>> pPQ = new HeapPriorityQueue<Integer, Edge<E>>();
        DisjointSetForest<Vertex<V>> fF = new UpTreeDisjointSetForest<Vertex<V>>();
        
        //Put all the edges into a heap sorted by their weight
        for(Edge<E> e : g.edges()) {
        	pPQ.insert(e.getElement().getWeight(), e);
        }
        
        int components = g.numVertices();
        
        for(Vertex<V> v : g.vertices()) {
        	fF.makeSet(v);
        }
        //Keep adding edges until it is all in one tree
        while(components > 1) {
        	Edge<E> current = pPQ.deleteMin().getValue();
        	Vertex<V>[] ends = g.endVertices(current);
        	
        	Position<Vertex<V>> u = fF.find(ends[0]);
        	Position<Vertex<V>> v = fF.find(ends[1]);
        	
        	if(!u.equals(v)) {
        		fF.union(u, v);
        		tT.addLast(current);
        		components--;
        	}
        }
        
        return tT;
    }
    
    /**
     * Returns a positional list of minimum spanning tree edges for the given graph
     * using Prim-Jarnik's minimum spanning tree algorithm.
     * 
     * @param <V>   the type of data in the graph vertices
     * @param <E>   the type of data in the graph edges
     * @param g graph the graph for which to compute a minimum spanning tree
     * @return a positional list of minimum spanning tree edges
     */
    public static <V, E extends Weighted> PositionalList<Edge<E>> primJarnik(Graph<V, E> g) {
        AdaptablePriorityQueue<Integer, Vertex<V>> qQ = new HeapAdaptablePriorityQueue<>();
        Map<Vertex<V>, Integer> wW = new LinearProbingHashMap<>();
        Set<Vertex<V>> sS = new HashSet<>();
        Map<Vertex<V>, Entry<Integer, Vertex<V>>> eE = new LinearProbingHashMap<>();
        Map<Vertex<V>, Edge<E>> cC = new LinearProbingHashMap<>();
        
        PositionalList<Edge<E>> tT = new PositionalLinkedList<>();
        
        Vertex<V> src = g.vertices().iterator().next();
        
        for(Vertex<V> v : g.vertices()) {
            if(v == src) {
                wW.put(v, 0);
            } else {
                wW.put(v, Integer.MAX_VALUE);
            }
            eE.put(v, qQ.insert(wW.get(v), v));
        }
        while(!qQ.isEmpty()) {
            Entry<Integer, Vertex<V>> entry = qQ.deleteMin();
            Vertex<V> u = entry.getValue();
            if(cC.get(u) != null) {
                tT.addLast(cC.get(u));
            }
            sS.add(u);
            for(Edge<E> e : g.outgoingEdges(u)) {
                Vertex<V> z = g.opposite(u, e);
                int r = e.getElement().getWeight();
                if(!sS.contains(z) && r < wW.get(z)) {
                    wW.put(z, r);
                    cC.put(z, e);
                    qQ.replaceKey(eE.get(z), r);
                }
            }
        }
        return tT;
    }


}