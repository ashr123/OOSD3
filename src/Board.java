import levelLoader.Cell;
import levelLoader.LevelLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

enum PlayerState
{
	FRONT, BACK, LEFT, RIGHT
}

public class Board extends JPanel
{
	private final LevelLoader loader=new LevelLoader();
	static Cell[][] board;
	private JLabel[][] jLabels;
	static int level;
	static PlayerState playerState;
	
	Board() throws IOException
	{
		loader.load("levels.txt");
		board=loader.get(level);
		buildBoard();
	}
	
	void buildBoard()
	{
		setLayout(new GridLayout(board.length, board[0].length));
		jLabels=new JLabel[board.length][board[0].length];
		for (int i=0; i<board.length; i++)
			for (int j=0; j<board[i].length; j++)
			{
				if (!board[i][j].isFloor())//is a wall
				{
					jLabels[i][j]=new JLabel(new ImageIcon("Images/WallBlack.png"));
					add(jLabels[i][j]);
					continue;
				}
				if (board[i][j].isStorage() && board[i][j].hasBox())//Storage with box
				{
					jLabels[i][j]=new JLabel(new ImageIcon("Images/StorageWithBox.png"));
					add(jLabels[i][j]);
					continue;
				}
				if (board[i][j].isStorage() && !board[i][j].hasBox() && !board[i][j].hasPlayer())//Storage without a box
				{
					jLabels[i][j]=new JLabel(new ImageIcon("Images/Storage.png"));
					add(jLabels[i][j]);
					continue;
				}
				if (board[i][j].isEmptyFloor())//Is empty floor
				{
					jLabels[i][j]=new JLabel(new ImageIcon("Images/Grass.png"));
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
							jLabels[i][j]=new JLabel(new ImageIcon("Images/CharacterFront.png"));
							break;
						case BACK:
							jLabels[i][j]=new JLabel(new ImageIcon("Images/CharacterBack.png"));
							break;
						case LEFT:
							jLabels[i][j]=new JLabel(new ImageIcon("Images/CharacterLeft.png"));
							break;
						case RIGHT:
							jLabels[i][j]=new JLabel(new ImageIcon("Images/CharacterRight.png"));
							break;
					}
					add(jLabels[i][j]);
					continue;
				}
				if (board[i][j].hasBox())//Box on the floor
				{
					jLabels[i][j]=new JLabel(new ImageIcon("Images/Box.png"));
					add(jLabels[i][j]);
					continue;
				}
//				if (!board[i][j].hasPlayer() && board[i][j].isStorage())//Player on storage
//				{
//					//TODO Change!!!
//					add(new JLabel());
//				}
			}
		
//		for (int i=0; i<jLabels[0].length; i++)
//			for (JLabel[] aLevel : jLabels)
//				add(aLevel[i]!=null ? aLevel[i] : new JLabel());
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
	}
}