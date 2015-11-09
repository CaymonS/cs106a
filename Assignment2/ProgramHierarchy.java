/* Caymon Sullivan P.3
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;

import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {
	
	//Constants for the size of the boxes
	private static final int BOX_WIDTH = 120;
	private static final int BOX_HEIGHT = 40;
	
	//Ignore this method for now.  It needs to be here to 
	//run the program.  We'll talk about it second semester.
	public static void main(String[] args){
		new ProgramHierarchy().start(args);
	}
	
	
	public void run()
	{
		
		/*
		 *  Variables
		 */
		//Middle of the  screen
		int middle_Width = getWidth()/2;
		
		//Left Rule of Thirds
		int left_Thirds = getWidth()/3;
		int upper_Thirds = getHeight()/3;
		int lower_Thirds = upper_Thirds + (getHeight()/3);
		
		//right Rule of Thirds
		int right_Thirds = left_Thirds + (getWidth()/3);
		
		
		/*
		 *  "Program"
		 */
		//Make GRect for the "Program" box
		GRect programBox = new GRect(
					middle_Width-(BOX_WIDTH/2), //X Position
					upper_Thirds-(BOX_HEIGHT/2), //Y Position
					BOX_WIDTH, //Width 
					BOX_HEIGHT //Height
					); 
		
		//Make the label for the box
		GLabel programLabel = new GLabel("Program");
		//Move the label to the center of the box
		programLabel.setLocation(
					programBox.getX() + programBox.getWidth()/2 - (programLabel.getWidth()/2), //X Position 
					programBox.getY() + programBox.getHeight()/2 + (programLabel.getHeight()/2)//Y Position YOU HAVE TO ADD BECAUSE THE CANVAS IS IN THE FOURTH QUANDRENT
					);
		add(programBox); //Add the Program box to the canvas
		add(programLabel); //Add the label to the canvas
		
		
		
		/*
		 *  "GraphicsProgram"
		 */
		//Make GRect for the "GraphicsProgram" box
		GRect graphicsProgramBox = new GRect(
					left_Thirds-(BOX_WIDTH/2), //X Position
					lower_Thirds-(BOX_HEIGHT/2), //Y Position
					BOX_WIDTH, //Width
					BOX_HEIGHT //Height
					);
				
		//Make the Label for the box
		GLabel graphicsProgramLabel = new GLabel("GraphicsProgram");
		//Move the label to the center of the box
		graphicsProgramLabel.setLocation(
					graphicsProgramBox.getX() + graphicsProgramBox.getWidth()/2 - (graphicsProgramLabel.getWidth()/2), //X Position 
					graphicsProgramBox.getY() + graphicsProgramBox.getHeight()/2 + (graphicsProgramLabel.getHeight()/2) //Y Position YOU HAVE TO ADD BECAUSE THE CANVAS IS IN THE FOURTH QUANDRENT
					);
		
		add(graphicsProgramBox); //Add the GraphicsProgram box to the canvas
		add(graphicsProgramLabel); //Add the graphicsProgramLabel to the canvas
		
		
		
		/*
		 *  "ConsoleProgram"
		 */
		//Make GRect for the "ConsoleProgram" box
		GRect consoleProgramBox = new GRect(
					middle_Width-(BOX_WIDTH/2), //X Position
					lower_Thirds-(BOX_HEIGHT/2), //Y Position
					BOX_WIDTH, //Width
					BOX_HEIGHT //Height
					);
		
		//Make the label for the box
		GLabel consoleProgramLabel = new GLabel("ConsoleProgram");
		//Move the label to the center of the box
		consoleProgramLabel.setLocation(
					consoleProgramBox.getX() + consoleProgramBox.getWidth()/2 - (consoleProgramLabel.getWidth()/2), //X Position 
					consoleProgramBox.getY() + consoleProgramBox.getHeight()/2 + (consoleProgramLabel.getHeight()/2)//Y Position YOU HAVE TO ADD BECAUSE THE CANVAS IS IN THE FOURTH QUANDRENT
					);
		
		add(consoleProgramBox); //Add the consoleProgramBox to the canvas
		add(consoleProgramLabel); //Add the consoleProgramLabel to the canvas
		
		
		
		/*
		 *  "DialogProgram"
		 */
		//Make the GRect for the "DialogProgram" box 
		GRect dialogProgramBox = new GRect(
					right_Thirds-(BOX_WIDTH/2), //X Position
					lower_Thirds-(BOX_HEIGHT/2), //Y Position
					BOX_WIDTH, //Width
					BOX_HEIGHT //Height
					);
		
		//Make the label for the box
		GLabel dialogProgramLabel = new GLabel("DialogProgram");
		dialogProgramLabel.setLocation(
					dialogProgramBox.getX() + dialogProgramBox.getWidth()/2 - (dialogProgramLabel.getWidth()/2), //X Position 
					dialogProgramBox.getY() + dialogProgramBox.getHeight()/2 + (dialogProgramLabel.getHeight()/2)//Y Position YOU HAVE TO ADD BECAUSE THE CANVAS IS IN THE FOURTH QUANDRENT
					);
		
		add(dialogProgramBox); //Add the dialogProgramBox to the Canvas
		add(dialogProgramLabel); //Add the dialogProgramLabel to the canvas
		
		
		
		/*
		 *  Connection Lines
		 */
		
		//programBox to graphicsProgramBox
		GLine line1 = new GLine(
					programBox.getX() + programBox.getWidth()/2, //Centers on the programBox
					programBox.getY() + programBox.getHeight(), //Gets to the bottom of the programBox
					graphicsProgramBox.getX() + graphicsProgramBox.getWidth()/2, //Centers the end of the line to the middle of the graphicsProgramBox
					graphicsProgramBox.getY() //Extends the line to the graphicsProgramBox Y plane
					);
		add(line1); //Add the line to the canvas
		
		//programBox to consoleProgramBox
		GLine line2 = new GLine(
					programBox.getX() + programBox.getWidth()/2, //Centers on the programBox
					programBox.getY() + programBox.getHeight(), //Gets to the bottom of the programBox
					consoleProgramBox.getX() + consoleProgramBox.getWidth()/2, //Centers the end of the line to the middle of the consoleProgramBox
					consoleProgramBox.getY() //Extends the line to the consoleProgramBox Y plane
					);
		add(line2); //Add the line to the canvas
		
		//programBox to dialogProgramBox
		GLine line3 = new GLine(
					programBox.getX() + programBox.getWidth()/2, //Centers on the programBox
					programBox.getY() + programBox.getHeight(), //Gets to the bottom of the programBox
					dialogProgramBox.getX() + dialogProgramBox.getWidth()/2, //Centers the end of the line to the middle of the dialogProgramBox
					dialogProgramBox.getY() //Extends the line to the dialogProgramBox Y plane
					);
		add(line3); //Add the line to the canvas
		
	} //End run()
	
}

