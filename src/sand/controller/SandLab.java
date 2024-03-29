package sand.controller;

import sand.view.SandDisplay;
import java.awt.*;
import java.util.*;

public class SandLab
{
  //Step 4,6
  //add constants for particle types here
  public static final int EMPTY = 0;
  public static final int METAL = 1;
  public static final int SAND = 2;
  public static final int WATER = 3;
  public static final int POISON = 4;
  public static final int EARTH = 5;
  public static final int CONFETTI = 6;
  
  
  //do not add any more fields below
  private int[][] grid;
  private SandDisplay display;
 
  
  /**
   * Constructor for SandLab
   * @param numRows The number of rows to start with
   * @param numCols The number of columns to start with;
   */
  public SandLab(int numRows, int numCols)
  {
    String[] names;
    // Change this value to add more buttons
    //Step 4,6
    names = new String[7];
    // Each value needs a name for the button
    names[EMPTY] = "Empty";
    names[METAL] = "Metal";
    names[SAND] = "Anakin hates me";
    names[WATER] = "h2o";
    names[POISON] = "Poison!";
    names[EARTH] = "Earth";
    names[CONFETTI] = "Confetti";
    
    //1. Add code to initialize the data member grid with same dimensions
    this.grid = new int [numRows][numCols];
    display = new SandDisplay("falling Water", numRows, numCols, names);
    
    display = new SandDisplay("Falling Sand", numRows, numCols, names);
  }
  
  //called when the user clicks on a location using the given tool
  private void locationClicked(int row, int col, int tool)
  {
    //2. Assign the values associated with the parameters to the grid
    grid[row][col] = tool;
  }

  //copies each element of grid into the display
  public void updateDisplay()
  {
      //Step 3
	  
	  for (int row = 0; row < grid.length; row++)
	  {
		  for (int col = 0; col < grid[0].length; col++)
		  {
			  if (grid[row][col] == EMPTY)
			  {
				  display.setColor(row,  col,  Color.BLACK);
			  }
			  else if (grid[row][col] == METAL)
			  {
				  display.setColor(row, col, Color.GRAY);
			  }
			  else if (grid[row][col] == SAND)
			  {
				  display.setColor(row, col, Color.YELLOW);
			  }
			  else if (grid[row][col] == WATER)
				  
				  display.setColor(row, col, Color.BLUE);
			  else if (grid[row][col] == POISON)
			  {
				  Color poisonColor = new Color(218,112,214);
				  display.setColor(row,  col, poisonColor);
			  }
			  else if (grid[row][col] == EARTH)
			  {
				  Color earthColor = new Color(99, 81, 71);
				  display.setColor(row,  col, earthColor);
			  }
			  else if (grid[row][col] == CONFETTI)
			  {
				  Color confettiColor = new Color (255,182,193);
				  display.setColor(row, col, confettiColor);
			  }
		  }
	  }
   //Hint - use a nested for loop
    
  }

  //Step 5,7
  //called repeatedly.
  //causes one random particle in grid to maybe do something.
  
//  public void collide()
//  {
//	  int orandomRow = (int) (Math.random() * grid.length);
//	  int orandomCol = (int) (Math.random() * grid.length);
//	  
//	  int currentParticle = grid [orandomRow][orandomCol];
//	  if (currentParticle == WATER)
//	  {
//		  if (orandomRow + 1 < grid.length && grid[orandomRow + 1][orandomCol] == EMPTY)
//		  {
//			  if (orandomCol -1 >= 0 && orandomCol - 1 < grid[0].length && grid[orandomRow + 1][orandomCol + 1] == EMPTY)
//			  {
//				  int temp = grid[orandomRow][orandomCol];
//				  grid [orandomRow + 1][orandomCol - 1] = currentParticle;
//				  grid [orandomRow][orandomCol] = temp;
//			  }
//			  //can we go right now make another if 
//			  int temp = grid[orandomRow][orandomCol];
//			  grid [orandomRow + 1][orandomCol] = currentParticle;
//			  grid [orandomRow][orandomCol] = temp;
//		  }
//	  }
//	  
//  }
  
  private void swapParticles(int rowOne, int colOne, int rowTwo, int colTwo)
  {
	 int tempParticle = grid[rowOne][colOne];
	 grid [rowOne][colOne] = grid[rowTwo][colTwo];
	 grid[rowTwo][colTwo] = tempParticle;
  }
  public void step()
  {
    //Remember, you need to access both row and column to specify a spot in the array
    //The scalar refers to how big the value could be
    //int someRandom = (int) (Math.random() * scalar)
	  
	  int randomRow = (int) (Math.random() * grid.length);
	  int randomCol = (int) (Math.random() * grid[0].length);
	  
	  int currentParticle = grid [randomRow][randomCol];
	if (currentParticle == SAND)
	{
		handleSand(randomRow, randomCol);
	}
	else if (currentParticle == WATER)
	{
		handleWater(randomRow, randomCol);
		
	}
	else if (currentParticle == POISON)
	{
		poisonBehavior(randomRow, randomCol);
	}
	else if (currentParticle == EARTH)
	{
		earthBehavior(randomRow, randomCol);
	}
	else if (currentParticle == CONFETTI)
	{
		confettiBehavior(randomRow, randomCol);
	}
    //remember that you need to watch for the edges of the array
    
    
  }
  private void confettiBehavior(int randomRow, int randomCol)
  
