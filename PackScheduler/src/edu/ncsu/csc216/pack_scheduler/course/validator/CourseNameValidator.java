package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Course Name Validator class for validating course names.
 * 
 * This class validates whether a course name is in the correct format.
 * A valid course name must start with 1 to 4 letters, followed by exactly
 * 3 digits, and can optionally end with 1 letter.
 * 
 * @author Siddhant Joshi
 * @author Arnav Ganguly
 * @author Shaurya Deepak
 */
public class CourseNameValidator {

    /** Represents the validity of the current course name state. */
    private boolean validEndState;

    /** The count of letters encountered in the course name. */
    private int letterCount;

    /** The count of digits encountered in the course name. */
    private int digitCount;

    /** The current state of the course name validation. */
    private State currentState;

    /** The initial state for course name validation. */
    private final State initialState = new InitialState();

    /**
     * The state for handling letters in the course name validation.
     */
    private final State letterState = new LetterState();

    /**
     * The state for handling digits in the course name validation.
     */
    private final State numberState = new NumberState();

    /**
     * The state for handling suffix letters in the course name validation.
     */
    private final State suffixState = new SuffixState();

    /**
     * Constructor for CourseNameValidator.
     * Initializes the validator's internal state.
     */
    public CourseNameValidator() {
        this.validEndState = false;
        this.letterCount = 0;
        this.digitCount = 0;
    }

    /**
     * Validate a course name.
     * 
     * @param courseID the course name to validate
     * @return true if the course name is valid, false otherwise
     * @throws InvalidTransitionException if the course name is in an illegal format
     * @throws IllegalArgumentException if the course name is null or empty
     */
    public boolean isValid(String courseID) throws InvalidTransitionException {
    	CourseNameValidator validator = new CourseNameValidator();
    	validator.validEndState = false;
    	validator.letterCount = 0;
    	validator.digitCount = 0;
    	validator.currentState = validator.initialState;

    	if (courseID == null || "".equals(courseID)) {
    	    throw new IllegalArgumentException("Invalid name");
    	}

    	for (char character : courseID.toCharArray()) {
    	    if (Character.isLetter(character)) {
    	        validator.letterCount++;
    	        validator.currentState.onLetter();
    	    } else if (Character.isDigit(character)) {
    	        validator.digitCount++;
    	        validator.currentState.onDigit();
    	    } else {
    	        validator.currentState.onOther();
    	    }
    	}

    	return validator.validEndState;

    }

    /**
     * State class helps validator transfer from different states
     * 
     * @author Siddhant Joshi
     * @author Arnav Ganguly
     * @author Shaurya Deepak
     */
    private abstract class State {
        /**
         * Default constructor for the State class.
         */
        public State(){
            // Empty constructor
        }

        /**
         * Handle the transition when a letter is encountered.
         * 
         * @throws InvalidTransitionException if the transition is not allowed
         */
        public abstract void  onLetter() throws InvalidTransitionException;

        /**
         * Handle the transition when a digit is encountered.
         * 
         * @throws InvalidTransitionException if the transition is not allowed
         */
        public  abstract void  onDigit() throws InvalidTransitionException;

        /**
         * Handle the transition when a character other than a letter or digit is encountered.
         * 
         * @throws InvalidTransitionException if the transition is not allowed
         */
        public void onOther() throws InvalidTransitionException {
            throw new InvalidTransitionException("Course name can only contain letters and digits.");
        }
    }

    /**
     * Initial state of the course name validator. This is the state that the FSM starts at.
     * 
     * @author Siddhant Joshi
     * @author Arnav Ganguly
     * @author Shaurya Deepak
     */
    public class InitialState extends State {

    	/**
    	 * Empty constructor for inital state
    	 */
        private InitialState(){

        }
        
        /**
         * Handle the transition when a letter is encountered.
         */
        public void onLetter(){
            currentState = letterState;
        }

        /**
         * Handle the transition when a digit is encountered.
         * 
         * @throws InvalidTransitionException if the transition is not allowed
         */
        public void onDigit() throws InvalidTransitionException {
            throw new InvalidTransitionException("Course name must start with a letter.");
        }
    }

    /**
     * Letter state of the Course Name Validator which becomes the state when one of the characters in the
     * Course name validator is a letter
     * 
     * @author Siddhant Joshi
     * @author Arnav Ganguly
     * @author Shaurya Deepak
     */
    public class LetterState extends State {

    	/**
    	 * Empty constructor for Letter state
    	 */
        private LetterState(){

        }
        
        /**
         * Handle the transition when a letter is encountered.
         * 
         * @throws InvalidTransitionException if the transition is not allowed
         */
        public void onLetter() throws InvalidTransitionException {
            if(letterCount <= 4){
                currentState = letterState;
            } else {
                throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
            }
        }
        
        /**
         * Handle the transition when a digit is encountered.
         */
        public void onDigit(){
            currentState = numberState;
            letterCount = 0;
        }
    }
    
    /**
     * Number state of the Course Name Validator which becomes the state when one of the characters in the
     * Course name validator is a number
     * 
     * @author Siddhant Joshi
     * @author Arnav Ganguly
     * @author Shaurya Deepak
     */
    public class NumberState extends State {

    	/**
    	 * Empty constructor for number state
    	 */
        private NumberState(){

        }
        
        /**
         * Handle the transition when a letter is encountered.
         * 
         * @throws InvalidTransitionException if the transition is not allowed
         */
        public void onLetter() throws InvalidTransitionException {
            if(digitCount != 3) {
                throw new InvalidTransitionException("Course name must have 3 digits.");
            } else {
                validEndState = true;
                currentState = suffixState;
            }
        }
        
        /**
         * Handle the transition when a digit is encountered.
         * 
         * @throws InvalidTransitionException if the transition is not allowed
         */
        public void onDigit() throws InvalidTransitionException {
            if(digitCount < 3){
                currentState = numberState;
            } else if (digitCount == 3) {
                validEndState = true;
            } else {
                throw new InvalidTransitionException("Course name can only have 3 digits.");
            }
        }
    }
    
    /**
     * Suffix of the Course Name Validator which becomes the state when the course name ends and the suffix starts
     * 
     * @author Siddhant Joshi
     * @author Arnav Ganguly
     * @author Shaurya Deepak
     */
    public class SuffixState extends State {

    	/**
    	 * Empty constructor for suffix state
    	 */
        private SuffixState(){

        }
        
        /**
         * Handle the transition when a letter is encountered.
         * 
         * @throws InvalidTransitionException if the transition is not allowed
         */
        public void onLetter() throws InvalidTransitionException {
            if(letterCount > 1){
                throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");
            } 
        }
        
        /**
         * Handle the transition when a digit is encountered.
         * 
         * @throws InvalidTransitionException if the transition is not allowed
         */
        public void onDigit() throws InvalidTransitionException {
            throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");
        }
    }
}
