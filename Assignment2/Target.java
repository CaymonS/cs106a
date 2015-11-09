/* Caymon Sullivan P.3
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;

import java.awt.*;

public class Target extends GraphicsProgram {	

	//Ignore this method for now.  It needs to be here to 
	//run the program.  We'll talk about it second semester.
	public static void main(String[] args)
	{
		new Target().start(args);
	}
	
	public void run() 
	{
		
		//Center the circle in the canvas.
		int height = getHeight()/2;
		int width = getWidth()/2;
		
		//Required sizes for the target
		int large_Size = 144;
		int medium_Size = 96;
		int small_Size = 48;
		
		//Makes the radius or half the circle for centering off of the GObject origin
		int large_Radius = large_Size/2;
		int medium_Radius = medium_Size/2;
		int small_Radius = small_Size/2;
		
		//CircleOne
		GOval circleOne = new GOval(
				width-small_Radius, 
				height-small_Radius, 
				small_Size, 
				small_Size
				);
			
		circleOne.setFillColor(Color.RED);
		circleOne.setFilled(true);
		
		//CircleTwo
		GOval circleTwo = new GOval(
				width-medium_Radius,
				height-medium_Radius, 
				medium_Size, 
				medium_Size
				);
			
		circleTwo.setFillColor(Color.WHITE);
		circleTwo.setFilled(true);
		
		//CircleThree
		GOval circleThree = new GOval(
				width-large_Radius, 
				height-large_Radius, 
				large_Size, 
				large_Size
				);
			
		circleThree.setFillColor(Color.RED);
		circleThree.setFilled(true);
		
		//Finally put the circle on the canvas, in order of biggest to smallest
		add(circleThree);
		add(circleTwo);
		add(circleOne);
		
	} //End run()
}
