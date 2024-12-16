package edu.ncsu.csc216.pack_scheduler.user;

/**
 * This is the user class
 */
public abstract class User {

	/** The student's first name */
	private String firstName;
	/** The student's last name */
	private String lastName;
	/** The student's id name */
	private String id;
	/** The student's email */
	private String email;
	/** The student's password */
	private String password;
	
	/**
	 * Constructor for user
	 * @param firstName first name of user
	 * @param lastName last name of usrr
	 * @param id id of user
	 * @param email email of user
	 * @param password password of user
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
	}

	/**
	 * Will return the student's first name
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the students first name
	 * @param firstName the firstName to set
	 * @throws IllegalArgumentException if firstName is null or an empty String
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || "".equals(firstName)) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * Gets the student's last name
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the student's last name
	 * @param lastName the lastName to set
	 * @throws IllegalArgumentException if lastName is null or an empty String
	 */
	public void setLastName(String lastName) {
		if (lastName == null || "".equals(lastName)) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * Gets the student's ID
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the student's ID
	 * @param id the id to set
	 * @throws IllegalArgumentException if id is null or an empty String
	 */
	protected void setId(String id) {
		if (id == null || "".equals(id)) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * Gets the student's email
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the student's email
	 * @param email the email to set
	 * @throws IllegalArgumentException if id is null or an empty String, if the email doesn't contain the'@' symbol,
	 * if the email doesn't contain the '.' symbol, and if the '.' symbol is before the '@' symbol
	 */
	public void setEmail(String email) {
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
		
		this.email = email;
	}

	/**
	 * Gets the student password
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the student password
	 * @param password the password to set
	 * @throws IllegalArgumentException if password is null or an empty String
	 */
	public void setPassword(String password) {
		if (password == null || "".equals(password)) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = password;
	}
	
	/**
	 * This is the method that converts a user into a unique hashcode
	 * @return result which is the unique hashcode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}
	
	/**
	 * This is the method that compares if two users are equal
	 * @return true if they are and false if they aren't
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
	

}