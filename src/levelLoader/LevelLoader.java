package levelLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class LevelLoader
{
	private static final char CHAR_WALL='#';
	private static final char CHAR_FLOOR=' ';
	private static final char CHAR_PLAYER='@';
	private static final char CHAR_STORAGE='.';
	private static final char CHAR_BOX='$';
	private static final char CHAR_BOX_IN_STORAGE='*';
	private static final char CHAR_PLAYER_AT_STORAGE='+';
	
	/**
	 * contains the initial board state of all the levels
	 */
	private Vector<Cell[][]> _levels;
	
	public LevelLoader()
	{
		_levels=new Vector<>();
	}
	
	/**
	 * Loads all the levels to the internal levels buffer
	 * @throws IOException if there is any error with the file
	 */
	public void load() throws IOException
	{
		_levels.clear();
		
		BufferedReader br=new BufferedReader(new FileReader("levels.txt"));
		String line;
		Cell level[][]=null;
		int w=0;
		int h=0;
		int row=0;
		while ((line=br.readLine())!=null)
		{
			// end of level
			if (line.trim().isEmpty())
			{
				if (null!=level)
				{
					_levels.add(level);
					level=null;
				}
				continue;
			}
			
			// board size
			if (line.trim().startsWith("w"))
			{
				w=Integer.valueOf(line.trim().substring(1));
				continue;
			}
			if (line.trim().startsWith("h"))
			{
				h=Integer.valueOf(line.trim().substring(1));
				continue;
			}
			
			// comment
			if (line.startsWith(";"))
				continue;
			
			// start of level definition
			if (null==level && h>0 && w>0)
			{
				level=new Cell[w][h];
				row=0;
			}
			
			// regular board line
			for (int col=0; col<line.length(); col++)
			{
				Cell cell=parseCell(line.charAt(col));
				if (null!=cell && level!=null)
					level[col][row]=cell;
				else
				{
					br.close();
					return;
				}
			}
			row++;
		}
		if (null!=level)
			_levels.add(level);
		br.close();
	}
	
	/**
	 * @return the number of levels available
	 */
	public int getLevelsCount()
	{
		return _levels.size();
	}
	
	/**
	 * @param index the level number
	 * @return a deep copy of the initial state of level number {@code index}
	 */
	public Cell[][] get(int index)
	{
		Cell[][] output=new Cell[_levels.get(index).length][_levels.get(index)[0].length];
		for (int i=0; i<_levels.get(index).length; i++)
			for (int j=0; j<_levels.get(index)[i].length; j++)
				output[i][j]=new Cell(_levels.get(index)[i][j]);
		return output;
	}
	
	/**
	 * create {@link Cell} instance from {@code char} representation
	 * @return the {@link Cell} object
	 */
	private Cell parseCell(char cell)
	{
		switch (cell)
		{
			case CHAR_WALL:
				return new Cell();
			case CHAR_FLOOR:
				return new Cell(false, false, false);
			case CHAR_PLAYER:
				return new Cell(false, true, false);
			case CHAR_STORAGE:
				return new Cell(true, false, false);
			case CHAR_BOX:
				return new Cell(false, false, true);
			case CHAR_BOX_IN_STORAGE:
				return new Cell(true, false, true);
			case CHAR_PLAYER_AT_STORAGE:
				return new Cell(true, true, false);
			default:
				return null;
		}
	}
}