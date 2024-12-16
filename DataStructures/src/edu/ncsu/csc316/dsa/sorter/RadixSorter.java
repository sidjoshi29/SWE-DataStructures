package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * RadixSorter uses the radix sort algorithm to sort data
 * @author Dr. King
 * @param <E> the generic type of data to sort
 */
public class RadixSorter<E extends Identifiable> implements Sorter<E> {

	
	public void sort(E[] data) {
		
		
		// find the largest value in the input data
		int k = 0;
		for(int i = 0; i < data.length; i++) {
			int currMax = data[i].getId();
			if(currMax > k) {
				k = currMax;
			}
 		}
		// Determine how many digits are in the largest value
		double x = Math.ceil(Math.log10(k + 1));
		
		//track place
		int p = 1;
		for (int j = 1; j <= x; j++) {
			// Initialize new array B
            int[] bArray = new int[10];
            for (int i = 0; i < data.length; i++) {
                int digit = data[i].getId() / p % 10;
                bArray[digit]++;
            }
            
            for (int i = 1; i < 10; i++) {
            	bArray[i] = bArray[i - 1] + bArray[i];
            }
            
            @SuppressWarnings("unchecked")
			E[] fArray = (E[]) new Identifiable[data.length];
            for (int i = data.length - 1; i >= 0; i--) {
            	fArray[bArray[data[i].getId() / p % 10] - 1] = data[i];
            	bArray[data[i].getId() / p % 10]--;
            }
            
            for (int i = 0; i < data.length; i++) {
                data[i] = fArray[i];
            }

            p = p * 10;
            
		}
	}
	
	
}
