package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for LinkedBinaryTree
 * Checks the expected outputs of the BinaryTree abstract data type behaviors when using
 * a linked data structure to store elements
 *
 * @author Dr. King
 * @author Siddhant Joshi
 *
 */
public class LinkedBinaryTreeTest {

    private LinkedBinaryTree<String> tree;
    private Position<String> one;
    private Position<String> two;
    private Position<String> three;
    private Position<String> four;
    private Position<String> five;
    private Position<String> six;
    private Position<String> seven;
    private Position<String> eight;
    private Position<String> nine;
    private Position<String> ten;

    /**
     * Create a new instance of a linked binary tree before each test case executes
     */       
    @Before
    public void setUp() {
        tree = new LinkedBinaryTree<String>(); 
    }
    
    /**
     * Sample tree to help with testing
     *
     * One
     * -> Two
     *   -> Six
     *   -> Ten
     *     -> Seven
     *     -> Five
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     * 
     * Or, visually:
     *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine    
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        three = tree.addRight(one, "three");
        six = tree.addLeft(two, "six");
        ten = tree.addRight(two, "ten");
        four = tree.addLeft(three, "four");
        seven = tree.addLeft(ten, "seven");
        five = tree.addRight(ten, "five");
        eight = tree.addLeft(four, "eight");
        nine = tree.addRight(four, "nine");
    }
    
    /**
     * Test the output of the set(p,e) behavior
     */     
    @Test
    public void testSet() {
    	createTree();

		assertEquals("one", tree.set(one, "one"));
		assertEquals("one", one.getElement());
		assertEquals("eight", tree.set(eight, "eight"));
		assertEquals("eight", eight.getElement());
    }
    
    /**
     * Test the output of the size() behavior
     */     
    @Test
    public void testSize() {
        assertTrue(tree.isEmpty());
        createTree();
        assertEquals(10, tree.size());
		assertEquals("five", tree.remove(five));
		
		assertEquals(9, tree.size());
		
		
    }
    
    /**
     * Test the output of the numChildren(p) behavior
     */     
    @Test
    public void testNumChildren() {
    	createTree();
		assertEquals(0, tree.numChildren(seven));
		assertEquals(1, tree.numChildren(three));
		assertEquals(2, tree.numChildren(four));

		assertEquals("nine", tree.remove(nine));
		assertEquals(9, tree.size());
		assertEquals(1, tree.numChildren(four));

    }

    /**
     * Test the output of the parent(p) behavior
     */   
    @Test
    public void testParent() {
    	createTree();
		assertNull(tree.parent(one));
		assertEquals(one, tree.parent(two));
		assertEquals(four, tree.parent(eight));
		assertEquals(four, tree.parent(nine));
    }

    /**
     * Test the output of the sibling behavior
     */     
    @Test
    public void testSibling() {
    	createTree();
		assertNull(tree.sibling(one));
		assertEquals(six, tree.sibling(ten));
		assertEquals(ten, tree.sibling(six));
	
    }

    /**
     * Test the output of the isInternal behavior
     */     
    @Test
    public void testIsInternal() {
    	createTree();

		assertFalse(tree.isInternal(eight));
		assertTrue(tree.isInternal(one));
		assertTrue(tree.isInternal(two));
		assertTrue(tree.isInternal(ten));
		assertTrue(tree.isInternal(four));
		
    }

    /**
     * Test the output of the isLeaf behavior
     */     
    @Test
    public void isLeaf() {
    	createTree();
		assertFalse(tree.isLeaf(one));
		assertFalse(tree.isLeaf(ten));
		assertTrue(tree.isLeaf(eight));
		assertTrue(tree.isLeaf(nine));
		
    }

    /**
     * Test the output of the isRoot(p)
     */     
    @Test
    public void isRoot() {
    	createTree();
		
		assertTrue(tree.isRoot(one));
		assertFalse(tree.isRoot(eight));
		tree = new LinkedBinaryTree<String>();
		ten = tree.addRoot("ten");
		assertTrue(tree.isRoot(ten));
    }
    
    /**
     * Test the output of the preOrder traversal behavior
     */     
    @Test
    public void testPreOrder() {

        createTree();
		Iterator<Position<String>> it = tree.preOrder().iterator();
		assertTrue(it.hasNext());
		assertEquals("one", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("two", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("six", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("ten", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("seven", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("five", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("three", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("four", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("eight", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("nine", it.next().getElement());
		assertFalse(it.hasNext());
		assertThrows(NoSuchElementException.class, () -> it.next());
		assertThrows(UnsupportedOperationException.class, () -> it.remove());
    }

    /**
     * Test the output of the postOrder traversal behavior
     */     
    @Test
    public void testPostOrder() {
    	createTree();
		Iterator<Position<String>> it = tree.postOrder().iterator();
		assertTrue(it.hasNext());
		
		assertEquals("six", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("seven", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("five", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("ten", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("two", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("eight", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("nine", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("four", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("three", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("one", it.next().getElement());
		assertFalse(it.hasNext());
		assertThrows(NoSuchElementException.class, () -> it.next());
		assertThrows(UnsupportedOperationException.class, () -> it.remove());
    }
    
    /**
     * Test the output of the inOrder traversal behavior
     */     
    @Test
    public void testInOrder() {
    	createTree();
		Iterator<Position<String>> it = tree.inOrder().iterator();
		assertTrue(it.hasNext());
		
		assertEquals("six", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("two", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("seven", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("ten", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("five", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("one", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("eight", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("four", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("nine", it.next().getElement());
		assertEquals("three", it.next().getElement());
		assertFalse(it.hasNext());
		assertThrows(NoSuchElementException.class, () -> it.next());
		assertThrows(UnsupportedOperationException.class, () -> it.remove());
    }

    /**
     * Test the output of the Binary Tree ADT behaviors on an empty tree
     */     
    @Test
    public void testEmptyTree() {
    	LinkedBinaryTree<Integer> tree1 = new LinkedBinaryTree<>();
    	assertTrue(tree1.isEmpty());
    	assertEquals(0, tree1.size());
    	assertNull(tree1.root());
    }
    
    @Test
    public void testLevelOrder() {
    	createTree();
		Iterator<Position<String>> it = tree.levelOrder().iterator();
		assertTrue(it.hasNext());
		assertEquals("one", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("two", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("three", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("six", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("ten", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("four", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("seven", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("five", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("eight", it.next().getElement());
		assertTrue(it.hasNext());
		assertEquals("nine", it.next().getElement());
    }

    /**
     * Test the output of the addLeft(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddLeft() {
    	one = tree.addRoot("one");
		assertEquals("two",  tree.addLeft(one, "two").getElement());
		assertEquals(2, tree.size());
		assertThrows(IllegalArgumentException.class, () -> tree.addLeft(one, "three"));
    }
    
    /**
     * Test the output of the addRight(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddRight() {
    	one = tree.addRoot("one");
		assertEquals("two",  tree.addRight(one, "two").getElement());
		assertEquals(2, tree.size());
		assertThrows(IllegalArgumentException.class, () -> tree.addRight(one, "three"));
    }   
    
    /**
     * Test the output of the remove(p) behavior, including expected exceptions
     */         
    @Test
    public void testRemove() {
        createTree();
   
		assertEquals("seven", tree.remove(seven));
		assertEquals(9, tree.size());
		assertEquals("ten", tree.remove(ten));
    }
    
    @Test
	public void testToString() {
		createTree();
		assertEquals("LinkedBinaryTree[\n"
				+ "one\n two\n  six\n  ten\n   seven\n   five\n"
				+ " three\n  four\n   eight\n   nine\n]", tree.toString());
		
	}
}