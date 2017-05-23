import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Game extends JFrame
{
	private Game() throws IOException
	{
		super("Sokoban");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//getContentPane().setLayout(new BorderLayout());
		JSplitPane pane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new LevelsPane(), new Board());
		//pane.setDividerLocation(150);
		getContentPane().add(pane);
		setSize(800, 600);
		setVisible(true);
	}
	
	public static void main(String[] args) throws IOException
	{
		new Game();
	}
}