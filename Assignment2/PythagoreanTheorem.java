/* Caymon Sullivan P.3
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	
	//Ignore this method for now.  It needs to be here to 
	//run the program.  We'll talk about it second semester.
	public static void main(String[] args){
		new PythagoreanTheorem().start(args);
	}
	
	public void run() 
	{
		//Ask the user for the A & B for the Pythagorean Theorem 
		double a = readDouble("A =");
		double b = readDouble("B =");
		
		//Square a and b 
		double a2 = Math.pow(a, 2);
		double b2 = Math.pow(b, 2);
		
		//Add a2 and b2, then square root the value
		double c = Math.sqrt(a2 + b2);
		
		//Print the answer to the console
		println("C = " + c);
	}
}
