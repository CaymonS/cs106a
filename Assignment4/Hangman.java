/**
 * @author Caymon Sullivan
 * @version 2.0 
 */
/* Java Library Imports */
import java.io.*;

/* ACM Library Imports */
import acm.program.*;
import acm.util.*;

public class Hangman extends ConsoleProgram 
{
	/** Get a new instance of the RandomGenerator class*/
    private RandomGenerator rgen = RandomGenerator.getInstance();
    
    /** The lexicon file*/
    private static String lexiconFile = "ShorterLexicon.txt";
    
    /** The minimum amount of lines in the HangmanLexicon.txt file, should always be 1*/
    private static final int MIN_NUM_LINES = 1;
    
    /** The maximum amount of lines in the HangmanLexicon.txt file*/
    private static final int MAX_NUM_LINES = linesInLexicon();
    
    /** The line that the word generated is on*/
    private int wordLine = rgen.nextInt(MIN_NUM_LINES, MAX_NUM_LINES);
    
    /** Boolean to run the game, default is true */
    private boolean go = true;    
    
    /** Boolean to determine if the program is running in console mode, or not. Default is true, set false in the canvas class*/
    private static boolean consoleMode = true;
    
    /** Get a new word from the lexicon. Given to us as a string*/ 
    private String secretWord = "";
    /** Declare the hiddenWord string. Used to display to the user, is the only String that should ever be manipulated*/
    private String hiddenWord = "";   
    
    //Ignore this method for now.  It needs to be here to 
    //run the program.  We'll talk about it second semester.
    public static void main(String[] args)
    {
    	new Hangman().start(args);
    }
    
    /**
     * Initial setup for game mechanics, and starts the game
     * @see #play()
     * @since Version 2.0
     */
	public void run()
    {
    	if (consoleMode == true) 
        {
            System.out.println("Welcome to Hangman!");
        }

    	genWord();
    	hideWord();
    	play();
    }
    
    /**
     * Takes in a string, and returns a "hidden" version to the hiddenWord string
     * @param str A string you want to hide
     */
    private void hideWord()
    {  	
    	for (int i = 0; i < secretWord.length(); i++) 
    	{
    		hiddenWord += "-";
    	}
    }
	
