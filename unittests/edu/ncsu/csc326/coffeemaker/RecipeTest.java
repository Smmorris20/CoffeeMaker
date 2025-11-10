package edu.ncsu.csc326.coffeemaker;

import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import junit.framework.TestCase;

public class RecipeTest extends TestCase {

	private Recipe r;
	/**
	 * Sets up the test fixture. 
	 * (Called before every test case method.) 
	 */ 
	public void setUp() {
		this.r = new Recipe();
	}

	/**
	 * Tears down the test fixture. 
	 * (Called after every test case method.) 
	 */ 
	public void tearDown() { 
		this.r = null;
	} 

	/**
	 * Testing getters and setters for all ingredients
	 * , price, name and toString() for recipes.
	 */
	
	public void testSetAmtChocolate() {
		try {
			this.r.setAmtChocolate("20");
			assertTrue(this.r.getAmtChocolate() == 20);
		} catch (RecipeException e) {
			fail("Recipe Exception should not be thrown");
		}
		
		try {
			this.r.setAmtChocolate("NO");
			fail("Recipe Exception should be thrown");
		} catch (RecipeException e) {
			assertEquals("Units of chocolate must be a positive integer",
					e.getMessage());
		}
		
		try {
			this.r.setAmtChocolate("-10");
			fail("Recipe Exception should be thrown");
		} catch (RecipeException e) {
			assertEquals("Units of chocolate must be a positive integer",
					e.getMessage());
		}
		
	}
	
	public void testSetAmtCoffee() {
		try {
			this.r.setAmtCoffee("20");
			assertTrue(this.r.getAmtCoffee() == 20);
		} catch (RecipeException e) {
			fail("Recipe Exception should not be thrown");
		}
		
		try {
			this.r.setAmtCoffee("NO");
			fail("Recipe Exception should be thrown");
		} catch (RecipeException e) {
			assertEquals("Units of coffee must be a positive integer",
					e.getMessage());
		}
		
		try {
			this.r.setAmtCoffee("-10");
			fail("Recipe Exception should be thrown");
		} catch (RecipeException e) {
			assertEquals("Units of coffee must be a positive integer",
					e.getMessage());
		}
	}
	
	public void testSetAmtSugar() {
		try {
			this.r.setAmtSugar("20");
			assertTrue(this.r.getAmtSugar() == 20);
		} catch (RecipeException e) {
			fail("Inventory Exception should not be thrown");
		}
		
		try {
			this.r.setAmtSugar("NO");
			fail("Recipe Exception should be thrown");
		} catch (RecipeException e) {
			assertEquals("Units of sugar must be a positive integer",
					e.getMessage());
		}
		
		try {
			this.r.setAmtSugar("-10");
			fail("Recipe Exception should be thrown");
		} catch (RecipeException e) {
			assertEquals("Units of sugar must be a positive integer",
					e.getMessage());
		}
	}
	
	public void testSetAmtMilk() {
		try {
			this.r.setAmtMilk("20");
			assertTrue(this.r.getAmtMilk() == 20);
		} catch (RecipeException e) {
			fail("Recipe Exception should not be thrown");
		}
		
		try {
			this.r.setAmtMilk("NO");
			fail("Recipe Exception should be thrown");
		} catch (RecipeException e) {
			assertEquals("Units of milk must be a positive integer",
					e.getMessage());
		}
		
		try {
			this.r.setAmtMilk("-10");
			fail("Recipe Exception should be thrown");
		} catch (RecipeException e) {
			assertEquals("Units of milk must be a positive integer",
					e.getMessage());
		}
	}
	
	public void testSetPrice() {
		try {
			this.r.setPrice("10");
			assertTrue(this.r.getPrice() == 10);
		} catch (RecipeException e) {
			fail("Recipe Exception should not be thrown");
		}
		
		try {
			this.r.setPrice("NO");
			fail("Recipe Exception should be thrown");
		} catch (RecipeException e) {
			assertEquals("Price must be a positive integer",
					e.getMessage());
		}
		
		try {
			this.r.setPrice("-10");
			fail("Recipe Exception should be thrown");
		} catch (RecipeException e) {
			assertEquals("Price must be a positive integer",
					e.getMessage());
		}
	}
	
	public void testSetName() {
		String s = "Coffee with sugar";
		this.r.setName(s);
		assertEquals(this.r.getName(), s);
		assertEquals(this.r.toString(), s);
		
		s = "Another name";
		this.r.setName(s);
		assertEquals(this.r.getName(), s);
		assertEquals(this.r.toString(), s);
	}
	
	/**
	 * Testing hashCode() and equals() methods below
	 */
	
	public void testHashCode() {
		Recipe r2 = new Recipe();
		r2.setName("r2");
		this.r.setName("r1");
		
		assertFalse(this.r.hashCode() == r2.hashCode());
		
		r2.setName("r1");
		assertTrue(this.r.hashCode() == r2.hashCode());
	}
	
	public void testEquals() {
		Recipe r2 = new Recipe();
		r2.setName("r2");
		
		this.r.setName("r1");
		
		assertTrue(this.r.equals(this.r));
		
		assertFalse(this.r.equals(null));
		assertFalse(this.r.equals(new Inventory()));
		assertFalse(this.r.equals(r2));
		
		this.r = new Recipe();
		r2 = new Recipe();
		assertFalse(this.r.equals(r2));
	}
}
