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
		for (int i=1; i<=loader.getLevelsCount(); i++)
		{
			buttons[i-1]=new JButton("Level "+i);
			int finalI=i;
			buttons[i-1].addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
//					System.out.println(finalI);
					Board.board=loader.get(finalI-1);
				}
			});
			add(buttons[i-1]);
		}
	}
}