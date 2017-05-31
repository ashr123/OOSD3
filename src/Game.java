import levelLoader.LevelLoader;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

class Game extends JFrame
{
	private static final LevelLoader loader=new LevelLoader();
	private static JLabel counterLabel=new JLabel();
	private static int counter;
	private JSplitPane pane;
	
	/**
	 * Builds a game window
	 * @throws IOException if there is any error with the levels file
	 * @throws LineUnavailableException if a clip object is not available due to resource restrictions
	 * @throws UnsupportedAudioFileException if the {@code File} does not point to valid audio file data recognized by the system
	 */
	private Game() throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		super("Sokoban");
		loader.load();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JSplitPane paneOfPanes=new JSplitPane(JSplitPane.VERTICAL_SPLIT, counterLabel, new LevelsPane(this));
		paneOfPanes.setEnabled(false);
		pane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, paneOfPanes, new Board(0));
		pane.setEnabled(false);
		add(pane);
		setResizable(false);
		pack();
		setVisible(true);
		pane.getRightComponent().setFocusable(true);
		pane.getRightComponent().requestFocus();
		playBackgroundMusic();
	}
	
	static LevelLoader getLoader()
	{
		return loader;
	}
	
	static void increaseNumberOfSteps()
	{
		counterLabel.setText(++counter+"");
	}
	
	/**
	 * Main function
	 * @param args an empty {@link String} array
	 * @throws IOException if an I/O exception occurs
	 * @throws LineUnavailableException if a clip object is not available due to resource restrictions
	 * @throws UnsupportedAudioFileException if the {@code File} does not point to valid audio file data recognized by the system
	 */
	public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException
	{
		new Game();
	}
	
	/**
	 * Plays the background music
	 * @throws LineUnavailableException if a clip object is not available due to resource restrictions
	 * @throws IOException if an I/O exception occurs
	 * @throws UnsupportedAudioFileException if the {@code File} does not point to valid audio file data recognized by the system
	 */
	private static void playBackgroundMusic() throws LineUnavailableException, IOException,
	                                                 UnsupportedAudioFileException
	{
		Clip clip=AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(Game.class.getClassLoader().getResource("Sounds/GameMusic.wav")));
		clip.loop(Integer.MAX_VALUE);
	}
	
	static void resetCounter()
	{
		counterLabel.setText((counter=0)+"");
	}
	
	JSplitPane getPane()
	{
		return pane;
	}
}