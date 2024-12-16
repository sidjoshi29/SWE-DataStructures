package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * CountingSorter uses the counting sort algorithm to sort data
 * @author Dr. King
 *
 * @param <E> the generic type of data to sort
 */
public class CountingSorter<E extends Identifiable> implements Sorter<E> {
    
	
	/**
	 * Sorts an array of Identifiable elements using the Counting Sort algorithm.
	 *
	 * @param data an array of Identifiable elements to be sorted.
	 */
    public void sort(E[] data) {
        // Since we constrained E to be Identifiable,
        // we can now access the .getId() method of E objects
        // from within this sort method
    	
    	
    	
    	// Find the min and the max elements in the input data
    	int min = data[0].getId();
        int max = data[0].getId();
        for (int i = 0; i < data.length; i++) {
            min = Math.min(data[i].getId(), min);
            max = Math.max(data[i].getId(), max);
        }
        
        int k = max - min + 1;
        
        int[] b = new int[k];
        
        
        for (int i = 0; i < data.length; i++) {
        	E element = data[i];
            b[element.getId() - min] = b[element.getId() - min] + 1;
        }
        
        for (int i = 1; i < k; i++) {
            b[i] = b[i - 1] + b[i];
        }
        
        
       //new array F with length n
        @SuppressWarnings("unchecked")
		E[] fArray = (E[]) new Identifiable[data.length];
        for (int i = data.length - 1; i >= 0; i--) {
            E element = data[i];
            int id = element.getId() - min;
            fArray[b[id] - 1] = element;
            b[id] = b[id] - 1;
        }
        
        //need to use a loop in Java to manually copy each element from F into A one-by-one.
        for (int i = 0; i < data.length; i++) {
            data[i] = fArray[i];
        }
    }
    
    
    
}