  {
	  if (grid[randomRow][randomCol-1] == EMPTY || grid[randomRow - 1][randomCol-1] == WATER)
	  {
		  if (randomCol + 1 < grid[0].length)
			{
				if (grid [randomRow][randomCol + 1] == EMPTY)
				{
					swapParticles(randomRow, randomCol, randomRow + 1, randomCol + 1);
				}
			}
	  }
	  else if (grid[randomRow][randomCol-1] == POISON )
	  {
		  
	  }
	  else
	  {
		  if (randomCol + 1 < grid[0].length)
			{
				if (grid [randomRow][randomCol + 1] == EMPTY)
				{
					swapParticles(randomRow, randomCol, randomRow, randomCol - 1);
				}
			}
	  }
  }
  
  
  private void handleWater(int randomRow, int randomCol)
  {
	  int waterDecision = (int) (Math.random()* 3);
		
		if (waterDecision == 0)
		{
			if (randomRow + 1 < grid.length)
			{
				if (grid[randomRow + 1][randomCol] == EMPTY)
				{
					swapParticles(randomRow, randomCol, randomRow + 1, randomCol);
				}
			}
		}
		else if (waterDecision == 1)
		{
			if (randomCol - 1 >= 0)
			{
				if (grid [randomRow][randomCol - 1] == EMPTY
						) {
				swapParticles(randomRow, randomCol, randomRow, randomCol - 1);
				}
			}
		}
		else
		{
			if (randomCol + 1 < grid[0].length)
			{
				if (grid [randomRow][randomCol + 1] == EMPTY)
				{
					swapParticles(randomRow, randomCol, randomRow, randomCol + 1);
				}
			}
		}
  }
  
  private void poisonBehavior (int currentRow, int currentCol)
  {
	  if (currentRow - 1 >= 0)
	  {
		  if (grid [currentRow - 1][currentCol ] == EMPTY)
		  {
			  swapParticles(currentRow, currentCol, currentRow - 1, currentCol);
		  }
		  
	  }
	  else if (currentCol - 1 >= 0)
	  {
		  int poisonDecision = (int) (Math.random()* 3);
			
			if (poisonDecision == 0)
			{
				if (currentRow + 1 < grid.length)
				{
					if (grid[currentRow + 1][currentCol] == EMPTY)
					{
						swapParticles(currentRow, currentCol, currentRow + 1, currentCol);
					}
				}
			}
			else if (poisonDecision == 1)
			{
				if (currentCol - 1 >= 0)
				{
					if (grid [currentRow][currentCol - 1] == EMPTY
							) {
					swapParticles(currentRow, currentCol, currentRow, currentCol - 1);
					}
				}
			}
			else
			{
				if (currentCol + 1 < grid[0].length)
				{
					if (grid [currentRow][currentCol + 1] == EMPTY)
					{
						swapParticles(currentRow, currentCol, currentRow, currentCol + 1);
					}
				}
			}
	  }
  }
  private void earthBehavior(int currentRow, int currentCol)
  {
	  if (currentRow + 1 <= 0)
	  {
		  if (grid[currentRow - 1][currentCol] == EMPTY || grid[currentRow - 1][currentCol] == WATER)
		  {
			  swapParticles(currentRow, currentCol, currentRow - 1, currentCol);
		  }
	  }
	  else if (currentCol - 1 >= 0)
	  {
		  int earthDecision = (int) (Math.random()* 3);
			
			if (earthDecision == 0)
			{
				if (currentRow + 1 < grid.length && currentCol - 1 >= 0)
				{
					if (grid[currentRow + 1][currentCol - 1] == EMPTY)
					{
						swapParticles(currentRow, currentCol, currentRow+1, currentCol - 1);
					}
				}
			}
			else if (earthDecision == 1)
			{
				if (currentCol - 1 >= 0 && currentRow + 1 < grid.length)
				{
					if (grid [currentRow + 1][currentCol - 1] == EMPTY
							) {
					swapParticles(currentRow, currentCol, currentRow + 1, currentCol - 1);
					}
				}
			}
			else
			{
				if (currentCol + 1 < grid[0].length && currentRow + 1 < grid.length)
				{
					if (grid [currentRow][currentCol + 1] == EMPTY)
					{
						swapParticles(currentRow, currentCol, currentRow + 1, currentCol + 1);
					}
				}
			}
	  }
	  
  }
  private void handleSand(int randomRow, int randomCol)
  {
	  if (randomRow + 1< grid.length)
	  {
		  if (grid[randomRow + 1][randomCol] == EMPTY ||grid[randomRow + 1][randomCol] == WATER)
		  {
			  swapParticles(randomRow, randomCol, randomRow + 1, randomCol);
		  }
	  }
  }
 
  //do not modify this method!
  public void run()
  {
    while (true) // infinite loop
    {
      for (int i = 0; i < display.getSpeed(); i++)
      {
        step();
      }
      updateDisplay();
      display.repaint();
      display.pause(1);  //wait for redrawing and for mouse
      int[] mouseLoc = display.getMouseLocation();
      if (mouseLoc != null)  //test if mouse clicked
      {
        locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
      }
    }
  }
}
