import levelLoader.LevelLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

class LevelsPane extends JPanel
{
	private final LevelLoader loader=new LevelLoader();
	private final JButton[] buttons;
	LevelsPane() throws IOException
	{
		super(new GridLayout());
		loader.load("levels.txt");
		((GridLayout)getLayout()).setRows(loader.getLevelsCount());
		buttons=new JButton[loader.getLevelsCount()];
		for (int i=0; i<loader.getLevelsCount(); i++)
		{
			buttons[i]=new JButton("Level "+(i+1));
			final int finalI=i;
			buttons[i].addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
//					System.out.println(finalI+1);
					Board.board=loader.get(finalI);
					try
					{
						Game.refreshBoard(finalI);
					}
					catch (IOException e1)
					{
						e1.printStackTrace();
					}
				}
			});
			add(buttons[i]);
		}
	}
}