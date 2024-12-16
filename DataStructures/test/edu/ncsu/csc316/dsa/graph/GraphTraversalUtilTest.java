package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for GraphTraversalUtil
 * Checks the expected outputs of depth first search
 * and breadth first search
 *
 * @author Dr. King
 * @author Siddhant Joshi
 */
public class GraphTraversalUtilTest {
	
	/** Graph for testing */
	private Graph<String, Integer> graph;
	
	/**Vertex for testing A */
	private Vertex<String> a;
	/**Vertex for testing B */
	private Vertex<String> b;
	/**Vertex for testing C */
	private Vertex<String> c;
	/**Vertex for testing D */
	private Vertex<String> d;
	/**Vertex for testing E */
	private Vertex<String> e;
	
	/**
	 * Setting up the graph
	 */
	@Before
	public void setUp() {
		graph = new AdjacencyListGraph<String, Integer>(true);
		
		a = graph.insertVertex("A");
		b = graph.insertVertex("B");
		c = graph.insertVertex("C");
		d = graph.insertVertex("D");
		e = graph.insertVertex("E");

		
		graph.insertEdge(a, b, 1);
		graph.insertEdge(a, e, 2);
		graph.insertEdge(b, e, 3);
		graph.insertEdge(b, d, 4);
		graph.insertEdge(c, d, 5);
		graph.insertEdge(b, c, 6);
			
		
	}

    /**
     * Test the output of depth first search on a graph
     */ 
    @Test
    public void testDepthFirstSearch() {
    	Map<Vertex<String>, Edge<Integer>> result = GraphTraversalUtil.breadthFirstSearch(graph, a);
		assertEquals(4, result.size());
		assertEquals(1, result.get(b).getElement().intValue());
		assertEquals(2, result.get(e).getElement().intValue());
		assertEquals(4, result.get(d).getElement().intValue());
		assertEquals(6, result.get(c).getElement().intValue());
		
		
    }
    
    /**
     * Test the output of the breadth first search
     */ 
    @Test
    public void testBreadthFirstSearch() {
    	Map<Vertex<String>, Edge<Integer>> result = GraphTraversalUtil.depthFirstSearch(graph, a);
		assertEquals(4, result.size());
		assertEquals(1, result.get(b).getElement().intValue());
		assertEquals(6, result.get(c).getElement().intValue());
		assertEquals(4, result.get(d).getElement().intValue());
		assertEquals(3, result.get(e).getElement().intValue());

    }
    
}