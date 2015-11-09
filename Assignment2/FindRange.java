/* Caymon Sullivan P.3
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import java.util.Arrays;

import acm.program.*;

public class FindRange extends ConsoleProgram {
	
	//Constants
	private static final int SENTINEL = 0;
	
	//Ignore this method for now.  It needs to be here to 
	//run the program.  We'll talk about it second semester.
	public static void main(String[] args){
		new FindRange().start(args);
	}
	
	public void run() 
	{
		double maxValue = 0;
		double minValue = Double.POSITIVE_INFINITY; //Make the min literally as big as possible so it will be changes
		boolean go = true;
		double input = 0;
		
		println ("This program finds the largest and smallest numbers");
		
		while (go == true)
		{
			input = readDouble("? "); //ReadDouble can take Doubles as well as Integer inputs
			
			if (input == SENTINEL)
			{
				go = false; //Break out of the loop
				
			} else {
				
				if (input > maxValue)
				{
					maxValue = input;
					
				} 
				if (input < minValue && input != SENTINEL)
				{
					minValue = input;
				}
				
			}//End of else statement
		} //End of loop
		
		//If the max has not change and is still zero, then the input was zero because it would break out of the loop
		if (maxValue == SENTINEL)
		{
			print("You didn't type any numbers...");
			
		} else {
			//If the minValue IS NOT a double, AKA the decimal is ZERO, then print it as a integer
			if ((minValue == Math.floor(minValue)) && !Double.isInfinite(minValue)) 
			{
				println("Smallest: " + (int)minValue); //Display as an int by casting int to the variable
				
			} else {
				//If it is indeed a double, then print it as a double
				println("Smallest: " + minValue);
			}
			
			//If the maxValue IS NOT a double, AKA the decimal is ZERO, then print it as a integer
			if ((maxValue == Math.floor(maxValue)) && !Double.isInfinite(maxValue)) 
			{
				println("Largest: " + (int)maxValue); //Display as an int by casting int to the variable
				
			} else {
				//If is is indeed a double, then print it as a double
				println("Largest: " + maxValue);
			}
			
		} //End of else statement
	} //End of run()
}

