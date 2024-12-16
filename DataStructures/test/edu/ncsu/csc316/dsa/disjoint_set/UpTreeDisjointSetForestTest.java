package edu.ncsu.csc316.dsa.disjoint_set;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for UpTreeDisjointSetForest
 * Checks the expected outputs of the Disjoint Set abstract data type 
 * behaviors when using an up-tree data structure
 *
 * @author Dr. King
 * @author Siddhant Joshi
 *
 */
public class UpTreeDisjointSetForestTest {

    private DisjointSetForest<String> set;

    /**
     * Create a new instance of a up-tree forest before each test case executes
     */     
    @Before
    public void setUp() {
        set = new UpTreeDisjointSetForest<>();
    }
    
    /**
     * Test the output of the makeSet behavior
     */ 
    @Test
    public void testMakeSet() {
        Position<String> one = set.makeSet("one");
        assertEquals("one", one.getElement());
        Position<String> two = set.makeSet("two");
        assertEquals("two", two.getElement());
        
        Position<String> three = set.makeSet("three");
        assertEquals("three", three.getElement());
        
        
        assertNull(set.find("four"));
        assertEquals("three", set.find("three").getElement());
        assertEquals("two", set.find("two").getElement());
        assertEquals("one", set.find("one").getElement());

    }

    /**
     * Test the output of the union-find behaviors
     */     
    @Test
    public void testUnionFind() {
        Position<String> one = set.makeSet("one");
        Position<String> two = set.makeSet("two");
        Position<String> three = set.makeSet("three");
        Position<String> four = set.makeSet("four");
        Position<String> five = set.makeSet("five");
        Position<String> six = set.makeSet("six");
        Position<String> seven = set.makeSet("seven");
        Position<String> eight = set.makeSet("eight");
        Position<String> nine = set.makeSet("nine");
        Position<String> ten = set.makeSet("ten");
        
        assertEquals(one, set.find("one"));
        
        //Make a bunch of pairs 1-2, 3-4, etc
        set.union(one, two);
        set.union(three, four);
        set.union(five, six);
        assertEquals(two.getElement(), set.find("two").getElement());
        assertEquals(two, set.find("one"));
        assertEquals(four, set.find("three"));
        assertEquals(four, set.find("four"));
        assertEquals(six, set.find("five"));
        assertEquals(six, set.find("six"));
        
        set.union(seven, eight);
        set.union(nine, ten);
        assertEquals(eight.getElement(), set.find("eight").getElement());
        assertEquals(eight, set.find("seven"));
        assertEquals(ten, set.find("nine"));
        assertEquals(ten, set.find("ten"));
        
        // you should draw out examples by hand (or use the examples
        // in the lecture slides or textbook) to help guide your test cases.
        // Be sure to perform find operations followed by union
        // operations to make sure the appropriate root notes are
        // returned and used when union-ing

    }
}