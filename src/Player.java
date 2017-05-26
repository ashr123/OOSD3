public class Player
{
	static int playerLocationX, playerLocationY;
	
	static void MoveDown(Board board)
	{
		Board.playerState=PlayerState.FRONT;
		if (playerLocationY+1<Board.board.length)
			if (Board.board[playerLocationY+1][playerLocationX].isFloor())
				if (Board.board[playerLocationY+1][playerLocationX].hasBox())
				{
					if (playerLocationY+2<Board.board.length)
						if (Board.board[playerLocationY+2][playerLocationX].isFloor() &&
						    !Board.board[playerLocationY+2][playerLocationX].hasBox())
						{
							Board.board[playerLocationY++][playerLocationX].set_hasPlayer(false);
							Board.board[playerLocationY][playerLocationX].set_hasPlayer(true);
							Board.board[playerLocationY++][playerLocationX].set_hasBox(false);
							Board.board[playerLocationY][playerLocationX].set_hasBox(true);
						}
				}
				else
				{
					Board.board[playerLocationY++][playerLocationX].set_hasPlayer(false);
					Board.board[playerLocationY][playerLocationX].set_hasPlayer(true);
				}
		board.RefreshBoard();
	}
	
	static void MoveUp(Board board)
	{
		Board.playerState=PlayerState.BACK;
		if (playerLocationY-1<Board.board.length)
			if (Board.board[playerLocationY-1][playerLocationX].isFloor())
				if (Board.board[playerLocationY-1][playerLocationX].hasBox())
				{
					if (playerLocationY+2<Board.board.length)
						if (Board.board[playerLocationY-2][playerLocationX].isFloor() &&
						    !Board.board[playerLocationY-2][playerLocationX].hasBox())
						{
							Board.board[playerLocationY--][playerLocationX].set_hasPlayer(false);
							Board.board[playerLocationY][playerLocationX].set_hasPlayer(true);
							Board.board[playerLocationY--][playerLocationX].set_hasBox(false);
							Board.board[playerLocationY][playerLocationX].set_hasBox(true);
						}
				}
				else
				{
					Board.board[playerLocationY--][playerLocationX].set_hasPlayer(false);
					Board.board[playerLocationY][playerLocationX].set_hasPlayer(true);
				}
		board.RefreshBoard();
	}
	
	static void MoveLeft(Board board)
	{
		Board.playerState=PlayerState.LEFT;
		if (playerLocationX-1<Board.board[playerLocationY].length)
			if (Board.board[playerLocationY][playerLocationX-1].isFloor())
				if (Board.board[playerLocationY][playerLocationX-1].hasBox())
				{
					if (playerLocationX-2<Board.board[playerLocationY].length)
						if (Board.board[playerLocationY][playerLocationX-2].isFloor() &&
						    !Board.board[playerLocationY][playerLocationX-2].hasBox())
						{
							Board.board[playerLocationY][playerLocationX--].set_hasPlayer(false);
							Board.board[playerLocationY][playerLocationX].set_hasPlayer(true);
							Board.board[playerLocationY][playerLocationX--].set_hasBox(false);
							Board.board[playerLocationY][playerLocationX].set_hasBox(true);
						}
				}
				else
				{
					Board.board[playerLocationY][playerLocationX--].set_hasPlayer(false);
					Board.board[playerLocationY][playerLocationX].set_hasPlayer(true);
				}
		board.RefreshBoard();
	}
	
	static void MoveRight(Board board)
	{
		Board.playerState=PlayerState.RIGHT;
		if (playerLocationX-1<Board.board[playerLocationY].length)
			if (Board.board[playerLocationY][playerLocationX+1].isFloor())
				if (Board.board[playerLocationY][playerLocationX+1].hasBox())
				{
					if (playerLocationX-2<Board.board[playerLocationY].length)
						if (Board.board[playerLocationY][playerLocationX+2].isFloor() &&
						    !Board.board[playerLocationY][playerLocationX+2].hasBox())
						{
							Board.board[playerLocationY][playerLocationX++].set_hasPlayer(false);
							Board.board[playerLocationY][playerLocationX].set_hasPlayer(true);
							Board.board[playerLocationY][playerLocationX++].set_hasBox(false);
							Board.board[playerLocationY][playerLocationX].set_hasBox(true);
						}
				}
				else
				{
					Board.board[playerLocationY][playerLocationX++].set_hasPlayer(false);
					Board.board[playerLocationY][playerLocationX].set_hasPlayer(true);
				}
		board.RefreshBoard();
	}
}