package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** 
 * Test for Course Name Validator
 * 
 * @author Siddhant Joshi
 * @author Arnav Ganguly
 * @author Shaurya Deepak
 * 
 */
public class CourseNameValidatorTest {
	@Test
	void testisValidException() {
		CourseNameValidator courseNameValidator = new CourseNameValidator();
		Exception e = assertThrows(InvalidTransitionException.class, () -> courseNameValidator.isValid("!"));
		assertEquals("Course name can only contain letters and digits.", e.getMessage());
	}
	
	@Test
	void testisValidValid() throws InvalidTransitionException {
		CourseNameValidator courseNameValidator = new CourseNameValidator();
		// 4 letters without extra suffix letter
		assertTrue(courseNameValidator.isValid("llll123"));
		// 4 letters with extra suffix letter
		assertTrue(courseNameValidator.isValid("llll123l"));
		// 3 letters without extra suffix letter
		assertTrue(courseNameValidator.isValid("lll123"));
		// 3 letters with extra suffix letter
		assertTrue(courseNameValidator.isValid("lll123l"));
		// 2 letters without suffix 
		assertTrue(courseNameValidator.isValid("ll123"));
		// 2 letters with suffix
		assertTrue(courseNameValidator.isValid("ll123l"));
		// 1 letter without suffix
		assertTrue(courseNameValidator.isValid("l123"));
		// 1 letter with suffix
		assertTrue(courseNameValidator.isValid("l123l"));
		
		assertTrue(courseNameValidator.isValid("lll123l"));
		
	}
	
	@Test
	void testisValidInvalid() throws InvalidTransitionException {
		
		CourseNameValidator courseNameValidator = new CourseNameValidator();
		Exception e = assertThrows(InvalidTransitionException.class, () -> courseNameValidator.isValid("1"));
		assertEquals("Course name must start with a letter.", e.getMessage());
		
		Exception e1 = assertThrows(InvalidTransitionException.class, () -> courseNameValidator.isValid("lllll123"));
		assertEquals("Course name cannot start with more than 4 letters.", e1.getMessage());
		
		Exception e2 = assertThrows(InvalidTransitionException.class, () -> courseNameValidator.isValid("lll1l"));
		assertEquals("Course name must have 3 digits.", e2.getMessage());
		
		Exception e3 = assertThrows(InvalidTransitionException.class, () -> courseNameValidator.isValid("lll11l"));
		assertEquals("Course name must have 3 digits.", e3.getMessage());
		
		Exception e4 = assertThrows(InvalidTransitionException.class, () -> courseNameValidator.isValid("lll1111"));
		assertEquals("Course name can only have 3 digits.", e4.getMessage());
		
		Exception e5 = assertThrows(InvalidTransitionException.class, () -> courseNameValidator.isValid("lll111ll"));
		assertEquals("Course name can only have a 1 letter suffix.", e5.getMessage());
		
		Exception e6 = assertThrows(InvalidTransitionException.class, () -> courseNameValidator.isValid("lll111l1"));
		assertEquals("Course name cannot contain digits after the suffix.", e6.getMessage());
	}
}
