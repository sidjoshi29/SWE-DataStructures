package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.priority_queue.AdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapAdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapAdaptablePriorityQueue.AdaptablePQEntry;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * ShortestPathUtil provides a collection of behaviors for computing shortest
 * path spanning trees for a given graph.
 * 
 * The ShortestPathUtil class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Siddhant Joshi
 *
 */
public class ShortestPathUtil {
    
    /**
     * For a connected graph, returns a map that represents shortest path costs to
     * all vertices computed using Dijkstra's single-source shortest path algorithm.
     * 
     * @param <V>   the type of data in the graph vertices
     * @param <E>   the type of data in the graph edges
     * @param graph the graph for which to compute the shortest path spanning tree
     * @param start the vertex at which to start computing the shorest path spanning
     *              tree
     * @return a map that represents the shortest path costs to all vertices in the
     *         graph
     */ 
    public static <V, E extends Weighted> Map<Vertex<V>, Integer> dijkstra(Graph<V, E> graph, Vertex<V> start) {
        //NOTE: since Dijkstra's algorithm is very similar to Prim-Jarnik's algorithm,
        //     you should review the provided Prim-Jarnik implementation in the next
        //     section of the lab on Minimum Spanning Trees
    	AdaptablePriorityQueue<Integer, Vertex<V>> q = new HeapAdaptablePriorityQueue<Integer, Vertex<V>>();
    	Map<Vertex<V>, Integer> c = new LinearProbingHashMap<Vertex<V>, Integer>();
    	Set<Vertex<V>> s = new HashSet<Vertex<V>>();
    	Map<Vertex<V>, AdaptablePQEntry<Integer, Vertex<V>>> edge = new LinearProbingHashMap<Vertex<V>, AdaptablePQEntry<Integer, Vertex<V>>>();
    	
    	for(Vertex<V> v : graph.vertices()) {
    		if(v.equals(start)) {
    			c.put(v, 0);
    		}
    		
    		else {
    			c.put(v, Integer.MAX_VALUE);
    		}
    		
    		int currentCost = c.get(v);
    		AdaptablePQEntry<Integer, Vertex<V>> entry = (AdaptablePQEntry<Integer, Vertex<V>>) q.insert(currentCost, v);
    		edge.put(v, entry);
    	}
    	
    	while(!q.isEmpty()) {
    		AdaptablePQEntry<Integer, Vertex<V>> entry = (AdaptablePQEntry<Integer, Vertex<V>>) q.deleteMin();
    		Vertex<V> u = entry.getValue();
    		s.add(u);
    		for(Edge<E> e : graph.outgoingEdges(u)) {
    			Vertex<V> z = graph.opposite(u, e);
    			if(!s.contains(z)) {
    				int r = e.getElement().getWeight() + c.get(u);
    				
    				if(r < c.get(z)) {
    					c.put(z, r);
    					q.replaceKey(edge.get(z), r);
    				}
    			}
    		}
    	}
    	
    	return c;
    }
    
    /**
     * For a connected graph, returns a map that represents shortest path spanning
     * tree edges computed using Dijkstra's single-source shortest path algorithm.
     * 
     * @param <V>       the type of data in the graph vertices
     * @param <E>       the type of data in the graph edges
     * @param graph         the graph for which to compute the shortest path spanning
     *                  tree
     * @param start         the vertex at which to start computing the shortest path
     *                  spanning tree
     * @param costs the map of shortest path costs to reach each vertex in the
     *                  graph
     * @return a map that represents the shortest path spanning tree edges
     */ 
    public static <V, E extends Weighted> Map<Vertex<V>, Edge<E>> shortestPathTree(Graph<V, E> graph, Vertex<V> start, Map<Vertex<V>, Integer> costs) {
    	// Create a map to store edges in the shortest path tree
    	Map<Vertex<V>, Edge<E>> map = new LinearProbingHashMap<Vertex<V>, Edge<E>>();
    	for(Entry<Vertex<V>, Integer> v : costs.entrySet()) {
    		Vertex<V> current = v.getKey();
    		if(!current.equals(start)) {
    			for(Edge<E> e : graph.incomingEdges(current)) {
    				Vertex<V> oppo = graph.opposite(current, e);
    				if(costs.get(current) == costs.get(oppo) + e.getElement().getWeight()) {
    					map.put(current, e);
    				}
    			}
    		}
    	}
    	return map;
    }
    
 
}