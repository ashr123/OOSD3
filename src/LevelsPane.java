import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Represents a level chooser panel
 */
class LevelsPane extends JPanel
{
	
	/**
	 * Builds the level chooser panel
	 * @param game needed only for changing the frame size after choosing a level
	 * @throws IOException if there is any error with the levels file
	 */
	LevelsPane(final Game game) throws IOException
	{
		super(new GridLayout());
		((GridLayout)getLayout()).setRows(Game.loader.getLevelsCount());
		JButton[] buttons=new JButton[Game.loader.getLevelsCount()];
		for (int i=0; i<Game.loader.getLevelsCount(); i++)
		{
			buttons[i]=new JButton("Level "+(i+1));
			final int finalI=i;
			buttons[i].addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					Board.board=Game.loader.get(finalI);
					try
					{
						Board.playerState=PlayerState.FRONT;
						Game.pane.setRightComponent(new Board(finalI));
						Game.pane.getRightComponent().setFocusable(true);
						Game.pane.getRightComponent().requestFocus();
						game.pack();
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