package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;

/**
 * Test class for AdjacencyListGraph
 * Checks the expected outputs of the Graph abstract data type behaviors when using
 * an adjacency list graph data structure
 *
 * @author Dr. King
 * @author Siddhant Joshi
 *
 */
public class AdjacencyListGraphTest {

	private Graph<String, Integer> undirectedGraph;
    private Graph<String, Integer> directedGraph;
    
    /**
     * Create a new instance of an edge list graph before each test case executes
     */ 
    @Before
    public void setUp() {
        undirectedGraph = new AdjacencyListGraph<String, Integer>();
        directedGraph = new AdjacencyListGraph<String, Integer>(true);
    }
    
    private void buildUndirectedSample() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        
        undirectedGraph.insertEdge(v1, v2, 5);
        undirectedGraph.insertEdge(v1, v3, 10);
        undirectedGraph.insertEdge(v1, v4, 15);
        undirectedGraph.insertEdge(v1, v5, 20);
        undirectedGraph.insertEdge(v2, v3, 25);
        undirectedGraph.insertEdge(v2, v4, 30);
        undirectedGraph.insertEdge(v2, v5, 35);
        undirectedGraph.insertEdge(v3, v4, 40);
        undirectedGraph.insertEdge(v3, v5, 45);
        undirectedGraph.insertEdge(v4, v5, 50);
    }
    
    private void buildDirectedSample() {
        Vertex<String> v1 = directedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = directedGraph.insertVertex("Asheville");
        Vertex<String> v3 = directedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = directedGraph.insertVertex("Durham");
        Vertex<String> v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        
        directedGraph.insertEdge(v1, v2, 5);
        directedGraph.insertEdge(v1, v3, 10);
        directedGraph.insertEdge(v1, v4, 15);
        directedGraph.insertEdge(v1, v5, 20);
        directedGraph.insertEdge(v2, v3, 25);
        directedGraph.insertEdge(v2, v4, 30);
        directedGraph.insertEdge(v2, v5, 35);
        directedGraph.insertEdge(v3, v4, 40);
        directedGraph.insertEdge(v3, v5, 45);
        directedGraph.insertEdge(v4, v5, 50);
        directedGraph.insertEdge(v5, v6, 55);
    }

    /**
     * Test the output of the numVertices() behavior
     */     
    @Test
    public void testNumVertices() {
        buildUndirectedSample();
        assertEquals(5, undirectedGraph.numVertices());
        
        buildDirectedSample();       
        assertEquals(6, directedGraph.numVertices());
    }

    /**
     * Test the output of the vertices() behavior
     */ 
    @Test
    public void testVertices() {
        // We cannot call buildUndirectedSample() because
        // then we would not be able to reference specific edges
        // or vertices when testing
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        undirectedGraph.insertEdge(v1, v2, 5);
        undirectedGraph.insertEdge(v1, v3, 10);
        undirectedGraph.insertEdge(v1, v4, 15);
        undirectedGraph.insertEdge(v1, v5, 20);
        undirectedGraph.insertEdge(v2, v3, 25);
        undirectedGraph.insertEdge(v2, v4, 30);
        undirectedGraph.insertEdge(v2, v5, 35);
        undirectedGraph.insertEdge(v3, v4, 40);
        undirectedGraph.insertEdge(v3, v5, 45);
        undirectedGraph.insertEdge(v4, v5, 50);
        
        Iterator<Vertex<String>> it = undirectedGraph.vertices().iterator();
     
        assertTrue(it.hasNext());
        assertEquals("Raleigh", it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals("Asheville", it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals("Wilmington", it.next().getElement());
        assertTrue(it.hasNext());
        
        
        
        
        // DIRECTED
        // We cannot call buildDirectedSample() because
        // then we would not be able to reference specific edges
        // or vertices when testing     
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        directedGraph.insertEdge(v1, v2, 5);
        directedGraph.insertEdge(v1, v3, 10);
        directedGraph.insertEdge(v1, v4, 15);
        directedGraph.insertEdge(v1, v5, 20);
        directedGraph.insertEdge(v2, v3, 25);
        directedGraph.insertEdge(v2, v4, 30);
        directedGraph.insertEdge(v2, v5, 35);
        directedGraph.insertEdge(v3, v4, 40);
        directedGraph.insertEdge(v3, v5, 45);
        directedGraph.insertEdge(v4, v5, 50);
        directedGraph.insertEdge(v5, v6, 55);
        
        Iterator<Vertex<String>> it1 = directedGraph.vertices().iterator();
        
        assertTrue(it1.hasNext());
        assertEquals("Durham", it.next().getElement());
        assertTrue(it1.hasNext());
        assertEquals("Greenville", it.next().getElement());
    }

    /**
     * Test the output of the numEdges() behavior
     */ 
    @Test
    public void testNumEdges() {
    	buildDirectedSample();
        assertEquals(11, directedGraph.numEdges());
        buildUndirectedSample();
        assertEquals(10, undirectedGraph.numEdges());  
    }

    /**
     * Test the output of the edges() behavior
     */ 
    @Test
    public void testEdges() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        Iterator<Edge<Integer>> it = undirectedGraph.edges().iterator();
        
        assertTrue(it.hasNext());
        assertEquals(e1.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e2.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e3.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e4.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e5.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e6.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e7.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e8.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e9.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e10.getElement(), it.next().getElement());
        assertFalse(it.hasNext());
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        it = directedGraph.edges().iterator();
        
        assertTrue(it.hasNext());
        assertEquals(e1.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e2.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e3.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e4.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e5.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e6.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e7.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e8.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e9.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e10.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e11.getElement(), it.next().getElement());
        assertFalse(it.hasNext());
    }

    /**
     * Test the output of the getEdge(v1,v2) behavior
     */ 
    @Test
    public void testGetEdge() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(e1, undirectedGraph.getEdge(v1, v2));
        assertEquals(e1, undirectedGraph.getEdge(v2, v1));
        assertEquals(e2, undirectedGraph.getEdge(v1, v3));
        assertEquals(e3, undirectedGraph.getEdge(v1, v4));
        assertEquals(e4, undirectedGraph.getEdge(v1, v5));
        assertEquals(e5, undirectedGraph.getEdge(v2, v3));
        assertEquals(e6, undirectedGraph.getEdge(v2, v4));
        assertEquals(e7, undirectedGraph.getEdge(v2, v5));
        assertEquals(e8, undirectedGraph.getEdge(v3, v4));
        assertEquals(e9, undirectedGraph.getEdge(v3, v5));
        assertEquals(e10, undirectedGraph.getEdge(v4, v5));
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(e1, directedGraph.getEdge(v1, v2));
        assertEquals(e2, directedGraph.getEdge(v1, v3));
        assertEquals(e3, directedGraph.getEdge(v1, v4));
        assertEquals(e4, directedGraph.getEdge(v1, v5));
        assertEquals(e5, directedGraph.getEdge(v2, v3));
        assertEquals(e6, directedGraph.getEdge(v2, v4));
        assertEquals(e7, directedGraph.getEdge(v2, v5));
        assertEquals(e8, directedGraph.getEdge(v3, v4));
        assertEquals(e9, directedGraph.getEdge(v3, v5));
        assertEquals(e10, directedGraph.getEdge(v4, v5));
        assertEquals(e11, directedGraph.getEdge(v5, v6));
    }

    /**
     * Test the output of the endVertices(e) behavior
     */ 
    @Test
    public void testEndVertices() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(v1, undirectedGraph.endVertices(e1)[0]);
        assertEquals(v2, undirectedGraph.endVertices(e1)[1]);
        assertEquals(v1, undirectedGraph.endVertices(e2)[0]);
        assertEquals(v3, undirectedGraph.endVertices(e2)[1]);
        assertEquals(v1, undirectedGraph.endVertices(e3)[0]);
        assertEquals(v4, undirectedGraph.endVertices(e3)[1]);
        assertEquals(v1, undirectedGraph.endVertices(e4)[0]);
        assertEquals(v5, undirectedGraph.endVertices(e4)[1]);
        assertEquals(v2, undirectedGraph.endVertices(e5)[0]);
        assertEquals(v3, undirectedGraph.endVertices(e5)[1]);
        assertEquals(v2, undirectedGraph.endVertices(e6)[0]);
        assertEquals(v4, undirectedGraph.endVertices(e6)[1]);
        assertEquals(v2, undirectedGraph.endVertices(e7)[0]);
        assertEquals(v5, undirectedGraph.endVertices(e7)[1]);
        assertEquals(v3, undirectedGraph.endVertices(e8)[0]);
        assertEquals(v4, undirectedGraph.endVertices(e8)[1]);
        assertEquals(v3, undirectedGraph.endVertices(e9)[0]);
        assertEquals(v5, undirectedGraph.endVertices(e9)[1]);
        assertEquals(v4, undirectedGraph.endVertices(e10)[0]);
        assertEquals(v5, undirectedGraph.endVertices(e10)[1]);
        
   
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(v1, directedGraph.endVertices(e1)[0]);
        assertEquals(v2, directedGraph.endVertices(e1)[1]);
        assertEquals(v1, directedGraph.endVertices(e2)[0]);
        assertEquals(v3, directedGraph.endVertices(e2)[1]);
        assertEquals(v1, directedGraph.endVertices(e3)[0]);
        assertEquals(v4, directedGraph.endVertices(e3)[1]);
        assertEquals(v1, directedGraph.endVertices(e4)[0]);
        assertEquals(v5, directedGraph.endVertices(e4)[1]);
        assertEquals(v2, directedGraph.endVertices(e5)[0]);
        assertEquals(v3, directedGraph.endVertices(e5)[1]);
        assertEquals(v2, directedGraph.endVertices(e6)[0]);
        assertEquals(v4, directedGraph.endVertices(e6)[1]);
        assertEquals(v2, directedGraph.endVertices(e7)[0]);
        assertEquals(v5, directedGraph.endVertices(e7)[1]);
        assertEquals(v3, directedGraph.endVertices(e8)[0]);
        assertEquals(v4, directedGraph.endVertices(e8)[1]);
        assertEquals(v3, directedGraph.endVertices(e9)[0]);
        assertEquals(v5, directedGraph.endVertices(e9)[1]);
        assertEquals(v4, directedGraph.endVertices(e10)[0]);
        assertEquals(v5, directedGraph.endVertices(e10)[1]);
        assertEquals(v5, directedGraph.endVertices(e11)[0]);
        assertEquals(v6, directedGraph.endVertices(e11)[1]);
    }

    /**
     * Test the output of the opposite(v, e) behavior
     */ 
    @Test
    public void testOpposite() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(v1, undirectedGraph.opposite(v2, e1));
        assertEquals(v2, undirectedGraph.opposite(v1, e1));
        assertEquals(v3, undirectedGraph.opposite(v1, e2));
        assertEquals(v4, undirectedGraph.opposite(v1, e3));
        assertEquals(v5, undirectedGraph.opposite(v1, e4));
        assertEquals(v3, undirectedGraph.opposite(v2, e5));
        assertEquals(v4, undirectedGraph.opposite(v2, e6));
        assertEquals(v5, undirectedGraph.opposite(v2, e7));
        assertEquals(v4, undirectedGraph.opposite(v3, e8));
        assertEquals(v5, undirectedGraph.opposite(v3, e9));
        assertEquals(v5, undirectedGraph.opposite(v4, e10));
        
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(v1, directedGraph.opposite(v2, e1));
        assertEquals(v2, directedGraph.opposite(v1, e1));
        assertEquals(v3, directedGraph.opposite(v1, e2));
        assertEquals(v4, directedGraph.opposite(v1, e3));
        assertEquals(v5, directedGraph.opposite(v1, e4));
        assertEquals(v3, directedGraph.opposite(v2, e5));
        assertEquals(v4, directedGraph.opposite(v2, e6));
        assertEquals(v5, directedGraph.opposite(v2, e7));
        assertEquals(v4, directedGraph.opposite(v3, e8));
        assertEquals(v5, directedGraph.opposite(v3, e9));
        assertEquals(v5, directedGraph.opposite(v4, e10));
        assertEquals(v6, directedGraph.opposite(v5, e11));
    }

    /**
     * Test the output of the outDegree(v) behavior
     */ 
    @Test
    public void testOutDegree() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        assertEquals(4, undirectedGraph.outDegree(v1));
        assertEquals(4, undirectedGraph.outDegree(v2));
        assertEquals(4, undirectedGraph.outDegree(v3));
        assertEquals(4, undirectedGraph.outDegree(v4));
        assertEquals(4, undirectedGraph.outDegree(v5));
        assertEquals(v1, undirectedGraph.endVertices(e1)[0]);
        assertEquals(v2, undirectedGraph.endVertices(e1)[1]);
        assertEquals(v1, undirectedGraph.endVertices(e2)[0]);
        assertEquals(v3, undirectedGraph.endVertices(e2)[1]);
        assertEquals(v1, undirectedGraph.endVertices(e3)[0]);
        assertEquals(v4, undirectedGraph.endVertices(e3)[1]);
        assertEquals(v1, undirectedGraph.endVertices(e4)[0]);
        assertEquals(v5, undirectedGraph.endVertices(e4)[1]);
        assertEquals(v2, undirectedGraph.endVertices(e5)[0]);
        assertEquals(v3, undirectedGraph.endVertices(e5)[1]);
        assertEquals(v2, undirectedGraph.endVertices(e6)[0]);
        assertEquals(v4, undirectedGraph.endVertices(e6)[1]);
        assertEquals(v2, undirectedGraph.endVertices(e7)[0]);
        assertEquals(v5, undirectedGraph.endVertices(e7)[1]);
        assertEquals(v3, undirectedGraph.endVertices(e8)[0]);
        assertEquals(v4, undirectedGraph.endVertices(e8)[1]);
        assertEquals(v3, undirectedGraph.endVertices(e9)[0]);
        assertEquals(v5, undirectedGraph.endVertices(e9)[1]);
        assertEquals(v4, undirectedGraph.endVertices(e10)[0]);
        assertEquals(v5, undirectedGraph.endVertices(e10)[1]);
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        assertEquals(4, directedGraph.outDegree(v1));
        assertEquals(3, directedGraph.outDegree(v2));
        assertEquals(2, directedGraph.outDegree(v3));
        assertEquals(1, directedGraph.outDegree(v4));
        assertEquals(1, directedGraph.outDegree(v5));
        assertEquals(0, directedGraph.outDegree(v6));
        assertEquals(v1, directedGraph.endVertices(e1)[0]);
        assertEquals(v2, directedGraph.endVertices(e1)[1]);
        assertEquals(v1, directedGraph.endVertices(e2)[0]);
        assertEquals(v3, directedGraph.endVertices(e2)[1]);
        assertEquals(v1, directedGraph.endVertices(e3)[0]);
        assertEquals(v4, directedGraph.endVertices(e3)[1]);
        assertEquals(v1, directedGraph.endVertices(e4)[0]);
        assertEquals(v5, directedGraph.endVertices(e4)[1]);
        assertEquals(v2, directedGraph.endVertices(e5)[0]);
        assertEquals(v3, directedGraph.endVertices(e5)[1]);
        assertEquals(v2, directedGraph.endVertices(e6)[0]);
        assertEquals(v4, directedGraph.endVertices(e6)[1]);
        assertEquals(v2, directedGraph.endVertices(e7)[0]);
        assertEquals(v5, directedGraph.endVertices(e7)[1]);
        assertEquals(v3, directedGraph.endVertices(e8)[0]);
        assertEquals(v4, directedGraph.endVertices(e8)[1]);
        assertEquals(v3, directedGraph.endVertices(e9)[0]);
        assertEquals(v5, directedGraph.endVertices(e9)[1]);
        assertEquals(v4, directedGraph.endVertices(e10)[0]);
        assertEquals(v5, directedGraph.endVertices(e10)[1]);
        assertEquals(v5, directedGraph.endVertices(e11)[0]);
        assertEquals(v6, directedGraph.endVertices(e11)[1]);
    }

    /**
     * Test the output of the inDegree(v) behavior
     */ 
    @Test
    public void testInDegree() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);

        
        assertEquals(3, undirectedGraph.inDegree(v1));
        assertEquals(1, undirectedGraph.inDegree(v2));
        assertEquals(1, undirectedGraph.inDegree(v3));
        assertEquals(1, undirectedGraph.inDegree(v4));
        assertEquals(0, undirectedGraph.inDegree(v5));
        
        assertEquals(v1, undirectedGraph.endVertices(e1)[0]);
        assertEquals(v2, undirectedGraph.endVertices(e1)[1]);
        assertEquals(v1, undirectedGraph.endVertices(e2)[0]);
        assertEquals(v3, undirectedGraph.endVertices(e2)[1]);
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        
        assertEquals(0, directedGraph.inDegree(v1));
        assertEquals(1, directedGraph.inDegree(v2));
        assertEquals(1, directedGraph.inDegree(v3));
        assertEquals(1, directedGraph.inDegree(v4));
        assertEquals(0, directedGraph.inDegree(v5));
        assertEquals(0, directedGraph.inDegree(v6));
        assertEquals(v1, directedGraph.endVertices(e1)[0]);
        assertEquals(v2, directedGraph.endVertices(e1)[1]);
        assertEquals(v1, directedGraph.endVertices(e2)[0]);
        assertEquals(v3, directedGraph.endVertices(e2)[1]);
        assertEquals(v1, directedGraph.endVertices(e3)[0]);
    }

    /**
     * Test the output of the outgoingEdges(v) behavior
     */ 
    @SuppressWarnings("unchecked")
    @Test
    public void testOutgoingEdges() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");

        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);

        
        // We can use a custom arrayContains() helper method to check that
        // an array *contains* a certain target edge.
        // This is helpful for testing graph ADT behaviors where an order
        // of edges cannot be guaranteed (such as .outgoingEdges or .incomingEdges
        // in adjacencyMaps, etc.)      
        Edge<Integer>[] temp = (Edge<Integer>[])(new Edge[4]);
        int count = 0;
        Iterator<Edge<Integer>> it = undirectedGraph.outgoingEdges(v1).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        assertFalse(it.hasNext());
        assertTrue(arrayContains(temp, e1));
        assertTrue(arrayContains(temp, e2));
        assertTrue(arrayContains(temp, e3));
        assertTrue(arrayContains(temp, e4));
        
        temp = (Edge<Integer>[])(new Edge[4]);
        count = 0;
        it = undirectedGraph.outgoingEdges(v2).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        assertFalse(it.hasNext());
        assertTrue(arrayContains(temp, e1));
        assertTrue(arrayContains(temp, e5));
        assertTrue(arrayContains(temp, e6));
        assertTrue(arrayContains(temp, e7));
        
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        
        
        temp = (Edge<Integer>[])(new Edge[4]);
        count = 0;
        it = directedGraph.outgoingEdges(v1).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        assertFalse(it.hasNext());
        assertTrue(arrayContains(temp, e1));
        assertTrue(arrayContains(temp, e2));
        assertTrue(arrayContains(temp, e3));
        assertTrue(arrayContains(temp, e4));
    }
    
    // Helper method to check that an array contains a certain target.
    // This is helpful for testing graph ADT behaviors where an order
    // of edges cannot be guaranteed (such as .outgoingEdges or .incomingEdges)
    private boolean arrayContains(Edge<Integer>[] temp, Edge<Integer> target) {
        for(Edge<Integer> e : temp) {
            if(e == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * Test the output of the incomingEdges(v) behavior
     */ 
    @SuppressWarnings("unchecked")
    @Test
    public void testIncomingEdges() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);

        
        Edge<Integer>[] temp = (Edge<Integer>[])(new Edge[4]);
        int count = 0;
        Iterator<Edge<Integer>> it = undirectedGraph.incomingEdges(v1).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        assertFalse(it.hasNext());
        assertTrue(arrayContains(temp, e1));
        assertTrue(arrayContains(temp, e2));
        assertTrue(arrayContains(temp, e3));
        assertTrue(arrayContains(temp, e4));
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);

        
        temp = (Edge<Integer>[])(new Edge[1]);
        count = 0;
        it = directedGraph.incomingEdges(v2).iterator();
        assertTrue(it.hasNext());
        temp[count] = it.next();
        assertFalse(it.hasNext());
        assertTrue(arrayContains(temp, e1));
        
        temp = (Edge<Integer>[])(new Edge[2]);
        count = 0;
        it = directedGraph.incomingEdges(v3).iterator();
        temp[count] = it.next();
        count++;
        temp[count] = it.next();
        count++;
        assertFalse(it.hasNext());
        assertTrue(arrayContains(temp, e2));
        assertTrue(arrayContains(temp, e5));
    }

    /**
     * Test the output of the insertVertex(x) behavior
     */ 
    @Test
    public void testInsertVertex() {
        assertEquals(0, undirectedGraph.numVertices());
        Vertex<String> v1 = undirectedGraph.insertVertex("Fayetteville");
        assertEquals(1, undirectedGraph.numVertices());
        
        Iterator<Vertex<String>> it = undirectedGraph.vertices().iterator();
        assertTrue(it.hasNext());
        assertEquals(v1, it.next());
        assertFalse(it.hasNext());      
        
        Vertex<String> v2 = undirectedGraph.insertVertex("A");
        assertEquals(2, undirectedGraph.numVertices());
        assertEquals(0, undirectedGraph.numEdges());
        assertEquals(v2, it.next());
    }

    /**
     * Test the output of the insertEdge(v1, v2, x) behavior
     */ 
    @Test
    public void testInsertEdge() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        
        assertEquals(0, undirectedGraph.numEdges());
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 99);
        assertEquals(1, undirectedGraph.numEdges());
        Iterator<Edge<Integer>> it = undirectedGraph.edges().iterator();
        assertTrue(it.hasNext());
        assertEquals(e1, it.next());
        assertFalse(it.hasNext());
        
        Vertex<String> v3 = undirectedGraph.insertVertex("A");
        undirectedGraph.insertEdge(v1, v3, 15);
        assertEquals(15, (int) undirectedGraph.getEdge(v1, v3).getElement());
        assertEquals(2, undirectedGraph.numEdges());
    }

    /**
     * Test the output of the removeVertex(v) behavior
     */ 
    @Test
    public void testRemoveVertex() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        Iterator<Edge<Integer>> it = undirectedGraph.edges().iterator();
        assertTrue(it.hasNext());
        assertEquals(e1.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e2.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e3.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e4.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e5.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e6.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e7.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e8.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e9.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e10.getElement(), it.next().getElement());
        assertFalse(it.hasNext());
        
        assertEquals(5, undirectedGraph.numVertices());
        assertEquals(10, undirectedGraph.numEdges());
        undirectedGraph.removeVertex(v5);
        assertEquals(4, undirectedGraph.numVertices());
        assertEquals(6, undirectedGraph.numEdges());
        undirectedGraph.removeVertex(v4);
        assertEquals(3, undirectedGraph.numEdges());
        assertEquals(3, undirectedGraph.numVertices());
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        Vertex<String> v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        Edge<Integer> e11 = directedGraph.insertEdge(v5, v6, 55);
        
        it = directedGraph.edges().iterator();
        assertTrue(it.hasNext());
        assertEquals(e1.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e2.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e3.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e4.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e5.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e6.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e7.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e8.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e9.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e10.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e11.getElement(), it.next().getElement());
        assertFalse(it.hasNext());
        
        assertEquals(6, directedGraph.numVertices());
        assertEquals(11, directedGraph.numEdges());
        directedGraph.removeVertex(v6);
        assertEquals(5, directedGraph.numVertices());
        assertEquals(10, directedGraph.numEdges());
        directedGraph.removeVertex(v5);
        assertEquals(4, directedGraph.numVertices());
        assertEquals(6, directedGraph.numEdges());
        directedGraph.removeVertex(v4);
        assertEquals(3, directedGraph.numEdges());
        assertEquals(3, directedGraph.numVertices());
    }

    /**
     * Test the output of the removeEdge(e) behavior
     */ 
    @Test
    public void testRemoveEdge() {
        Vertex<String> v1 = undirectedGraph.insertVertex("Raleigh");
        Vertex<String> v2 = undirectedGraph.insertVertex("Asheville");
        Vertex<String> v3 = undirectedGraph.insertVertex("Wilmington");
        Vertex<String> v4 = undirectedGraph.insertVertex("Durham");
        Vertex<String> v5 = undirectedGraph.insertVertex("Greenville");
        Vertex<String> v6 = undirectedGraph.insertVertex("Boone");
        Edge<Integer> e1 = undirectedGraph.insertEdge(v1, v2, 5);
        Edge<Integer> e2 = undirectedGraph.insertEdge(v1, v3, 10);
        Edge<Integer> e3 = undirectedGraph.insertEdge(v1, v4, 15);
        Edge<Integer> e4 = undirectedGraph.insertEdge(v1, v5, 20);
        Edge<Integer> e5 = undirectedGraph.insertEdge(v2, v3, 25);
        Edge<Integer> e6 = undirectedGraph.insertEdge(v2, v4, 30);
        Edge<Integer> e7 = undirectedGraph.insertEdge(v2, v5, 35);
        Edge<Integer> e8 = undirectedGraph.insertEdge(v3, v4, 40);
        Edge<Integer> e9 = undirectedGraph.insertEdge(v3, v5, 45);
        Edge<Integer> e10 = undirectedGraph.insertEdge(v4, v5, 50);
        
        Iterator<Edge<Integer>> it = undirectedGraph.edges().iterator();
        assertTrue(it.hasNext());
        assertEquals(e1.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e2.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e3.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e4.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e5.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e6.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e7.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e8.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e9.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e10.getElement(), it.next().getElement());
        assertFalse(it.hasNext());
        
        
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(10, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e1);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(9, undirectedGraph.numEdges());
        undirectedGraph.removeEdge(e10);
        assertEquals(6, undirectedGraph.numVertices());
        assertEquals(8, undirectedGraph.numEdges());
        
        Edge<Integer> e11 = undirectedGraph.insertEdge(v5, v6, 50);
        
        assertEquals(1, undirectedGraph.outDegree(v6));
        undirectedGraph.removeEdge(e11);
        assertEquals(0, undirectedGraph.outDegree(v6));
        
        // DIRECTED
        v1 = directedGraph.insertVertex("Raleigh");
        v2 = directedGraph.insertVertex("Asheville");
        v3 = directedGraph.insertVertex("Wilmington");
        v4 = directedGraph.insertVertex("Durham");
        v5 = directedGraph.insertVertex("Greenville");
        v6 = directedGraph.insertVertex("Boone");
        e1 = directedGraph.insertEdge(v1, v2, 5);
        e2 = directedGraph.insertEdge(v1, v3, 10);
        e3 = directedGraph.insertEdge(v1, v4, 15);
        e4 = directedGraph.insertEdge(v1, v5, 20);
        e5 = directedGraph.insertEdge(v2, v3, 25);
        e6 = directedGraph.insertEdge(v2, v4, 30);
        e7 = directedGraph.insertEdge(v2, v5, 35);
        e8 = directedGraph.insertEdge(v3, v4, 40);
        e9 = directedGraph.insertEdge(v3, v5, 45);
        e10 = directedGraph.insertEdge(v4, v5, 50);
        
        it = directedGraph.edges().iterator();
        assertTrue(it.hasNext());
        assertEquals(e1.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e2.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e3.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e4.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e5.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e6.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e7.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e8.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e9.getElement(), it.next().getElement());
        assertTrue(it.hasNext());
        assertEquals(e10.getElement(), it.next().getElement());
     
       
        assertFalse(it.hasNext());
        
        assertEquals(6, directedGraph.numVertices());
        assertEquals(10, directedGraph.numEdges());
        directedGraph.removeEdge(e1);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(9, directedGraph.numEdges());
        directedGraph.removeEdge(e10);
        assertEquals(6, directedGraph.numVertices());
        assertEquals(8, directedGraph.numEdges());
    }
}
