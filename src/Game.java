import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class Game extends JFrame
{
	private static JSplitPane pane;
	static int level;
	static Board board;
	private Game() throws IOException
	{
		super("Sokoban");
		Board.playerState=PlayerState.FRONT;
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//getContentPane().setLayout(new BorderLayout());
		//pane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new LevelsPane(), new Board(0));
		
		//getContentPane().add(pane);
		board=new Board(0);
		getContentPane().add(board);
		playBackgroundMusic("Sounds/GameMusic.wav");
		addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
			
			}
			
			@Override
			public void keyPressed(KeyEvent e)
			{
				try
				{
					switch (e.getKeyCode())
					{
						case KeyEvent.VK_LEFT:
							
							break;
						case KeyEvent.VK_RIGHT:
							
							break;
						case KeyEvent.VK_UP:
							
							break;
						case KeyEvent.VK_DOWN:
							System.out.println("DOWN");
							Player.MoveDown();
							break;
					}
					Game.refreshBoard(level);
				}
				catch (IOException e1)
				{
					e1.printStackTrace();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e)
			{
			
			}
		});
		//setSize(800, 600);
		setResizable(false);
		setVisible(true);
		pack();
	}
	
	static void refreshBoard(int level2) throws IOException
	{
		level=level2;//
		//pane.setRightComponent(new Board());
		//container.add(board);
	}
	
	public static void main(String[] args) throws IOException
	{
		new Game();
	}
	
	public static void playBackgroundMusic(String filename)
	{
		try
		{
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(filename)));
			clip.start();
		}
		catch (Exception exc)
		{
			exc.printStackTrace(System.out);
		}
	}
}