	/**
	 * Finds a random word in the HanmanLexicon.txt file and sets it to the secretWord String
	 * {@value #MIN_NUM_LINES} The minimum amount of lines in the Lexicon (Must be set manually)
	 * {@value #MAX_NUM_LINES} The maximum amount of lines in the lexicon (Must be set manually)
	 * @return Returns the new secretWord String
	 */
	private void genWord()
	{
		try 
    	{
			FileReader file = new FileReader(lexiconFile);
			BufferedReader br = new BufferedReader(file);
			
			//This reads all the lines up to the randomly generated number, but doesn't actually do anything with those lines.
			for (int i = 0; i < wordLine - 1; i++) 
			{
				br.readLine();
			}
			
    		secretWord = br.readLine(); //Finally sets the line that we want to the secretWord String.
    		br.close(); //Close to BufferedReader to save memory leaks!
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}
    
   /**
    * Main game method, runs and ends the game.
    * {@value #go} go 
    * {@value #consoleMode} consoleMode
    */
    private void play()
    {
    	while (go == true)
    	{
    		if (consoleMode == true) 
    		{
    			System.out.println("Your word is: " + hiddenWord + " (Characters: " + secretWord.length() + ")"); //Display the hiddenWord for the user, and the amount of characters in the string.
    			System.out.println("[DEBUG] (secretWord): " + secretWord);
    			System.out.println("[DEBUG] (hiddenWord): " + hiddenWord);
    			System.out.println("[DEBUG] (secretWord RGEN line number): " + wordLine);
    			
    			//Ask the user for their guess as to what a character could be.
    			//Get the first character the user inputs. TODO BROKEN: If the user presses enter, or has a space before their character, Java has an error.
    			//Right away make the character upper case for ease.
    			String input = readLine("Guess a letter: ");
    			char letter = input.charAt(0);
    			letter = Character.toUpperCase(letter);
    			
    			if (checkGuess(letter) == true) 
    			{
    				//Check if the user won!
        			if (hiddenWord.equalsIgnoreCase(secretWord))
        			{
        				if (consoleMode == true) 
                        {
                            System.out.println("Good job you won!");
        				}
                        setGo(false);
        				break;
        			}
    				
        			if (consoleMode == true) 
                    {
                        System.out.println("\n Correct! \n Guess again... \n"); //If the user is correct. Automatically asks for another guess because of the while loop.
                    }
                } else {
    				if (consoleMode == true) 
                    {
                        System.out.println("\n Incorrect :( \n Try again.. \n"); //If the user is incorrect. Automatically asks for another guess because of the while loop.
                    }
                }
    		}
    	}
    }
    
    /**
     * Checks whether the input is in the secretWord string, and changes the hiddenWord to now display that character.
     * @param character The character that the user inputs from the play() method.
     * @return True if the character the user inputed was in the String, false if the character was not in the secretWord.
     * @since Version 1.0
     */
    public boolean checkGuess(char character)
    {
    	char guess = character;
    	guess = Character.toUpperCase(character);
    	
    	boolean changed = false; //Boolean is used to see if we changed anything in the string.
    	
    	//For the length of out secretWord, we check each and every character to see if it matches the guess of the user.
    	for (int i = 0; i <= secretWord.length() - 1; i++)
    	{
    		if (guess == secretWord.charAt(i))
    		{
       			StringBuffer sb = new StringBuffer(hiddenWord); //Put the hidden word into a StringBuffer (StringBuffer is a write-able string, the primative String is read-only).
    			sb.setCharAt(i, guess); //If the guess the character in the secretWord string for the index of i, then changed the buffered hiddenWord to the guess for index i.
    			hiddenWord = sb.toString(); //Set the buffered hiddenWord to the string hiddenWord.
    			changed = true; //Say that we changed the hidden, so the user was correct!
    			//No need to close because a StringBuffer is not active in memory, so there would be no memory leak.
    		}
    	}
    	return changed; //Return the value of changed to say whether the user was correct or false.
    }
    
    /**
     * Finds the size of the file in terms of line numbers, using the br.readLine() method.
     * @param fileName The name of the file that you want to read.
     * @return The amount, as an int, of lines in the file.
     */
    private static int linesInLexicon()
    {
    	int lines = 0;
    	
    	try 
    	{
			BufferedReader br = new BufferedReader(new FileReader(lexiconFile)); //Wrap FileReader in a BufferedReader (BufferedReader brings the file into memory, reducing the time it takes to read and write. We are not using StringBuffer because there is no need to edit the String).
			String str = br.readLine();
			
			while (str != null)
			{
				str = br.readLine();
				lines++;
			}
			
    		br.close(); //Close to BufferedReader to save memory leaks!
    		
    	} catch (Exception e) {e.printStackTrace();} //Print any errors to the console.
    	
    	return lines; //Return the number of lines.
    }
    
    /**
     * Getter for the line that the secretWord is on
     * @return
     */
    public int getSecretWordLine() {return wordLine;}
    
    /** 
     * Getter for the go boolean 
     * @return True if the game is running, false if not 
     */
    public boolean isGo() {return go;}
    
    /** 
     * Setter for the go boolean 
     * @param Bool true to run the game, false to stop. You don't want to mess with this
     */
    public void setGo(boolean Bool) {go = Bool;}
    
    /** 
     * Getter for the consoleMode boolean 
     * @return True if in console mode, false if not in console mode 
     */
    public static boolean isConsoleMode(Boolean bool) {if (consoleMode == bool) {return true;} else {return false;}}
    
    /** 
     * Setter for the consoleMode boolean 
     * @param Bool Boolean Bool, Changes if in console mode or not 
     */
    public static void setConsoleMode(Boolean Bool) {consoleMode = Bool;}
}
