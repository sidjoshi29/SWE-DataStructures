package edu.ncsu.csc316.dsa.data;

/**
 * A student is comparable and identifiable.
 * Students have a first name, last name, id number, 
 * number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 * @author Siddhant Joshi
 *
 */
public class Student implements Comparable<Student>, Identifiable  {
	/** First name of the student */
	private String first;
	
	/** Last name of the student */
	private String last;
	
	/** id of the student */
	private int id;
	
	/** credit hours of the student */
	private int creditHours;
	
	/** GPA of the student */
	private double gpa;
	
	/** UnityID of the student */
	private String unityID;


	/**
	 * The Student Constructor that helps with the creation of a student object.
	 * 
	 * @param first first name of the student
	 * @param last last name of the student
	 * @param id id of the student
	 * @param creditHours of the student
	 * @param gpa gpa of the student
	 * @param unityID unityID of the student
	 */
	public Student(String first, String last, int id, int creditHours, double gpa, String unityID) {
		setFirst(first);
		setLast(last);
		setId(id);
		setCreditHours(creditHours);
		setUnityID(unityID);
		setGpa(gpa);
	}
	
	
	
	/**
	 * Gets the first name of the student.
	 * @return the first
	 */
	public String getFirst() {
		return first;
	}


	/**
	 * Gets the last name of the student.
	 * @return the last
	 */
	public String getLast() {
		return last;
	}


	/**
	 * Returns the id of the student.
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * Returns the no of credit hours the student has taken up.
	 * @return the creditHours
	 */
	public int getCreditHours() {
		return creditHours;
	}


	/**
	 * Gets the students GPA.
	 * @return the gpa
	 */
	public double getGpa() {
		return gpa;
	}


	/**
	 * Gets students Unity ID.
	 * @return the unityID
	 */
	public String getUnityID() {
		return unityID;
	}


	/**
	 * Sets the first name of student.
	 * @param first the first to set
	 */
	public void setFirst(String first) {
		this.first = first;
	}

	/**
	 * Sets last name of the student.
	 * @param last the last to set
	 */
	public void setLast(String last) {
		this.last = last;
	}


	/**
	 * Set the id of the student.
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Set the credit hours for the student.
	 * @param creditHours the creditHours to set
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}


	/**
	 * Sets the gpa of the student.
	 * @param gpa the gpa to set
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}


	/**
	 * Set the UnityID for the student.
	 * @param unityID the unityID to set
	 */
	public void setUnityID(String unityID) {
		this.unityID = unityID;
	}


	/**
	 * Generates a string representation of the Student object.
	 *
	 * @return A string representation of the Student object.
	 */
	@Override
	public String toString() {
		return "Student [first=" + first + ", last=" + last + ", id=" + id + ", creditHours=" + creditHours + ", gpa="
				+ gpa + ", unityID=" + unityID + "]";
	}

	
	

	/**
	 * Generates a hash code for the Student object.
	 *
	 * @return An integer hash code value for the object.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + creditHours;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		long temp;
		temp = Double.doubleToLongBits(gpa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		result = prime * result + ((unityID == null) ? 0 : unityID.hashCode());
		return result;
	}


	/**
	 * Compares this Student object with another object for equality.
	 *
	 * @param obj The object to compare this Student.
	 * @return true if the given object represents a Student equal to this Student, false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		
		if (id != other.id)
			return false;
		if (last == null) {
			if (other.last != null)
				return false;
		} else if (!last.equals(other.last))
			return false;
		return true;
	}



	/**
	 * Compares this Student with another Student for order.
	 * This method first compares students based on their last names, then first names if the last names are identical,
	 * and finally, ID if both first and last names are identical.
	 *
	 * @param s The Student object to be compared.
	 * @return A negative integer, zero, or a positive integer as this Student is less than, equal to, or greater than the specified Student.
	 */
	public int compareTo(Student s) {
		String studentLastName = this.getLast();
		String studentFirstName = this.getFirst();
		int studentId = this.getId();
		
		String otherLastName = s.getLast();
		String otherFirstName = s.getFirst();
		int otherId = s.getId();
		
		//compare last names first
		
		if(studentLastName.compareTo(otherLastName) > 0) {
			return 1;
		}
		else if(studentLastName.compareTo(otherLastName) < 0) {
			return -1;
		}
		//if they have same last names we compare first names
		else { 
			if(studentFirstName.compareTo(otherFirstName) > 0) {
				return 1;
			}
			else if(studentFirstName.compareTo(otherFirstName) < 0) {
				return -1;
			}
			
			//if they have the same first names then we check for their ID's
			else {
				if(studentId < otherId) {
					return -1; 
				} else if(studentId > otherId) {
					return 1; 
				}
			}
		}
		
		//return 0 if they are same
		return 0;
	}

}
	

	
	
	

