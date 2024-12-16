package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EmptyStackException;

import org.junit.jupiter.api.Test;

class ArrayStackTest {

	@Test
	void testArrayStack() {
		ArrayStack<Integer> stack = new ArrayStack<Integer>(5);
		assertTrue(stack.isEmpty());	
	}
	
	@Test
	void testPush() {
		ArrayStack<Integer> stack = new ArrayStack<Integer>(3);
		assertTrue(stack.isEmpty());
		
		// Null element
		assertThrows(IllegalArgumentException.class, () -> stack.push(null));
		
		// Adding elements
		stack.push(1);
		assertEquals(1, stack.size());
		
		stack.push(2);
		assertEquals(2, stack.size());
		
		stack.push(3);
		assertEquals(3, stack.size());
		
		// Full Capacity
		assertThrows(IllegalArgumentException.class, () -> stack.push(4));
	}
	
	@Test
	void testPop() {
		ArrayStack<Integer> stack = new ArrayStack<Integer>(3);
		assertTrue(stack.isEmpty());
		
		// Popping an empty stack
		assertThrows(EmptyStackException.class, () -> stack.pop());
		
		// adding elements again
		stack.push(1);
		assertEquals(1, stack.size());
		
		stack.push(2);
		assertEquals(2, stack.size());
		
		stack.push(3);
		assertEquals(3, stack.size());
		
		// popping them one by one
		assertEquals(3, stack.pop());
		assertEquals(2, stack.size());
		
		assertEquals(2, stack.pop());
		assertEquals(1, stack.size());
		
		assertEquals(1, stack.pop());
		assertEquals(0, stack.size());
		assertTrue(stack.isEmpty());
	}
	
	@Test
	void testSetCapacity() {
		ArrayStack<Integer> stack = new ArrayStack<Integer>(5);
		assertTrue(stack.isEmpty());
		
		stack.push(1);
		assertEquals(1, stack.size());
		
		stack.push(2);
		assertEquals(2, stack.size());
		
		stack.push(3);
		assertEquals(3, stack.size());
		
		assertThrows(IllegalArgumentException.class, () -> stack.setCapacity(-1));
		
		assertThrows(IllegalArgumentException.class, () -> stack.setCapacity(2));
		
		
	}

}
