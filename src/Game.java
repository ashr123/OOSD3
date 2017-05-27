import levelLoader.LevelLoader;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

class Game extends JFrame
{
	static final LevelLoader loader=new LevelLoader();
	static int numberOfSteps;
	static JSplitPane pane;
	private static JLabel counter;
	
	/**
	 * Builds a game window
	 * @throws IOException if there is any error with the levels file
	 */
	private Game() throws IOException
	{
		super("Sokoban");
		loader.load();
		Board.playerState=PlayerState.FRONT;
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		counter=new JLabel(numberOfSteps+"");
		Board board=new Board(0);
		JSplitPane paneOfPanes=new JSplitPane(JSplitPane.VERTICAL_SPLIT, counter, new LevelsPane(this));
		paneOfPanes.setEnabled(false);
		pane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, paneOfPanes, board);
		pane.setEnabled(false);
		add(pane);
		setResizable(false);
		pack();
		setVisible(true);
		playBackgroundMusic();
		pane.getRightComponent().setFocusable(true);
		pane.getRightComponent().requestFocus();
	}
	
	/**
	 * @return the number of steps the player did in a single level
	 */
	static int getNumberOfSteps()
	{
		return numberOfSteps;
	}
	
	/**
	 * Main function
	 * @param args an empty {@link String} array
	 * @throws IOException if there is any error with the levels file
	 */
	public static void main(String[] args) throws IOException
	{
		new Game();
	}
	
	/**
	 * Plays the background music
	 */
	private static void playBackgroundMusic()
	{
		try
		{
			Clip clip=AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File("Sounds/GameMusic.wav")));
			clip.loop(Integer.MAX_VALUE);
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}
	
	static JLabel getCounter()
	{
		return counter;
	}
	
	/**
	 * Resets the number of steps when changing a level
	 */
	static void resetNumberOfSteps()
	{
		numberOfSteps=0;
	}
}