package chess;

import bordgame.Board;
import bordgame.Piece;
import bordgame.Posicao;


public abstract class ChessPiece  extends Piece{
	
	private Color color;
	private int moveCount;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	
	public void increaseMoveCount() {
		moveCount++;
	}
	
	public void decreaseMoveCount() {
		moveCount--;
	}
	
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(posicao);
	}
	
	protected boolean isThereOpponentPiece(Posicao posicao) {
		ChessPiece p = (ChessPiece)getBoard().piece(posicao);
		return p != null && p.getColor() != color;
	}


	
	

}
