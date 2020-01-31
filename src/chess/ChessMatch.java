package chess;

		//regras do jogo
import bordgame.Board;
import bordgame.Posicao;
import chess.pieces.Rei;
import chess.pieces.Torre;

//partida de zadrez
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
	private void initialSetup() {
		board.placePiece(new Torre(board, Color.WHITE), new Posicao(2,1));
		board.placePiece(new Rei(board, Color.BLACK), new Posicao(0,4));
		board.placePiece(new Rei(board, Color.WHITE), new Posicao(7,4));
	}
	
	
	
	
}
