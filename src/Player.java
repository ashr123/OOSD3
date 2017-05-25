import java.io.IOException;

public class Player
{
	static int playerLocationX, playerLocationY;
	
	static void MoveDown(Board board) throws IOException
	{
		Board.playerState=PlayerState.BACK;
		if (playerLocationY+1<Board.board.length)
			if (Board.board[playerLocationY+1][playerLocationX].isFloor())
				if (Board.board[playerLocationY+1][playerLocationX].hasBox())
					if (playerLocationY+2<Board.board.length)
						if (Board.board[playerLocationY+2][playerLocationX].isFloor() && !Board.board[playerLocationY+2][playerLocationX].hasBox())
						{
							Board.board[playerLocationY++][playerLocationX].set_hasPlayer(false);
							Board.board[playerLocationY][playerLocationX].set_hasPlayer(true);
							Board.board[playerLocationY++][playerLocationX].set_hasBox(false);
							Board.board[playerLocationY][playerLocationX].set_hasBox(true);
						}
		board.RefreshBoard();
	}
	
	static void MoveUp()
	{
	
	}
	
	static void MoveLeft()
	{
	
	}
	
	static void MoveRight()
	{
	
	}
}