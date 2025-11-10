package edu.ncsu.csc326.coffeemaker;

import junit.framework.TestCase;
import edu.ncsu.csc326.coffeemaker.Inventory;
import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;

public class InventoryTest extends TestCase {

	private Inventory inv;
	private Recipe r;

	/**
	 * Sets up the test fixture. 
	 * (Called before every test case method.) 
	 */ 
	public void setUp() {
		this.inv = new Inventory();
		this.r = new Recipe();
		try {
			this.r.setAmtChocolate("10");
			this.r.setAmtCoffee("10");
			this.r.setAmtMilk("10");
			this.r.setAmtSugar("10");
			this.r.setName("Recipe #1");
			this.r.setPrice("20");
		} catch (RecipeException e) {
			// TODO Auto-generated catch block
		}

	}

	/**
	 * Tears down the test fixture. 
	 * (Called after every test case method.) 
	 */ 
	public void tearDown() { 
		this.inv = null;
		this.r = null;
	} 

	/**
	 * Test cases for all of the setters for each of the four 
	 * different ingredients.
	 */
	public void testSetCoffee() {
		this.inv.setCoffee(10);
		assertTrue(this.inv.getCoffee() == 10);
	}

	public void testSetMilk() {
		this.inv.setMilk(10);
		assertTrue(this.inv.getMilk() == 10);
	}

	public void testSetSugar() {
		this.inv.setSugar(10);
		assertTrue(this.inv.getSugar() == 10);
	}

	public void testSetChocolate() {
		this.inv.setChocolate(10);
		assertTrue(this.inv.getChocolate() == 10);
	}


	/**
	 * Test cases for all of the 'adder' methods for all four of
	 * the different ingredients.
	 */
	public void testAddCoffee() {
		try {
			this.inv.addCoffee("5");
			assertTrue(this.inv.getCoffee() == 20);
		} catch (InventoryException e) {
			fail("Inventory Exception should not be thrown");
		}

		try {
			this.inv.addCoffee("NO");
			fail("Expected Inventory Exception to be thrown");
		} catch (InventoryException e) {
			assertEquals("Units of coffee must be a positive integer",
					e.getMessage());
		}
		
		try {
			this.inv.addCoffee("-5");
			fail("Expected Inventory Exception to be thrown");
		} catch (InventoryException e) {
			assertEquals("Units of coffee must be a positive integer",
					e.getMessage());
		}
	}

	public void testAddMilk() {
		try {
			this.inv.addMilk("5");
			assertTrue(this.inv.getMilk() == 20);
		} catch (InventoryException e) {
			fail("Inventory Exception should not be thrown");
		}

		try {
			this.inv.addMilk("NO");
			fail("Expected Inventory Exception to be thrown");
		} catch (InventoryException e) {
			assertEquals("Units of milk must be a positive integer",
					e.getMessage());
		}
		
		try {
			this.inv.addMilk("-5");
			fail("Expected Inventory Exception to be thrown");
		} catch (InventoryException e) {
			assertEquals("Units of milk must be a positive integer",
					e.getMessage());
		}
	}

	public void testAddSugar() {
		try {
			this.inv.addSugar("5");
			assertTrue(this.inv.getSugar() == 20);
		} catch (InventoryException e) {
			fail("Inventory Exception should not be thrown");
		}

		try {
			this.inv.addSugar("NO");
			fail("Expected Inventory Exception to be thrown");
		} catch (InventoryException e) {
			assertEquals("Units of sugar must be a positive integer",
					e.getMessage());
		}
		
		try {
			this.inv.addSugar("-5");
			assertTrue(this.inv.getSugar() == 20);
		} catch (InventoryException e) {
			assertEquals("Units of sugar must be a positive integer",
					e.getMessage());
		}
	}

	public void testAddChocolate() {
		try {
			this.inv.addChocolate("5");
			assertTrue(this.inv.getChocolate() == 20);
		} catch (InventoryException e) {
			fail("Inventory Exception should not be thrown");
		}

		try {
			this.inv.addChocolate("NO");
			fail("Expected Inventory Exception to be thrown");
		} catch (InventoryException e) {
			assertEquals("Units of chocolate must be a positive integer",
					e.getMessage());
		}
		
		try {
			this.inv.addChocolate("-5");
			assertTrue(this.inv.getChocolate() == 20);
		} catch (InventoryException e) {
			assertEquals("Units of chocolate must be a positive integer",
					e.getMessage());
		}
	}

	/**
	 * Testing enoughIngredients, useIngredients, and the toString
	 * methods below
	 */

	public void testEnoughIngredients(){
		assertTrue(this.inv.enoughIngredients(this.r) == true);

		try {
			this.r.setAmtChocolate("100");
			this.r.setAmtCoffee("100");
			this.r.setAmtMilk("100");
			this.r.setAmtSugar("100");
		} catch (RecipeException e) {
		}
		
		assertTrue(!this.inv.enoughIngredients(this.r));

		try {
			this.r.setAmtMilk("10");
			this.r.setAmtSugar("10");
		} catch (RecipeException e) {
			// TODO Auto-generated catch block
		}
		assertTrue(this.inv.enoughIngredients(this.r) == false);

	}

	public void testUseIngredients(){
		boolean t = this.inv.useIngredients(this.r);
		assertTrue(t);
		
		assertFalse(this.inv.getChocolate() > 15);
		assertFalse(this.inv.getCoffee() > 15);
		assertFalse(this.inv.getMilk() > 15);
		assertFalse(this.inv.getSugar() > 15);
		
		t = this.inv.useIngredients(this.r);
		assertFalse(t);
	}
	
}
