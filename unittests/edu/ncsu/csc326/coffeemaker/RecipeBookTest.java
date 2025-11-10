package edu.ncsu.csc326.coffeemaker;

import junit.framework.TestCase;

public class RecipeBookTest extends TestCase {

	private RecipeBook rb;
	private Recipe r1, r2, r3 , r4;

	/**
	 * Sets up the test fixture. 
	 * (Called before every test case method.) 
	 */ 
	public void setUp() {
		this.rb = new RecipeBook();
		this.r1 = new Recipe();
		this.r2 = new Recipe();
		this.r3 = new Recipe();
		this.r4 = new Recipe();
	}

	/**
	 * Tears down the test fixture. 
	 * (Called after every test case method.) 
	 */ 
	public void tearDown() { 
		this.rb = null;
		this.r1 = null;
		this.r2 = null;
		this.r3 = null;
		this.r4 = null;
	} 

	/**
	 * Testing addRecipes() below
	 */

	public void testAddRecipes() {
		assertTrue(this.rb.addRecipe(this.r1));
		assertTrue(this.rb.addRecipe(this.r2));
		assertTrue(this.rb.addRecipe(this.r3));
		assertTrue(this.rb.addRecipe(this.r4));

		assertFalse(this.rb.addRecipe(this.r1));
		assertFalse(this.rb.addRecipe(new Recipe()));
	}

	/**
	 * Testing deleteRecipe() below
	 */

	public void testDeleteRecipe() {
		this.testAddRecipes();
		this.r1.setName("r1");
		this.r4.setName("r4");
		assertEquals(this.rb.deleteRecipe(0), this.r1.getName());
		assertEquals(this.rb.deleteRecipe(3), this.r4.getName());

		assertTrue(this.rb.deleteRecipe(10) == null);
		assertTrue(this.rb.deleteRecipe(4) == null);
	}
	
	/**
	 * Testing editRecipe() below
	 */
	
	public void testEditRecipe() {
		this.testAddRecipes();
		this.r1.setName("r1");
		this.r4.setName("r4");
		
		Recipe nr1 = new Recipe();
		nr1.setName("nr1");
		Recipe nr4 = new Recipe();
		nr4.setName("nr4");
		assertEquals(this.rb.editRecipe(0, nr1), r1.getName());
		assertEquals(this.rb.editRecipe(3, nr4), r4.getName());
		
		assertTrue(this.rb.editRecipe(10, nr4) == null);
		assertTrue(this.rb.editRecipe(4, nr4) == null);
	}
	
	/**
	 * Testing getRecipes() below
	 */
	
	public void testGetRecipes() {
		this.r1.setName("r1");
		this.r2.setName("r2");
		this.r3.setName("r3");
		this.r4.setName("r4");
		this.testAddRecipes();
		
		for (int i = 0; i <= 3; i ++)
			assertEquals(this.rb.getRecipes()[i].getName(), 
					("r" + (i + 1)));
	}
	
	
	
}
