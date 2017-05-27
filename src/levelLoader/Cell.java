package levelLoader;

public class Cell
{
	private boolean _isFloor;
	private boolean _isStorage;
	private boolean _hasPlayer;
	private boolean _hasBox;
	
	/**
	 * construct a Wall cell
	 */
	Cell()
	{
	
	}
	
	/**
	 * construct a Floor cell
	 */
	Cell(boolean isStorage, boolean hasPlayer, boolean hasBox)
	{
		_isFloor=true;
		_isStorage=isStorage;
		_hasPlayer=hasPlayer;
		_hasBox=hasBox;
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
}