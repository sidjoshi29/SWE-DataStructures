package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator to compare students based on id number
 * @author Dr. King
 * @author Siddhant Joshi
 * @author Siddhant Joshi
 *
 */
public class StudentIDComparator implements Comparator<Student> {

	/**
	 * Compares students based on id number in ascending order
	 */
	@Override
	public int compare(Student one, Student two) {
		//compare only based on ID in asc order so first > second means its good.
		if(one.getId() > two.getId()) {
			return 1;
		} 
		if(one.getId() < two.getId()) {
			return -1;
		}
		else {
			return 0;
		}
	}

}
