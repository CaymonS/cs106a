/*	Caymon Sullivan P.3
 * 	Practice world to mess with nested for loops in class
 */

import java.awt.Color;

import acm.graphics.*;
import acm.program.GraphicsProgram;

public class Practice extends GraphicsProgram {
	
	//Constants
	private static final double OVAL_WIDTH = 20;
	private static final double OVAL_HEIGHT = 20;
	private static final double OVAL_OFFSET = 30;
	private static final int REPEAT_VALUE = 10;
	
	public static void main(String[] args)
	{
		new Practice().start(args);
	}
	
	public void run()
	{
		for (int j = 0; j < REPEAT_VALUE; j++)
		{
			for(int i = 0; i < REPEAT_VALUE; i++)
			{			
				//Create a new GOval Object called myCircle
				GOval oval = new GOval(OVAL_OFFSET*i, OVAL_OFFSET*j, OVAL_WIDTH, OVAL_HEIGHT);
				add(oval);
				
				if (j == 0 || j == 1)
				{
					oval.setColor(Color.RED);
					oval.setFilled(true);
					
				} else if (j == 2 || j == 3)
				{
					oval.setColor(Color.GREEN);
					oval.setFilled(true);
				}
			}
		}
		
		add(new GTurtle(getWidth()/2, getHeight()/2));
	}	
}
