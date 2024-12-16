package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * The `RegistrationManager` class manages course registration and user authentication in a university system.
 * It provides functionality to log in as a student or registrar, manage course catalogs and student directories, and handle password hashing.
 * The class follows the Singleton pattern to ensure only one instance of `RegistrationManager` exists.
 */
public class RegistrationManager {
	/** Instance of Registration Manager */
    private static RegistrationManager instance;
    /** Course catalog initiated */
    private CourseCatalog courseCatalog;
    /** Student directory */
    private StudentDirectory studentDirectory;
    /** Faculty Directory */
    private FacultyDirectory facultyDirectory;
    /** User called registrar */
    private User registrar;
    /** Current user  */
    private User currentUser;

    /** Hashing algorithm used for password encryption */
    private static final String HASH_ALGORITHM = "SHA-256";
    /** Properties of the file */
    private static final String PROP_FILE = "registrar.properties";

    /**
     * Private constructor for `RegistrationManager`. It initializes the instance and creates the registrar.
     */
    private RegistrationManager() {
        createRegistrar();
        this.courseCatalog = new CourseCatalog();
        this.studentDirectory = new StudentDirectory();
        this.facultyDirectory = new FacultyDirectory();
    }

    /**
     * Creates the registrar by reading properties from a file and hashing the password.
     * @throws IllegalArgumentException if the the prop file cannot be found
     */
    private void createRegistrar() {
        Properties prop = new Properties();

        try (InputStream input = new FileInputStream(PROP_FILE)) {
            prop.load(input);

            String hashPW = hashPW(prop.getProperty("pw"));

            registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"), prop.getProperty("email"), hashPW);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot create registrar.");
        }
    }

    /**
     * Hashes a given password using the specified algorithm.
     *
     * @param pw The password to be hashed
     * @return The hashed password
     * @throws IllegalArgumentException if the hashing algorithm is not available
     */
    private String hashPW(String pw) {
        try {
            MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
            digest1.update(pw.getBytes());
            return Base64.getEncoder().encodeToString(digest1.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Cannot hash password");
        }
    }

    /**
     * Gets the instance of the `RegistrationManager` (Singleton pattern).
     *
     * @return The `RegistrationManager` instance
     */
    public static RegistrationManager getInstance() {
        if (instance == null) {
            instance = new RegistrationManager();
        }
        return instance;
    }

    /**
     * Gets the course catalog.
     *
     * @return The course catalog
     */
    public CourseCatalog getCourseCatalog() {
        return courseCatalog;
    }

    /**
     * Gets the student directory.
     *
     * @return The student directory
     */
    public StudentDirectory getStudentDirectory() {
        return studentDirectory;
    }

    /**
     * Logs in a user with the given ID and password. It checks if the user is a student or the registrar and verifies the password.
     *
     * @param id       The user's ID
     * @param password The user's password
     * @throws IllegalArgumentException if the user isn't the registrar or the student
     * @return `true` if the login is successful, `false` otherwise
     */
    public boolean login(String id, String password) {
    	if (currentUser != null && !currentUser.getId().equals(registrar.getId())) {
            // If someone is already logged in, return false
            return false;
        }
    	
    	if (registrar.getId().equals(id)) {
            // Check if the user is the registrar
            if (registrar.getPassword().equals(hashPW(password))) {
                currentUser = registrar;
                return true; // Successful login
            }
            else {
            	return false;
            }
        } 
        else if (studentDirectory.getStudentById(id) != null) {
            
            Student student = instance.getStudentDirectory().getStudentById(id);
            if (student.getPassword().equals(hashPW(password))) {
                currentUser = student;
                return true; 
            } else {
                return false;
            }
        } 
        else if (facultyDirectory.getFacultyById(id) != null) {
        	Faculty faculty = instance.getFacultyDirectory().getFacultyById(id);
            if (faculty.getPassword().equals(hashPW(password))) {
                currentUser = faculty;
                return true;
            } else {
                return false;
            }
        }
        else {
            throw new IllegalArgumentException("User doesn't exist.");
        }
//        else{
//            // Check if the user is a student
//            Student student = studentDirectory.getStudentById(id);
//            if (student != null && student.getPassword() == (hashPW(password))) {
//                currentUser = student;
//                return true; // Successful login
//            } else {
//                // User exists, but the password is incorrect
//                return false;
//            }
//        }

        
//        else {
//            throw new IllegalArgumentException("User doesn't exist.");
//        }
    }


    /**
     * Logs out the currently logged-in user by setting `currentUser` to the registrar.
     */
    public void logout() {
        currentUser = null;
    }

    /**
     * Gets the currently logged-in user.
     *
     * @return The currently logged-in user
     */
    public User getCurrentUser() {
        return currentUser;
    }
    
    /**
     * Returns the Faculty Directory
     * @return the faculty directory of this manager
     */
    public FacultyDirectory getFacultyDirectory() {
    	return this.facultyDirectory;
    }

    /**
     * Clears data by creating new course catalogs and student directories.
     */
    public void clearData() {
        courseCatalog.newCourseCatalog();
        studentDirectory.newStudentDirectory();
        facultyDirectory.newFacultyDirectory();
    }
    
    /**
     * This is the method that adds a faculty to the course
     * @param course which the faculty needs to be added to
     * @param faculty which will be added to the course
     * @return true if the faculty was successfully added to the course
     * @throws IllegalArgumentException if the user isnt a registrar
     */
    public boolean addFacultyToCourse(Course course, Faculty faculty) {
    	if (currentUser != null && currentUser.getId().equals(registrar.getId())) {
    		faculty.getSchedule().addCourseToSchedule(course);
    		course.setInstructorId(faculty.getId());
    		return true;
    	}
    	else {
    		throw new IllegalArgumentException();
    	}
    }
    
    /**
     * This is the method that removes a faculty from a course
     * @param course which the faculty needs to be removed from
     * @param faculty which will be removed from the course
     * @return true if the course was removed from the schedule
     * @throws IllegalArgumentException if the user isnt a registrar
     */
    public boolean removeFacultyFromCourse(Course course, Faculty faculty) {
    	if (currentUser != null && currentUser.getId().equals(registrar.getId())) {
    		faculty.getSchedule().removeCourseFromSchedule(course);
    		course.setInstructorId(null);
    		return true;
    	}
    	else {
    		throw new IllegalArgumentException();
    	}
    }
    
    /**
     * This is the method that resets the faculties schedule
     * @param faculty who's schedule will be reset
     * @return true if the schedule was successfully reset
     * @throws IllegalArgumentException if the user isnt a registrar
     */
    public boolean resetFacultySchedule(Faculty faculty) {
    	if (currentUser != null && currentUser.getId().equals(registrar.getId())) {
    		faculty.getSchedule().resetSchedule();
    		for (int i = 0; i < faculty.getSchedule().getNumScheduledCourses(); i++) {
    			String name = faculty.getSchedule().getScheduledCourses()[i][0];
    			String section = faculty.getSchedule().getScheduledCourses()[i][1];
    			Course course = courseCatalog.getCourseFromCatalog(name, section);
    			this.removeFacultyFromCourse(course, faculty);
    		}
    		return true;
    	} else {
    		throw new IllegalArgumentException();
    	}
    }

    /**
     * An inner class `Registrar` that represents a registrar user. It inherits from the `User` class.
     */
    private static class Registrar extends User {
        /**
         * Creates a registrar user.
         *
         * @param firstName The first name of the registrar
         * @param lastName  The last name of the registrar
         * @param id        The ID of the registrar
         * @param email     The email address of the registrar
         * @param hashPW    The hashed password of the registrar
         */
        public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
            super(firstName, lastName, id, email, hashPW);
        }
    }
    
    /**
     * Returns true if the logged in student can enroll in the given course.
     * @param c Course to enroll in
     * @return true if the student is successfully enrolled
     * @throws IllegalArgumentException if the currentUser is not a student
     */
    public boolean enrollStudentInCourse(Course c) {
        if (!(currentUser instanceof Student)) {
            throw new IllegalArgumentException("Illegal Action");
        }
        try {
            Student s = (Student)currentUser;
            Schedule schedule = s.getSchedule();
            CourseRoll roll = c.getCourseRoll();
            
            if (s.canAdd(c) && roll.canEnroll(s)) {
                schedule.addCourseToSchedule(c);
                roll.enroll(s);
                return true;
            }
            
        } catch (IllegalArgumentException e) {
            return false;
        }
        return false;
    }

    /**
     * Returns true if the logged in student can drop the given course.
     * @param c Course to drop
     * @return true if dropped
     * @throws IllegalArgumentException if the currentUser is not a student
     */
    public boolean dropStudentFromCourse(Course c) {
        if (!(currentUser instanceof Student)) {
            throw new IllegalArgumentException("Illegal Action");
        }
        try {
            Student s = (Student)currentUser;
            c.getCourseRoll().drop(s);
            return s.getSchedule().removeCourseFromSchedule(c);
        } catch (IllegalArgumentException e) {
            return false; 
        }
    }

    /**
     * Resets the logged in student's schedule by dropping them
     * from every course and then resetting the schedule.
     * @throws IllegalArgumentException if the currentUser is not a student
     */
    public void resetSchedule() {
        if (!(currentUser instanceof Student)) {
            throw new IllegalArgumentException("Illegal Action");
        }
        try {
            Student s = (Student)currentUser;
            Schedule schedule = s.getSchedule();
            String [][] scheduleArray = schedule.getScheduledCourses();
            for (int i = 0; i < scheduleArray.length; i++) {
                Course c = courseCatalog.getCourseFromCatalog(scheduleArray[i][0], scheduleArray[i][1]);
                c.getCourseRoll().drop(s);
            }
            schedule.resetSchedule();
        } catch (IllegalArgumentException e) {
            //do nothing 
        }
    }
}
