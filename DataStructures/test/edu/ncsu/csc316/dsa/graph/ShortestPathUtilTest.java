package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * Test class for ShortestPathUtil
 * Checks the expected outputs of Dijksra's algorithm
 * and the shortest path tree construction method
 *
 * @author Dr. King
 * @author Siddhant Joshi
 *
 */
public class ShortestPathUtilTest {

	/**Graph for testing*/
	private Graph<String, EdgePath> graph;

	/**Vertex for testing*/
	private Vertex<String> a;
	/**Vertex for testing*/
	private Vertex<String> b;
	/**Vertex for testing*/
	private Vertex<String> c;
	/**Vertex for testing*/
	private Vertex<String> d;
	/**Costs from start to a given vertex*/
	private Map<Vertex<String>, Integer> p;

	/**
	 * Sets up the graph
	 */
	@Before
	public void setUp() {

		graph = new AdjacencyListGraph<String, EdgePath>(true);
		p = new LinearProbingHashMap<Vertex<String>, Integer>();
		a = graph.insertVertex("A");
		b = graph.insertVertex("B");
		c = graph.insertVertex("C");
		d = graph.insertVertex("D");
		graph.insertEdge(a, d, new EdgePath(2));
		graph.insertEdge(d, b, new EdgePath(2));
		graph.insertEdge(d, c, new EdgePath(1));
		graph.insertEdge(a, c, new EdgePath(10));
		graph.insertEdge(c, b, new EdgePath(3));
		graph.insertEdge(b, c, new EdgePath(1));

		p.put(d, 2);
		p.put(a, 0);
		p.put(b, Integer.MAX_VALUE);
		p.put(c, 99);
	}
    /**
     * Test the output of Dijkstra's algorithm
     */ 
    @Test
    public void testDijkstra() {
    	Map<Vertex<String>, Integer> dijkstraRes = ShortestPathUtil.dijkstra(graph, a);

		assertEquals(4, dijkstraRes.size());
		assertEquals(0, dijkstraRes.get(a).intValue());
		assertEquals(2, dijkstraRes.get(d).intValue());
		assertEquals(3, dijkstraRes.get(c).intValue());
		assertEquals(4, dijkstraRes.get(b).intValue());
    }

    /**
     * Test the output of the shortest path tree construction method
     */ 
    @Test
    public void testShortestPathTree() {
    	Map<Vertex<String>, Edge<EdgePath>> res = ShortestPathUtil.shortestPathTree(graph, a, p);
		assertEquals(1, res.size());
		assertEquals(2, res.get(d).getElement().getWeight());
    }

    


}