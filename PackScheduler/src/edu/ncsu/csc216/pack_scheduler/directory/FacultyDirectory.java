/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.directory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import edu.ncsu.csc216.pack_scheduler.io.FacultyRecordIO;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Faculty directory class
 * 
 * @author Siddhant Joshi
 * @author Arnav Gangully
 * @author Shuarya Deepak
 */
public class FacultyDirectory {
	/** List of students in the directory */
	private LinkedList<Faculty> facultyDirectory;
	/** Hashing algorithm */
	private static final String HASH_ALGORITHM = "SHA-256";
	
	/**
	 * Constructs a faculty list
	 */
	public FacultyDirectory() {
		newFacultyDirectory();
	}
	
	/**
	 * creates empty faculty list
	 */
	public void newFacultyDirectory() {
		facultyDirectory = new LinkedList<Faculty>();	
	}
	
	/**
	 * Loads the faculty from the file
	 * @param fileName name of file
	 * @throws IllegalArgumentException if the file is unreadable
	 */
	public void loadFacultyFromFile(String fileName) {
		try {
			facultyDirectory = FacultyRecordIO.readFacultyRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file " + fileName);
		}
	}
	
	/**
	 * Adds a faculty member to the directory with the specified information.
	 * Validates input parameters to ensure they meet certain criteria before adding to the directory.
	 *
	 * @param firstName      the first name of the faculty member
	 * @param lastName       the last name of the faculty member
	 * @param id             the ID of the faculty member (must be unique)
	 * @param email          the email address of the faculty member (must be valid)
	 * @param password       the password for the faculty member (cannot be null or empty)
	 * @param repeatPassword the repeated password for confirmation
	 * @param maxCourses     the maximum number of courses the faculty member can teach (between 1 and 3)
	 * @return true if the faculty member is successfully added to the directory, false otherwise
	 * @throws IllegalArgumentException if any of the input parameters are invalid, such as null or empty values,
	 *                                  mismatched passwords, duplicate faculty ID, invalid email format,
	 *                                  or an invalid range for the maximum number of courses
	 */
	public boolean addFaculty(String firstName, String lastName, String id, String email, String password,
			String repeatPassword, int maxCourses) {
		if (password == null || password.length() == 0) {
			throw new IllegalArgumentException("Invalid password");
		}
		else if (id == null || id.length() == 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		else if (!password.equals(repeatPassword)) {
			throw new IllegalArgumentException("Passwords do not match");
		}
		else {
			for (int i = 0; i < facultyDirectory.size(); i++) {
				if (facultyDirectory.get(i).getId().equals(id)) {
					throw new IllegalArgumentException("Faculty already in system");
				}
			}
			if (firstName == null || firstName.length() == 0) {
				throw new IllegalArgumentException("Invalid first name");
			}
			else if (lastName == null || lastName.length() == 0) {
				throw new IllegalArgumentException("Invalid last name");
			}
			else if (email == null || email.length() == 0) {
				throw new IllegalArgumentException("Invalid email");
			}
			else if (maxCourses < 1 || maxCourses > 3) {
				throw new IllegalArgumentException("Invalid max courses");
			}
			else {
				// Check if empty or null
				if (email == null || "".equals(email)) {
					throw new IllegalArgumentException("Invalid email");
				}
				
				// Check for '@' symbol
				if (!email.contains("@")) {
					throw new IllegalArgumentException("Invalid email");
				}
				
				// Check for '.' symbol
				if (!email.contains(".")) {
					throw new IllegalArgumentException("Invalid email");
				}
				
				// Check if the "." is before the "@"
				int lastPeriodIndex = 0;
				for (int i = 0; i < email.length(); i++) {
					if ('.' == email.charAt(i)) {
						lastPeriodIndex = i;
					}
				}
				int atIndex = email.indexOf("@");
				
				if (lastPeriodIndex < atIndex) {
					throw new IllegalArgumentException("Invalid email");
				}
				
				facultyDirectory.add(new Faculty(firstName, lastName, id, email, hashString(password), maxCourses));
				return true;
			}
				
		}
		
	}
	
	/**
	 * Removes a faculty
	 * @param id of faculty
	 * @return true if can be removed from the list
	 */
	public boolean removeFaculty(String id) {
		for (int i = 0; i < facultyDirectory.size(); i++) {
			Faculty s = facultyDirectory.get(i);
			if (s.getId().equals(id)) {
				facultyDirectory.remove(i);
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets a 2D array of faculty in directory
	 * @return directory 2D array of faculty first name, last name, and id
	 */
	public String[][] getFacultyDirectory() {
		String[][] directory = new String[facultyDirectory.size()][3];
		if (directory.length != 0) {
			for (int i = 0; i < facultyDirectory.size(); i++) {
				Faculty f = facultyDirectory.get(i);
				directory[i][0] = f.getFirstName();
				directory[i][1] = f.getLastName();
				directory[i][2] = f.getId();
			}
		}
		return directory;
	}
	
	/**
	 * Saves the current directory to a file
	 * @param fileName name of file
	 * @throws IllegalArgumentException if the file couldn't be written
	 */
	public void saveFacultyDirectory(String fileName) {
		try {
			FacultyRecordIO.writeFacultyRecords(fileName, facultyDirectory);
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to write to file " + fileName);
		}
		
	}
	
	/**
	 * Gets faculty using id
	 * @param id Faculty id
	 * @return the faculty
	 * @throws IllegalArgumentException if the faculty is null when it is extracted from the directory
	 */
	public Faculty getFacultyById(String id) {
		for(int i = 0; i < facultyDirectory.size(); i++){
			if(facultyDirectory.get(i).getId().equals(id)){
				if(facultyDirectory.get(i) == null){
					throw new IllegalArgumentException();
				} else {
					return facultyDirectory.get(i);
				}
			}
		}
		return null;
		
	}
	
	/**
	 * Hashes a String according to the SHA-256 algorithm, and outputs the digest in base64 encoding.
	 * This allows the encoded digest to be safely copied, as it only uses [a-zA-Z0-9+/=].
	 * 
	 * @param toHash the String to hash 
	 * @return the encoded digest of the hash algorithm in base64
	 * @throws IllegalArgumentException if the password cannot be hashed
	 */
	private static String hashString(String toHash) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(toHash.getBytes());
			return Base64.getEncoder().encodeToString(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}
}
