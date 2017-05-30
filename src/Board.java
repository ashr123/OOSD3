import levelLoader.Cell;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 * Represents the player direction state
 */
enum PlayerState
{
	FRONT, BACK, LEFT, RIGHT
}

/**
 * Represents a Sokoban board
 */
@SuppressWarnings("ConstantConditions")
class Board extends JPanel
{
	private static final ImageIcon IMAGE_ICON_WALL=new ImageIcon(Board.class.getClassLoader().getResource("Images/WallBlack.png"));
	private static final ImageIcon IMAGE_ICON_STORAGE_WITH_BOX=new ImageIcon(Board.class.getClassLoader().getResource("Images/StorageWithBox.png"));
	private static final ImageIcon IMAGE_ICON_STORAGE=new ImageIcon(Board.class.getClassLoader().getResource("Images/Storage.png"));
	private static final ImageIcon IMAGE_ICON_GRASS=new ImageIcon(Board.class.getClassLoader().getResource("Images/Grass.png"));
	private static final ImageIcon IMAGE_ICON_CHARACTER_FRONT=new ImageIcon(Board.class.getClassLoader().getResource("Images/CharacterFront.png"));
	private static final ImageIcon IMAGE_ICON_CHARACTER_BACK=new ImageIcon(Board.class.getClassLoader().getResource("Images/CharacterBack.png"));
	private static final ImageIcon IMAGE_ICON_CHARACTER_LEFT=new ImageIcon(Board.class.getClassLoader().getResource("Images/CharacterLeft.png"));
	private static final ImageIcon IMAGE_ICON_CHARACTER_RIGHT=new ImageIcon(Board.class.getClassLoader().getResource("Images/CharacterRight.png"));
	private static final ImageIcon IMAGE_ICON_BOX=new ImageIcon(Board.class.getClassLoader().getResource("Images/Box.png"));
	static Cell[][] board;
	static PlayerState playerState;
	private int numberOfBoxes;
	private JLabel[][] jLabels;
	
