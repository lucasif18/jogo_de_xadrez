package chess.pieces;

import bordgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Torre extends ChessPiece{

	public Torre(Board board, Color color) {
		super(board, color);
		
	}
	
	@Override
	public String toString() {
		return "T";
	}

}
