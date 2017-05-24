import levelLoader.Cell;
import levelLoader.LevelLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Board extends JPanel
{
	private final LevelLoader loader=new LevelLoader();
	static Cell[][] board;
	private JLabel[][] level;
	private int playerLocationX, playerLocationY;
	
	Board(int level) throws IOException
	{
		loader.load("levels.txt");
		board=loader.get(level);
		buildBoard();
	}
	
	void buildBoard()
	{
		setLayout(new GridLayout(board[0].length, board.length));
		level=new JLabel[board.length][board[0].length];
		for (int i=0; i<board.length; i++)
			for (int j=0; j<board[i].length; j++)
			{
				if (!board[i][j].isFloor())//is a wall
				{
					level[i][j]=new JLabel(new ImageIcon("wall.jpg"));
					//add(level[i][j]);
					continue;
				}
				if (board[i][j].isStorage() && board[i][j].hasBox())//Storage with box
				{
					//TODO Change!!!
					//add(new JLabel());
					continue;
				}
				if (board[i][j].isStorage() && !board[i][j].hasBox() && !board[i][j].hasPlayer())//Storage without a box
				{
					level[i][j]=new JLabel(new ImageIcon("Storage.png"));
					//add(level[i][j]);
					continue;
				}
				if (board[i][j].isEmptyFloor())//Is empty floor
				{
					level[i][j]=new JLabel(new ImageIcon("floor.jpg"));
					//add(level[i][j]);
					continue;
				}
				if (board[i][j].hasPlayer() && !board[i][j].hasBox())//Player on floor
				{
					//TODO Change!!!
					//add(new JLabel());
					continue;
				}
				if (board[i][j].hasBox())//Box on the floor
				{
					level[i][j]=new JLabel(new ImageIcon("box.png"));
					//add(level[i][j]);
					continue;
				}
				if (!board[i][j].hasPlayer() && board[i][j].isStorage())//Player on storage
				{
					//TODO Change!!!
					//add(new JLabel());
				}
			}
		
		for (int i=0; i<level[0].length; i++)
			for (JLabel[] aLevel : level)
				add(aLevel[i]!=null ? aLevel[i] : new JLabel());
		addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
			
			}
			
			@Override
			public void keyPressed(KeyEvent e)
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
						
						break;
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e)
			{
			
			}
		});
	}
}