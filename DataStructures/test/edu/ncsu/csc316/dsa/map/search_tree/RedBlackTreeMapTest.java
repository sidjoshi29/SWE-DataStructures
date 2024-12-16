package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;



/**
 * Test class for RedBlackTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a red-black tree data structure 
 *
 * @author Dr. King
 * @author Siddhant Joshi
 *
 */
public class RedBlackTreeMapTest {

    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a red-black tree-based map before each test case executes
     */  
    @Before
    public void setUp() {
        tree = new RedBlackTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(5, "five");
        tree.put(9, "nine");

        tree.put(7, "seven");
        
        //7 is now the root of the right subtree
        assertEquals(7, (int) tree.root().getElement().getKey());
        
        tree.put(6, "six");
        tree.put(8, "eight");
        
        tree.put(12, "twelve");
        tree.put(13, "thirteen");
        assertEquals(7, tree.size());
        
        
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
    	assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(3, "three");
        tree.put(16, "sixteen"); 
        tree.put(17, "seventeen");
        assertEquals(3, tree.size());
        assertEquals("three", tree.get(3));
        assertEquals("seventeen", tree.get(17));

    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());

        tree.put(10, "ten");
        tree.put(5, "five");
        tree.put(15, "fifteen");
        tree.put(3, "three");
        tree.put(7, "seven");
        tree.put(6, "six");
        tree.put(9, "nine");
        tree.put(12, "twelve");
        tree.put(13, "thirteen");
        tree.put(14, "fourteen");


        //six should be left after removal of 3
        assertEquals("three", tree.remove(3));
        assertEquals("six", tree.left(tree.root()).getElement().getValue()); 
        assertEquals(1, tree.getProperty(tree.left(tree.root()))); 
        assertEquals(0, tree.getProperty(tree.left(tree.left(tree.root())))); 
        assertEquals(0, tree.getProperty(tree.right(tree.left(tree.root())))); 

        //now if we remove 7 then 6 should be root
        assertEquals("seven", tree.remove(7)); 
        assertEquals("six", tree.left(tree.root()).getElement().getValue()); 
        assertEquals(1, tree.getProperty(tree.left(tree.root())));
        assertEquals(0, tree.getProperty(tree.left(tree.left(tree.root())))); 

        tree.remove(5);
        tree.remove(6);
        tree.remove(9);
        tree.remove(13);
        tree.remove(14);

        //after these removals the root should be 12 and on its right should be 15
        assertEquals("twelve", tree.root().getElement().getValue());
        assertEquals("fifteen", tree.right(tree.root()).getElement().getValue()); 
    }


    
    
}