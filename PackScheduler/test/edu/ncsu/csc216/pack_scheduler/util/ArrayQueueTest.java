package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class ArrayQueueTest {

	@Test
	void testArrayQueue() {
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>(5);
		assertTrue(queue.isEmpty());	
	}
	
	@Test
	void testEnqueue() {
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>(3);
		assertTrue(queue.isEmpty());
		
		// Null element
		assertThrows(IllegalArgumentException.class, () -> queue.enqueue(null));
		
		// Adding elements
		queue.enqueue(1);
		assertEquals(1, queue.size());
		
		queue.enqueue(2);
		assertEquals(2, queue.size());
		
		queue.enqueue(3);
		assertEquals(3, queue.size());
		
		// Full Capacity
		assertThrows(IllegalArgumentException.class, () -> queue.enqueue(4));
	}
	
	@Test
	void testDequeue() {
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>(5);
		assertTrue(queue.isEmpty());	
		
		// Popping an empty stack
		assertThrows(NoSuchElementException.class, () -> queue.dequeue());
		
		// adding elements again
		queue.enqueue(1);
		assertEquals(1, queue.size());
		
		queue.enqueue(2);
		assertEquals(2, queue.size());
		
		queue.enqueue(3);
		assertEquals(3, queue.size());
		
		// popping them one by one
		assertEquals(1, queue.dequeue());
		assertEquals(2, queue.size());
		
		assertEquals(2, queue.dequeue());
		assertEquals(1, queue.size());
		
		assertEquals(3, queue.dequeue());
		assertEquals(0, queue.size());
		
		assertTrue(queue.isEmpty());
	}
	
	@Test
	void testSetCapacity() {
		ArrayQueue<Integer> queue = new ArrayQueue<Integer>(5);
		assertTrue(queue.isEmpty());	
		
		queue.enqueue(1);
		assertEquals(1, queue.size());
		
		queue.enqueue(2);
		assertEquals(2, queue.size());
		
		queue.enqueue(3);
		assertEquals(3, queue.size());
		
		assertThrows(IllegalArgumentException.class, () -> queue.setCapacity(-1));
		
		assertThrows(IllegalArgumentException.class, () -> queue.setCapacity(2));
		
		
	}

}
