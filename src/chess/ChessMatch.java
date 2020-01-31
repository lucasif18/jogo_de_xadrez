package chess;

		//regras do jogo
import bordgame.Board;
import chess.pieces.Rei;
import chess.pieces.Torre;

//partida de xadrez
public class ChessMatch {
	
	private Board board;
	
	public ChessMatch() {
		board = new Board(8,8);
		initialSetup();
	}
	
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getLinhas()][board.getColunas()];
		for (int i=0; i<board.getLinhas(); i++) {
			for(int j=0; j<board.getColunas(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
				
			}
		}
		return mat;
	}
	
	private void placeNewPiece(char coluna, int linha, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(coluna, linha).toPosition());
	}
	
	private void initialSetup() {
		placeNewPiece('b',6, new Torre(board, Color.WHITE));
		placeNewPiece('e',8, new Rei(board, Color.BLACK));
		placeNewPiece('e',1, new Rei(board, Color.WHITE));
	}
	
	
	
	
}
