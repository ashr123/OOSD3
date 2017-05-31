import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents a level chooser panel
 */
class LevelsPane extends JPanel
{
	
	/**
	 * Builds the level chooser panel
	 * @param game needed only for changing the frame size after choosing a level
	 */
	LevelsPane(final Game game)
	{
		super(new GridLayout());
		((GridLayout)getLayout()).setRows(Game.loader.getLevelsCount());
		for (int i=0; i<Game.loader.getLevelsCount(); i++)
		{
			add(new JButton("Level "+(i+1)));
			final int finalI=i;
			((JButton)getComponent(i)).addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					game.pane.setRightComponent(new Board(finalI));
					game.pane.getRightComponent().setFocusable(true);
					game.pane.getRightComponent().requestFocus();
					game.pack();
				}
			});
//			add(buttons[i]);
		}
	}
}