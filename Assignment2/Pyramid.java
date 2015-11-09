/* Caymon Sullivan P.3
 * 
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;

import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	//Ignore this method for now.  It needs to be here to 
	//run the program.  We'll talk about it second semester.
	public static void main(String[] args)
	{
		new Pyramid().start(args);
	}
	
	public void run() 
	{
		int start = (getWidth() / 2) - (BRICK_WIDTH * BRICKS_IN_BASE / 2);
		int bottom = getHeight();
		
		for (int k = 0; k < BRICKS_IN_BASE; k++) 
		{
			bottom = bottom - BRICK_HEIGHT;
			
			start = start + BRICK_WIDTH/2;
			
			for(int i = 0; i < BRICKS_IN_BASE-k; i++) 
			{
				add(new GRect(
						start + (BRICK_WIDTH) * i,
						bottom, 
						BRICK_WIDTH, 
						BRICK_HEIGHT
						));
			}
			
		}
		
		
		
	}
}