	/**
	 * Builds a new board
	 * @param level the level of which the player wants to play
	 */
	Board(int level)
	{
		board=Game.loader.get(level);
		final Board temp=this;
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
					playWalkingSound();
				}
				catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1)
				{
					e1.printStackTrace();
				}
				Game.numberOfSteps++;
				Game.getCounter().setText(Game.getNumberOfSteps()+"");
				switch (e.getKeyCode())
				{
					case KeyEvent.VK_LEFT:
						Player.MoveLeft(temp);
						break;
					case KeyEvent.VK_RIGHT:
						Player.MoveRight(temp);
						break;
					case KeyEvent.VK_UP:
						Player.MoveUp(temp);
						break;
					case KeyEvent.VK_DOWN:
						Player.MoveDown(temp);
						break;
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e)
			{
			
			}
		});
		buildBoard();
	}
	
	/**
	 * Plays a walking sound for every move the player makes
	 * @throws LineUnavailableException if a clip object is not available due to resource restrictions
	 * @throws IOException if an I/O exception occurs
	 * @throws UnsupportedAudioFileException if the {@code File} does not point to valid audio file data recognized by the system
	 */
	private static void playWalkingSound() throws LineUnavailableException, IOException, UnsupportedAudioFileException
	{
		Clip clip=AudioSystem.getClip();
		clip.open(AudioSystem.getAudioInputStream(Board.class.getClassLoader().getResource("Sounds/WalkingOnGrassSound.wav")));
		clip.start();
	}
	
	/**
	 * Builds a board
	 */
	private void buildBoard()
	{
		Game.resetNumberOfSteps();
		Game.getCounter().setText(0+"");
		setLayout(new GridLayout(board.length, board[0].length));
		//setLayout(new GridLayout(board[0].length, board.length));
		jLabels=new JLabel[board.length][board[0].length];
		for (int i=0; i<board.length; i++)
			for (int j=0; j<board[i].length; j++)
			{
				if (!board[i][j].isFloor())//is a wall
				{
					jLabels[i][j]=new JLabel(IMAGE_ICON_WALL);
					add(jLabels[i][j]);
					continue;
				}
				if (board[i][j].isStorage() && board[i][j].hasBox())//Storage with box
				{
					numberOfBoxes++;
					jLabels[i][j]=new JLabel(IMAGE_ICON_STORAGE_WITH_BOX);
					add(jLabels[i][j]);
					continue;
				}
				if (board[i][j].isStorage() && !board[i][j].hasBox() && !board[i][j].hasPlayer())//Storage without a box
				{
					jLabels[i][j]=new JLabel(IMAGE_ICON_STORAGE);
					add(jLabels[i][j]);
					continue;
				}
				if (board[i][j].isEmptyFloor())//Is empty floor
				{
					jLabels[i][j]=new JLabel(IMAGE_ICON_GRASS);
					add(jLabels[i][j]);
					continue;
				}
				if (board[i][j].hasPlayer() && !board[i][j].hasBox())//Player
				{
					Player.playerLocationY=i;
					Player.playerLocationX=j;
					switch (playerState)
					{
						case FRONT:
							jLabels[i][j]=new JLabel(IMAGE_ICON_CHARACTER_FRONT);
							break;
						case BACK:
							jLabels[i][j]=new JLabel(IMAGE_ICON_CHARACTER_BACK);
							break;
						case LEFT:
							jLabels[i][j]=new JLabel(IMAGE_ICON_CHARACTER_LEFT);
							break;
						case RIGHT:
							jLabels[i][j]=new JLabel(IMAGE_ICON_CHARACTER_RIGHT);
							break;
					}
					add(jLabels[i][j]);
					continue;
				}
				if (board[i][j].hasBox())//Box on the floor
				{
					numberOfBoxes++;
					jLabels[i][j]=new JLabel(IMAGE_ICON_BOX);
					add(jLabels[i][j]);
				}
			}
//		for (int i=0; i<jLabels[0].length; i++)
//			for (JLabel[] aLevel : jLabels)
//				add(aLevel[i]!=null ? aLevel[i] : new JLabel());
	}
	
	/**
	 * Refreshes the board after every move
	 */
	void RefreshBoard()
	{
		int counterPlacedBoxes=0;
		for (int i=0; i<board.length; i++)
			for (int j=0; j<board[i].length; j++)
			{
				if (!board[i][j].isFloor())//is a wall
				{
					jLabels[i][j].setIcon(IMAGE_ICON_WALL);
					continue;
				}
				if (board[i][j].isStorage() && board[i][j].hasBox())//Storage with box
				{
					counterPlacedBoxes++;
					jLabels[i][j].setIcon(IMAGE_ICON_STORAGE_WITH_BOX);
					continue;
				}
				if (board[i][j].isStorage() && !board[i][j].hasBox() && !board[i][j].hasPlayer())//Storage without a box
				{
					jLabels[i][j].setIcon(IMAGE_ICON_STORAGE);
					continue;
				}
				if (board[i][j].isEmptyFloor())//Is empty floor
				{
					jLabels[i][j].setIcon(IMAGE_ICON_GRASS);
					continue;
				}
				if (board[i][j].hasPlayer() && !board[i][j].hasBox())//Player
				{
					Player.playerLocationY=i;
					Player.playerLocationX=j;
					switch (playerState)
					{
						case FRONT:
							jLabels[i][j].setIcon(IMAGE_ICON_CHARACTER_FRONT);
							break;
						case BACK:
							jLabels[i][j].setIcon(IMAGE_ICON_CHARACTER_BACK);
							break;
						case LEFT:
							jLabels[i][j].setIcon(IMAGE_ICON_CHARACTER_LEFT);
							break;
						case RIGHT:
							jLabels[i][j].setIcon(IMAGE_ICON_CHARACTER_RIGHT);
							break;
					}
					continue;
				}
				if (board[i][j].hasBox())//Box on the floor
					jLabels[i][j].setIcon(IMAGE_ICON_BOX);
			}
		if (numberOfBoxes==counterPlacedBoxes)
			JOptionPane.showMessageDialog(null, "Congratulations, you did it!");
	}
}