import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Game extends JFrame
{
	static int numberOfSteps;
	private static JLabel counter;
	static JSplitPane pane;
	
	private Game() throws IOException
	{
		super("Sokoban");
		Board.playerState=PlayerState.FRONT;
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		counter=new JLabel(numberOfSteps+"");
		Board board=new Board(0);
		JSplitPane paneOfPanes=new JSplitPane(JSplitPane.VERTICAL_SPLIT, counter, new LevelsPane(this));
		pane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, paneOfPanes, board);
		add(pane);
		setResizable(false);
		pack();
		setVisible(true);
		playBackgroundMusic();
		pane.getRightComponent().setFocusable(true);
		pane.getRightComponent().requestFocus();
	}
	
	
	static int getNumberOfSteps()
	{
		return numberOfSteps;
	}
	
	public static void main(String[] args) throws IOException
	{
		new Game();
	}
	
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
			exc.printStackTrace(System.out);
		}
	}
	
	static JLabel getCounter()
	{
		return counter;
	}
	
	static void setNumberOfSteps()
	{
		numberOfSteps=0;
	}
}