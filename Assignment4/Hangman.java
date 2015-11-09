/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

	//Ignore this method for now.  It needs to be here to 
	//run the program.  We'll talk about it second semester.
	public static void main(String[] args){
		new Hangman().start(args);
	}
	
    public void run() {
    	String word = getWord();
    	int wordLength = getStringLength(word);
		println("Welcome to Hangman!");
		println("Your word now looks like this: " + wordLength);
		
		
	}
    
    private String getWord()
    {
    	return null;
    }
    
    private int getStringLength(String word)
    {
    	String str = word;
    	
    	return (Integer) null;
    }

}
