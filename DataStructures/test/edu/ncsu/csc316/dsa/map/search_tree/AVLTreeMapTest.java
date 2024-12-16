package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


/**
 * Test class for AVLTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an AVL tree data structure 
 *
 * @author Dr. King
 * @author Siddhant Joshi
 *
 */
public class AVLTreeMapTest {

    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of an AVL tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new AVLTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        tree.put(5, "five");
        tree.put(3, "three");
        assertEquals("five", tree.root().getElement().getValue());
        tree.put(2,  "two");
        assertEquals("three", tree.root().getElement().getValue());
        
        tree.put(7, "seven");
        tree.put(9, "nine");
        assertEquals("seven", tree.right(tree.root()).getElement().getValue());
        
        tree.put(6, "six");
        assertEquals("five", tree.root().getElement().getValue());
        assertEquals("three", tree.left(tree.root()).getElement().getValue());
        assertEquals("seven", tree.right(tree.root()).getElement().getValue());
        
        tree.put(11, "eleven");
        tree.put(4, "four");
        tree.put(8, "eight");
        
        assertEquals("six", tree.left(tree.right(tree.root())).getElement().getValue());
       
        // You should create test cases to check all the
        // trinode restructuring scenarios. The textbook has visual examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
    	assertTrue(tree.isEmpty());
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        assertEquals("one", tree.get(1));
        
        
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
    	assertTrue(tree.isEmpty());
        tree.put(2, "two");
        tree.put(1, "one");
        tree.put(3, "three");
        tree.put(4, "four");
        assertEquals(4, tree.size());
        assertEquals("one", tree.remove(1));
        assertEquals(3, (int)tree.root().getElement().getKey());
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(4, (int)tree.right(tree.root()).getElement().getKey());

        tree.put(1, "one");
        tree.put(10, "ten");
        tree.put(7, "seven");
        tree.remove(2);

        assertEquals(7, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(10, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(4, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        

        // You should create test cases to check all the
        // trinode restructuring scenarios. The textbook has visual examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());     
    }
}