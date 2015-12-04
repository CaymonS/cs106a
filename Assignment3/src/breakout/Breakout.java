package breakout;
/**
 *  @author Caymon Sullivan
 */

/** ACM library imports*/
import acm.graphics.*;
import acm.program.*;
import acm.util.*;

/** Java library imports*/
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;
	
	/** X Offset of scores*/
	private static final int LABEL_X_OFFSET = 5;

	//Ignore this method for now...
	public static void main (String[] args) 
	{
		new Breakout().start(args);
		
	}
	
	//Variables 
	/** Random Generator*/
	private RandomGenerator rgen = RandomGenerator.getInstance();
	/** Delay for moving the ball*/
	double DELAY = 5;  
	/** Find the current directory that the user has the Breakout program in*/
	static String workingDir = System.getProperty("user.dir");
	/** Total amount of bricks */
	int brickCounter = 100;
	/** Velocity of the ball on the X-Axis*/
	int vx = rgen.nextInt(-2, 2);
	/** Velocity of the ball on the Y-Axis*/
	int vy = rgen.nextInt(-2, 2);
	/** Number of deaths for game mechanics*/
	int deaths = 0;
	/** Number of lives for score mechanics*/
	int numLives = 3;
	/** Score number for score mechanics*/
	int numScore;
	/** Modifier amount used for score mechanics*/
	int numScoreMod = 3;
	
	/** Amount of bricks broken in a row, reset after a death*/
	int numCombo = 0;
	
	/** 
	 *	Boolean to run {@link #playGame()}
	 */
	boolean go = true;
	/** Start and stops the ball movement*/
	boolean moveBall = true;
	/** Attempt to stop the ball on the paddle*/
	boolean ballOnPaddle;
	/** Strings for logger file*/
	static Logger logger = Logger.getLogger("BreakOutLog");
	/** Initiate a FileHandler*/
	static FileHandler fh;  
		
	/** 
	 * 	Runs the Breakout program.
	 */
	public void run() 
	{
		while (vx == 0 || vy == 0)
		{
			if (vx == 0) 
			{
				vx = rgen.nextInt(-2, 2);
				
			} else if (vy == 0)
			{
				vy = rgen.nextInt(-2, 2);
			}
		}
		
		setup();
		playGame();
	}
	
	/**
	 * 	Main game mechanics and physics
	 * 	Used in {@link #run()}
	 */
	private void playGame()
	{
		resetBall(); //Replaced myWait(), might fix double click to start OBOB
		
		while (go == true)
		{
			
			//Score mechanics
			add(score, 0, score.getAscent());
			add(lives, score.getWidth() + LABEL_X_OFFSET, lives.getAscent());
			add(combo, lives.getX() + lives.getWidth() + LABEL_X_OFFSET, combo.getAscent());
			
			//Game mechanics
			moveBall();
			checkForCollidingObject();
				
			//End game
			if (deaths == NTURNS)
			{
				loser();
				go = false; //Ends the while loop
				
			}
			if (brickCounter == 0)
			{
				winner();
				go = false; //Ends the while loop
			}
		} //END while 
	}
	
	/**
	 *  Creates the GObjects needed for the game (The Setup). Also adds listeners
	 *	Used in {@link #run()}
	 */
	private void setup()
	{
		//Listeners
		addMouseListeners();
		addKeyListeners();
		addActionListeners();
	
		//Background effects
		setBackground(Color.BLACK);
		
		//Game objects
		paddle.setFillColor(Color.WHITE);
		ball.setFillColor(Color.WHITE);
		paddle.setFilled(true);
		ball.setFilled(true);
		add(ball);
		add(paddle);
		makeBricks();
		
		//Labels n' stuff
		score.setColor(Color.WHITE);
		score.sendToFront();
		lives.setColor(Color.WHITE);
		lives.sendToFront();
		combo.setColor(Color.WHITE);
		combo.sendToFront();
		
		//Logger
				try 
				{  
					fh = new FileHandler(workingDir + "BreakOut.log");  
					logger.addHandler(fh);
					SimpleFormatter formatter = new SimpleFormatter();  
			        fh.setFormatter(formatter);
				} catch (IOException e) {  
					e.printStackTrace();
				} catch (SecurityException se) {
					se.printStackTrace();
				}
	}
	
	/**
	 * 	Moves the paddle based on the position of the mouse 
	 */
	public void mouseMoved(MouseEvent e)
	{
		if (e.getX() < getWidth())
		{
			paddle.setLocation(e.getX() - (PADDLE_WIDTH/2), getHeight() - PADDLE_Y_OFFSET);
		}
	}
	
	/**
	 *	//TODO Grab the ball on the paddle and move it on the X-Axis only
	 */
	public void keyPressed(KeyEvent e) 
	{
		int key = e.getKeyCode(); //Gets the key
		
		if (key == KeyEvent.VK_SPACE)
		{
				moveBall = false;
				//ball.setLocation(ball.getX() + paddle.getX(), ball.getY() + paddle.getY());
		}
		else 
		{
			moveBall = true;
		}
	}
	
	/**
	 *	//TODO Lets the ball off the paddle in the same direction it was going
	 */
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode(); //Gets the key
		if (key == KeyEvent.VK_SPACE)
		{
			moveBall = true;
		} 
		else 
		{
			moveBall = false;
		}
	}
	
	/**
	 *  Resets the ball to the middle of the screen
	 */
	private void resetBall()
	{
		ball.setLocation(getWidth()/2, getHeight()/2);
		myWait();
	}
	
	/**
	 *  Moves the ball based on its current velocity
	 */   
	private void moveBall() 
	{
		if (moveBall == true)
		{
			ball.move(vx, vy);
			pause(DELAY);
		}
	}
	
	/**
	 *	Checks for when a side of the ball hits something and does what it needs to do
	 */
	private void checkForCollidingObject()
	{
		GObject collider = getHitSide();
		
		//TOP
		if (ball.getY() <= HEIGHT - HEIGHT)
		{
			 vy = -vy;
		}
		
		//LEFT WALL
		if (ball.getX() <= WIDTH - WIDTH)
		{
			vx = -vx;
		}
		
		//RIGHT WALL
		if (ball.getX() + ball.getWidth() >= WIDTH)
		{
			vx = -vx;
		}
		
		//BOTTOM
		if (ball.getY() + ball.getHeight() >= HEIGHT)
		{
			//Sounds
			effects.playSound("fatality.wav");
			
			//Scoring system
			numLives--;
			remove(lives);
			lives.setLabel("Lives: " + numLives);
			numScoreMod--;
			numCombo = 0;
			remove(combo);
			combo.setLabel("Combo: " + numCombo);
			
			//Score mechanics
			add(score, 0, score.getAscent());
			add(lives, score.getWidth() + LABEL_X_OFFSET, lives.getAscent());
			add(combo, lives.getX() + lives.getWidth() + LABEL_X_OFFSET, combo.getAscent());
			
			//Game Mechanic
			deaths++;
			if (deaths != NTURNS)
			{
				resetBall();
				
			}
		}
		
		//Paddle TOP
		if (collider == paddle)
		{
			//Attempt at stopping the ball only when on the paddle
			ballOnPaddle = true;
			
			//Game Mechanic
			vy = -vy;
		}
		
		//BRICK
		if (collider != null && collider != paddle && collider != ball && collider != score && collider != lives && collider != combo)
		{	
			//Game Mechanic
			brickCounter--;
			numCombo++;
			remove(collider);
			vy = -vy;
			
			//Sounds
			if (numCombo == 1) 
			{
				effects.playSound("firstBlood.wav");
			} 
			else if (brickCounter == 97) 
			{
				effects.playSound("tripleKill.wav");
			} 
			else if (numCombo == 10)
			{
				//effects.playSound("combo.wav");
				//if (DELAY >= 1) DELAY -= 1;
				//vx += 1;
				//vy += 1;
			} 
			else if (numCombo == 20)
			{
				effects.playSound("mmmMonsterKill.wav");
			} 
			else if (numCombo == 30)
			{
				effects.playSound("megaKill.wav");
			} 
			else if (numCombo == 40)
			{
				effects.playSound("godLike.wav");
				//if (DELAY >= 1) DELAY -= 1;
			} 
			else if (numCombo == 50)
			{
				effects.playSound("dominating.wav");
			}
			else if (numCombo == 60)
			{
				effects.playSound("killingSpree.wav");
			} 
			else if (numCombo == 70)
			{
				effects.playSound("ultraKill.wav");
				//if (DELAY >= 1) DELAY -= 1;
			} 
			else if (numCombo == 80)
			{
				effects.playSound("unstoppable.wav");
			} 
			else if (numCombo == 90)
			{
				//effects.playSound("holySht.wav");
			}
			
			//Score system
			remove(score);
			numScore += numScoreMod;
			score.setLabel("Score: " + (numScore));	
			remove(combo);
			combo.setLabel("Combo: " + (numCombo));
			
		}	
	}
	
	/**
	 *	Gets the GObject that hits the Top, Bottom, Left or Right of the ball
	 *	@return object GObject that hits any side of the ball 
	 */
	private GObject getHitSide()
	{
		GObject object;
		
		//Top of ball
		if (getElementAt(ball.getX() + BALL_RADIUS, ball.getY()) != null)
		{
			object = getElementAt(ball.getX() + BALL_RADIUS, ball.getY());
			return (object);
		}
		
		//Bottom of ball
		if (getElementAt(ball.getX() + BALL_RADIUS, ball.getY() + BALL_RADIUS) != null)
		{
			object = getElementAt(ball.getX() + BALL_RADIUS, ball.getY() + BALL_RADIUS);
			return (object);
		}
		
		//Left of ball
		if (getElementAt(ball.getX(), ball.getY() + BALL_RADIUS) != null)
		{
			object = getElementAt(ball.getX(), ball.getY() + BALL_RADIUS/2);
			return (object);
		}
		
		//Right of ball
		if (getElementAt(ball.getX() + (BALL_RADIUS * 2), ball.getY() + BALL_RADIUS) != null)
		{
			object = getElementAt(ball.getX() + BALL_RADIUS, ball.getY() + BALL_RADIUS/2);
			return (object);
		}
		
		return (null);
	} 

	/** 
	 * 	Draw the Bricks on the canvas to setup the game
	 */
	private void makeBricks()
	{
		for (int row = 0; row < NBRICK_ROWS; row++)
		{
			for (int column = 0; column < NBRICKS_PER_ROW; column++)
			{	
				GRect brick = new GRect(
						(BRICK_SEP + BRICK_WIDTH) * column, //X-Axis
						((BRICK_SEP + BRICK_HEIGHT) * row) + BRICK_Y_OFFSET, //Y-Axis
						BRICK_WIDTH,
						BRICK_HEIGHT
						);
				
				add(brick);
				//brick.sendToBack();
				brick.setFilled(true);
				
				//Change the bricks color per row
				if (row == 0 || row == 1)
				{
					brick.setFillColor(Color.RED);
					
				} 
				else if (row == 2 || row == 3)
				{
					brick.setFillColor(Color.ORANGE);
					
				} 
				else if (row == 4 || row == 5)
				{
					brick.setFillColor(Color.YELLOW);
					
				} 
				else if (row == 6 || row == 7)
				{
					brick.setFillColor(Color.GREEN);
					
				} 
				else if (row == 8 || row == 9)
				{
					brick.setFillColor(Color.CYAN);
				}
			}
			
		}
	} //END makeBricks()
	
	/**	
	 * 	Runs if the user met the winning requirements
	 */
	private void winner()
	{
		//Graphics
		GLabel winnerLabel = new GLabel("You win!");
		winnerLabel.setLocation(
				getWidth()/2 - (winnerLabel.getWidth()/2), 
				getHeight()/2 - (winnerLabel.getHeight()/2)
				);
		
		winnerLabel.setColor(Color.GREEN);
		add(winnerLabel);
		
		//Sounds
		effects.playSound("wickedSick.wav");
		
		//Score mechanics
		add(score, 0, score.getAscent());
		add(lives, score.getWidth() + LABEL_X_OFFSET, lives.getAscent());
		add(combo, lives.getX() + lives.getWidth() + LABEL_X_OFFSET, combo.getAscent());
		
		pause(1000); //Wait for one second
		restart();
		
	} //END winner()
	
	/**	
	 *	Runs if the user met the losing requirements
	 */
	private void loser() 
	{
		GLabel loserLabel = new GLabel("You lost :( R.I.P.");
		loserLabel.setLocation(
				getWidth()/2 - (loserLabel.getWidth()/2),
				getHeight()/2 - (loserLabel.getHeight()/2)
				);
		
		loserLabel.setColor(Color.RED);
		add(loserLabel);
		
		//Sounds
		effects.playSound("fatality.wav");
		
		//restart
		pause(1000); //Wait one second
		restart();
		
	} //END loser()
	
	/**
	 *	Pauses the game until the user clicks
	 */
	public void myWait()
	{
		GLabel waitLabel = new GLabel("Click to start");
		waitLabel.setLocation(getWidth()/2 - waitLabel.getWidth()/2, getHeight()/2 - waitLabel.getHeight()/2);
		waitLabel.setColor(Color.RED);
		add(waitLabel);
		waitForClick();
		remove(waitLabel);
	}
	
	/**
	 * Restarts the program
	 * Used in {@link loser()} after a one second delay
	 */
	private void restart()
	{
		
		GLabel restartLabel = new GLabel("Click to restart");
		restartLabel.setLocation(getWidth()/2 - restartLabel.getWidth()/2, ((2 * getHeight()) / 3) - restartLabel.getHeight()/2);
		restartLabel.setColor(Color.GREEN);
		add(restartLabel);
		waitForClick();
		remove(restartLabel);
		new Breakout().start();
		
	}
	
	/**
	 *	Creates a GLabel for the score you have in the game
	 *	Used in {@link #checkForCollidingObject()} to effect the score
	 */
	GLabel score = new GLabel("Score: " + numScore);
	
	/**
	 *	Creates a GLabel for the number of lives (or turns) you have left
	 *	Used in {@link #checkForCollidingObject()} to effect the amount of lives
	 */
	GLabel lives = new GLabel("Lives: " + numLives);
	
	/**
	 *	Creates a GLabel for the current combo
	 *	Used in {@link #checkForCollidingObject()} to effect the combo sounds
	 */
	GLabel combo = new GLabel("Combo: " + numCombo);
	
	/**
	 *	Creates a GRect object for the paddle
	 *	Used in {@link #mouseMoved(MouseEvent e)} and {@link #checkForCollidingObject()}
	 */
	GRect paddle = new GRect(
			(WIDTH/2) - (PADDLE_WIDTH/2), 
			HEIGHT - PADDLE_Y_OFFSET, 
			PADDLE_WIDTH, 
			PADDLE_HEIGHT
			);
	
	/**
	 *	Creates a GOval object for the ball
	 *	Used in {@link #getHitSide()}, {@link #moveBall()} and {@link #resetBall()}
	 */
	GOval ball = new GOval(
			WIDTH/2, 
			HEIGHT/2, 
			BALL_RADIUS, 
			BALL_RADIUS
			);
		
} //END OF CLASS
