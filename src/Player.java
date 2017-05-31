/**
 * Represents a player and how he can move
 */
class Player
{
	static int playerLocationX, playerLocationY;
	
	/**
	 * Moves the player one square down if he can move
	 * @param board needed only for refreshing the board
	 */
	static void MoveDown(Board board)
	{
		board.setPlayerState(PlayerState.FRONT);
		if (playerLocationY+1<board.board.length &&
		    board.board[playerLocationY+1][playerLocationX].isFloor())
			if (board.board[playerLocationY+1][playerLocationX].hasBox())
			{
				if (playerLocationY+2<board.board.length &&
				    board.board[playerLocationY+2][playerLocationX].isFloor() &&
				    !board.board[playerLocationY+2][playerLocationX].hasBox())
				{
					board.board[playerLocationY++][playerLocationX].set_hasPlayer(false);
					board.board[playerLocationY][playerLocationX].set_hasPlayer(true);
					board.board[playerLocationY++][playerLocationX].set_hasBox(false);
					board.board[playerLocationY][playerLocationX].set_hasBox(true);
					board.refreshBoard();
				}
			}
			else
			{
				board.board[playerLocationY++][playerLocationX].set_hasPlayer(false);
				board.board[playerLocationY][playerLocationX].set_hasPlayer(true);
				board.refreshBoard();
			}
	}
	
	/**
	 * Moves the player one square up if he can move
	 * @param board needed only for refreshing the board
	 */
	static void MoveUp(Board board)
	{
		board.setPlayerState(PlayerState.BACK);
		if (playerLocationY-1<board.board.length &&
		    board.board[playerLocationY-1][playerLocationX].isFloor())
			if (board.board[playerLocationY-1][playerLocationX].hasBox())
			{
				if (playerLocationY-2<board.board.length &&
				    board.board[playerLocationY-2][playerLocationX].isFloor() &&
				    !board.board[playerLocationY-2][playerLocationX].hasBox())
				{
					board.board[playerLocationY--][playerLocationX].set_hasPlayer(false);
					board.board[playerLocationY][playerLocationX].set_hasPlayer(true);
					board.board[playerLocationY--][playerLocationX].set_hasBox(false);
					board.board[playerLocationY][playerLocationX].set_hasBox(true);
					board.refreshBoard();
				}
			}
			else
			{
				board.board[playerLocationY--][playerLocationX].set_hasPlayer(false);
				board.board[playerLocationY][playerLocationX].set_hasPlayer(true);
				board.refreshBoard();
			}
	}
	
	/**
	 * Moves the player one square left if he can move
	 * @param board needed only for refreshing the board
	 */
	static void MoveLeft(Board board)
	{
		board.setPlayerState(PlayerState.LEFT);
		if (playerLocationX-1<board.board[playerLocationY].length &&
		    board.board[playerLocationY][playerLocationX-1].isFloor())
			if (board.board[playerLocationY][playerLocationX-1].hasBox())
			{
				if (playerLocationX-2<board.board[playerLocationY].length &&
				    board.board[playerLocationY][playerLocationX-2].isFloor() &&
				    !board.board[playerLocationY][playerLocationX-2].hasBox())
				{
					board.board[playerLocationY][playerLocationX--].set_hasPlayer(false);
					board.board[playerLocationY][playerLocationX].set_hasPlayer(true);
					board.board[playerLocationY][playerLocationX--].set_hasBox(false);
					board.board[playerLocationY][playerLocationX].set_hasBox(true);
					board.refreshBoard();
				}
			}
			else
			{
				board.board[playerLocationY][playerLocationX--].set_hasPlayer(false);
				board.board[playerLocationY][playerLocationX].set_hasPlayer(true);
				board.refreshBoard();
			}
	}
	
	/**
	 * Moves the player one square right if he can move
	 * @param board needed only for refreshing the board
	 */
	static void MoveRight(Board board)
	{
		board.setPlayerState(PlayerState.RIGHT);
		if (playerLocationX-1<board.board[playerLocationY].length &&
		    board.board[playerLocationY][playerLocationX+1].isFloor())
			if (board.board[playerLocationY][playerLocationX+1].hasBox())
			{
				if (playerLocationX-2<board.board[playerLocationY].length &&
				    board.board[playerLocationY][playerLocationX+2].isFloor() &&
				    !board.board[playerLocationY][playerLocationX+2].hasBox())
				{
					board.board[playerLocationY][playerLocationX++].set_hasPlayer(false);
					board.board[playerLocationY][playerLocationX].set_hasPlayer(true);
					board.board[playerLocationY][playerLocationX++].set_hasBox(false);
					board.board[playerLocationY][playerLocationX].set_hasBox(true);
					board.refreshBoard();
				}
			}
			else
			{
				board.board[playerLocationY][playerLocationX++].set_hasPlayer(false);
				board.board[playerLocationY][playerLocationX].set_hasPlayer(true);
				board.refreshBoard();
			}
	}
}