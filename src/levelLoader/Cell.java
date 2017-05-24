package levelLoader;

public class Cell
{
	private final int _x, _y;
	private boolean _isFloor;
	private boolean _isStorage;
	private boolean _hasPlayer;
	private boolean _hasBox;
	
	/**
	 * construct a Wall cell
	 */
	public Cell(int x, int y)
	{
//		_isFloor=false;
//		_isStorage=false;
//		_hasPlayer=false;
//		_hasBox=false;
		_x=x;
		_y=y;
	}
	
	/**
	 * construct a Floor cell
	 */
	public Cell(int x, int y, Boolean isStorage, Boolean hasPlayer, Boolean hasBox)
	{
		this(x, y);
		_isFloor=true;
		_isStorage=isStorage;
		_hasPlayer=hasPlayer;
		_hasBox=hasBox;
		//_x=x;
		//_y=y;
	}
	
	public Cell(Cell cell)
	{
		this(cell.get_x(), cell.get_y(), cell.isStorage(), cell.hasPlayer(), cell.hasBox());
	}
	
	public boolean isFloor()
	{
		return _isFloor;
	}
	
	public boolean isStorage()
	{
		return _isStorage;
	}
	
	public boolean hasPlayer()
	{
		return _hasPlayer;
	}
	
	public boolean hasBox()
	{
		return _hasBox;
	}
	
	public void set_isStorage(Boolean _isStorage)
	{
		if (!isFloor())
			return;
		this._isStorage=_isStorage;
	}
	
	public void set_hasPlayer(Boolean _hasPlayer)
	{
		if (!isFloor())
			return;
		this._hasPlayer=_hasPlayer;
	}
	
	public void set_hasBox(Boolean _hasBox)
	{
		if (!isFloor())
			return;
		this._hasBox=_hasBox;
	}
	
	public boolean isEmptyFloor()
	{
		return isFloor() && !hasBox() && !hasPlayer();
	}
	
//	@Override
//	public Cell clone()
//	{
//		Cell c=new Cell(_x, _y);
//		c._hasBox=this._hasBox;
//		c._hasPlayer=this._hasPlayer;
//		c._isFloor=this._isFloor;
//		c._isStorage=this._isStorage;
//		return c;
//	}
	
	public int get_x()
	{
		return _x;
	}
	
	public int get_y()
	{
		return _y;
	}
}