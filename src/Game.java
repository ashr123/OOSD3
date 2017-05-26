import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class Game extends JFrame
{
	static int numberOfSteps;
	static JSplitPane pane;
	static int level;
	static Board board;
	
	private Game() throws IOException
	{
		super("Sokoban");
		Board.playerState=PlayerState.FRONT;
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//getContentPane().setLayout(new BorderLayout());
		
		
		//getContentPane().add(pane);
		board=new Board(0);
		pane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new LevelsPane(board), board);
		pane.getRightComponent().addKeyListener(board);
		add(pane);
		//add(board);
//		addKeyListener(new KeyListener()
//		{
//			@Override
//			public void keyTyped(KeyEvent e)
//			{
//
//			}
//
//			@Override
//			public void keyPressed(KeyEvent e)
//			{
//				playWalkingSound();
//				numberOfSteps++;
//				switch (e.getKeyCode())
//				{
//					case KeyEvent.VK_LEFT:
//						System.out.println("LEFT");
//						Player.MoveLeft(board);
//						break;
//					case KeyEvent.VK_RIGHT:
//						System.out.println("RIGHT");
//						Player.MoveRight(board);
//						break;
//					case KeyEvent.VK_UP:
//						System.out.println("UP");
//						Player.MoveUp(board);
//						break;
//					case KeyEvent.VK_DOWN:
//						System.out.println("DOWN");
//						Player.MoveDown(board);
//						break;
//				}
//			}
//
//			@Override
//			public void keyReleased(KeyEvent e)
//			{
//
//			}
//		});
		setResizable(false);
		pack();
		setVisible(true);
		playBackgroundMusic();
		pane.getRightComponent().setFocusable(true);
		pane.getRightComponent().requestFocus();
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
			//clip.start();
			clip.loop(Integer.MAX_VALUE);
		}
		catch (Exception exc)
		{
			exc.printStackTrace(System.out);
		}
	}
	
	private void playWalkingSound()
	{
		try
		{
			Clip clip=AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File("Sounds/WalkingOnGrassSound.wav")));
			clip.start();
		}
		catch (Exception exc)
		{
			exc.printStackTrace(System.out);
		}
	}
}