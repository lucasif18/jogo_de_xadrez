package chess;

import bordgame.Board;
import bordgame.Piece;
import bordgame.Posicao;


public abstract class ChessPiece  extends Piece{
	
	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	protected boolean isThereOpponentPiece(Posicao posicao) {
		ChessPiece p = (ChessPiece)getBoard().piece(posicao);
		return p != null && p.getColor() != color;
	}


	
	

}
