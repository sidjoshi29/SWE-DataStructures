package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * Test class for MinimumSpanningTreeUtil
 * Checks the expected outputs of Prim-Jarnik's algorithm
 * and Kruskal's algorithm
 *
 * @author Dr. King
 * @author Siddhant Joshi
 *
 */
public class MinimumSpanningTreeUtilTest {

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


	/**
	 * Sets up the graph
	 */
	@Before
public void setUp() {

		graph = new AdjacencyListGraph<String, EdgePath>(true);
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


	}

    /**
     * Test the output of Prim-Jarnik's algorithm
     */ 
    @Test
    public void testPrimJarnik() {
    	PositionalList<Edge<EdgePath>> result = MinimumSpanningTreeUtil.primJarnik(graph);
		Iterator<Edge<EdgePath>> it = result.iterator();
		assertEquals(2, it.next().getElement().getWeight());
		assertEquals(1, it.next().getElement().getWeight());
		assertEquals(2, it.next().getElement().getWeight());
		assertFalse(it.hasNext());
    }

    /**
     * Test the output of Kruskal's algorithm
     */ 
    @Test
    public void testKruskal() {
    	PositionalList<Edge<EdgePath>> result = MinimumSpanningTreeUtil.kruskal(graph);

		assertEquals(3, result.size());
		Iterator<Edge<EdgePath>> it = result.iterator();
		assertEquals(1, it.next().getElement().getWeight());
		assertEquals(1, it.next().getElement().getWeight());
		assertEquals(2, it.next().getElement().getWeight());
		assertFalse(it.hasNext());
    }


    

}