package breakout;

import java.io.File;
import java.util.logging.Level;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class effects extends Breakout
{	
	/**
	 *	Plays the sound file requested. Static means that this method is overall, not making new instance
	 *	@param file File name of the sound in a string format, WAV 16-bit only
	 *	@throws Exception If there was an error with the sound, prints an error to the console and prints the stack trace
	 */
	public static void playSound(String file) 
	{   
	    try 
	    {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(workingDir + "/music/" + file).getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	        //logger.info("Played: " + file);
	    } catch (Exception exception) 
	    {
	        System.out.println("Error with playing sound! Make sure it is WAV (Microsoft) signed 16-bit PCM");
	        logger.log(Level.SEVERE, "Error with playing sound! Make sure it is WAV (Microsoft) signed 16-bit PCM");
	        exception.printStackTrace();
	    }
	}
	/**
	 * //TODO
	 * Plays background music as requested in the main breakout class.
	 * @param file Background music file
	 * @throws Exception Exception if there was an error while playing the sound. 
	 */
	public static void playBackgroundSound(String file)
	{
		try
		{
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(workingDir + "/music/background/" + file).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	        
		} catch (Exception exception)
		{
			System.out.println("Error with playing background music! Make sure it is WAV (Microsoft) signed 16-bit PCM");
			logger.log(Level.SEVERE, "Error with playing background music! Make sure it is WAV (Microsoft) signed 16-bit PCM");
			exception.printStackTrace();
		}
}		
}
