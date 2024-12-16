package edu.ncsu.csc316.dsa.sorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * MergeSorter sorts arrays of comparable elements using the merge sort
 * algorithm. This implementation ensures O(nlogn) worst-case runtime to sort an
 * array of n elements that are comparable.
 * 
 * @author Dr. King
 * @author Siddhant Joshi
 * Citing Help from the Textbooks
 * The code for this method is based on the mergesort algorithm in the course textbook
 * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

    /**
     * Constructs a new MergeSorter with a specified custom Comparator
     * 
     * @param comparator a custom Comparator to use when sorting
     */
    public MergeSorter(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * Constructs a new MergeSorter with comparisons based on the element's natural
     * ordering
     */ 
    public MergeSorter() {
        this(null);
    }

	@Override
	public void sort(E[] data) {
		int n = data.length;
		if(n < 2) {
			return;
		}
		int mid = n / 2;
		//divide the list to two halves
		E[] dataLeft = Arrays.copyOfRange(data, 0, mid);
		E[] dataRight = Arrays.copyOfRange(data, mid, n);
		
		sort(dataLeft);
		sort(dataRight);
		
		merge(dataLeft, dataRight, data);
		
	}
	
	private void merge(E[] s1, E[] s2, E[] s) {
		int left = 0; 
		int right = 0;
		while(left + right < s.length) {
			if(right == s2.length || (left < s1.length && compare(s1[left], s2[right]) < 0)) {
				s[left + right] = s1[left++];
			} else {
				s[left + right] = s2[right++];
			}
		}
	}
    

}