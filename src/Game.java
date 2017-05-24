import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Game extends JFrame
{
	private static JSplitPane pane;
	private Game() throws IOException
	{
		super("Sokoban");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//getContentPane().setLayout(new BorderLayout());
		pane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new LevelsPane(), new Board(0));
		//pane.setDividerLocation(150);
		getContentPane().add(pane);
		setSize(800, 600);
		setResizable(false);
		setVisible(true);
		//pack();
	}
	
	static void refreshBoard(int level) throws IOException
	{
		pane.setRightComponent(new Board(level));
	}
	
	public static void main(String[] args) throws IOException
	{
		new Game();
	}
}