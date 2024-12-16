package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for UnorderedLinkedMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an unordered link-based list data structure that uses the move-to-front heuristic for
 * self-organizing entries based on access frequency
 *
 * @author Dr. King
 * @author Siddhant Joshi
 *
 */
public class UnorderedLinkedMapTest {

    private Map<Integer, String> map;
    
    /**
     * Create a new instance of an unordered link-based map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new UnorderedLinkedMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("UnorderedLinkedMap[3]", map.toString());
        assertEquals(1, map.size());

        assertEquals(1, map.size());
        assertFalse(map.isEmpty());
        assertNull(map.put(1, "string1"));
        assertEquals("UnorderedLinkedMap[1, 3]", map.toString());
        assertEquals(2, map.size());
    }

    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string4", map.get(4));
        assertEquals("UnorderedLinkedMap[4, 1, 2, 5, 3]", map.toString());
        
        assertNull(map.get(100));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string5", map.remove(5));
        assertNull(map.get(5));
        assertEquals("UnorderedLinkedMap[1, 4, 2, 3]", map.toString());
        
        assertNull(map.get(5));
        
        assertEquals("string1", map.remove(1));
        assertNull(map.get(1));
        assertEquals("UnorderedLinkedMap[4, 2, 3]", map.toString());
        
        assertNull(map.get(1));
        
        assertEquals("string4", map.remove(4));
        assertNull(map.get(4));
        assertEquals("UnorderedLinkedMap[2, 3]", map.toString());
        
        assertNull(map.get(4));
        
        assertEquals("string2", map.remove(2));
        assertNull(map.get(2));
        assertEquals("UnorderedLinkedMap[3]", map.toString());
        
        assertNull(map.get(2));
        
        assertEquals("string3", map.remove(3));
        assertNull(map.get(3));
        assertEquals("UnorderedLinkedMap[]", map.toString());
        
        assertNull(map.get(3));
    }

    /**
     * Test the output of the iterator behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));

        Iterator<Integer> it = map.iterator();
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(1), it.next());
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(4), it.next());
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(2), it.next());
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(5), it.next());
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(3), it.next());
        assertFalse(it.hasNext());
        try {
        	it.next();
        	fail();
        } catch(Exception e) {
        	//no elements after
        }
    }

    /**
     * Test the output of the entrySet() behavior, including expected exceptions
     */     
    @Test
    public void testEntrySet() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterable<Entry<Integer, String>> entry = map.entrySet();
        
        Iterator<Entry<Integer, String>> it = entry.iterator();
        assertTrue(it.hasNext());
        assertEquals("string1", it.next().getValue());
        assertTrue(it.hasNext());
        assertEquals("string4", it.next().getValue());
        assertTrue(it.hasNext());
        assertEquals("string2", it.next().getValue());
        assertTrue(it.hasNext());
        assertEquals("string5", it.next().getValue());
        assertTrue(it.hasNext());
        assertEquals("string3", it.next().getValue());
        assertFalse(it.hasNext());
        try {
        	it.next();
        	fail();
        } catch(Exception e) {
        	//no elements after
        }
    }

    /**
     * Test the output of the values() behavior, including expected exceptions
     */     
    @Test
    public void testValues() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterable<String> values = map.values();
        
        Iterator<String> it = values.iterator();
        assertTrue(it.hasNext());
        assertEquals("string1", it.next());
        assertTrue(it.hasNext());
        assertEquals("string4", it.next());
        assertTrue(it.hasNext());
        assertEquals("string2", it.next());
        assertTrue(it.hasNext());
        assertEquals("string5", it.next());
        assertTrue(it.hasNext());
        assertEquals("string3", it.next());
        assertFalse(it.hasNext());
        try {
        	it.next();
        	fail();
        } catch(Exception e) {
        	//no elements after
        }
    }
}