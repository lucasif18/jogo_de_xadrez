package chess.pieces;

import bordgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rei  extends ChessPiece{

	public Rei(Board board, Color color) {
		super(board, color);
		
	}
	
	@Override
	public String toString() {
		return "R";
	}

}
