package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//regras do jogo
import bordgame.Board;
import bordgame.Piece;
import bordgame.Posicao;
import chess.pieces.Rei;
import chess.pieces.Torre;

//partida de xadrez
public class ChessMatch {
	
	private int turn;
	private Color currentPlayer;
	private Board board;
	private boolean check;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();
	
	public ChessMatch() {
		board = new Board(8,8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
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
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		Posicao posicao = sourcePosition.toPosition();
		validateSourcePosition(posicao);
		return board.piece(posicao).possibleMoves();
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Posicao source = sourcePosition.toPosition();
		Posicao target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		
		if(testCheck(currentPlayer)){
			undoMove(source, target, capturedPiece);
			throw new ChessException("You cab't put yourself in check");
		}
		
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		
		nextTurn();
		return (ChessPiece)capturedPiece;
	}
	
	private Piece makeMove(Posicao source, Posicao target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		return capturedPiece;
	}
	
	private void undoMove(Posicao source, Posicao target, Piece capturedPiece) {
		Piece p = board.removePiece(target);
		board.placePiece(p, source);
		
		if(capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
			}
		}
	
	
	private void validateSourcePosition(Posicao posicao) {
		if (!board.thereIsAPiece(posicao)) {
			throw new ChessException("There is not piece on sours position");
		}
		if(currentPlayer != ((ChessPiece)board.piece(posicao)).getColor()){
			throw new ChessException("The chose piece is not yours");
		}
		if (!board.piece(posicao).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece");
			
		}
	}
	
		public void validateTargetPosition(Posicao source, Posicao target) {
			if (!board.piece(source).possibleMoves(target)){
				throw new ChessException("The chosen piece can't move to target position");
			}
		}
		
		private void nextTurn() {
			turn++;
			currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
		}
		
		private Color opponent(Color color) {
			return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
		}
		
		private ChessPiece Rei(Color color) {
			List<Piece> list = piecesOnTheBoard.stream().filter(x ->((ChessPiece)x).getColor()== color).collect(Collectors.toList());
			for (Piece p : list){
				if (p instanceof Rei) {
					return (ChessPiece)p;
				}
			}
			throw new IllegalStateException("There is no" + color + "King on the board");
		}
		
		private boolean testCheck(Color color) {
			Posicao ReiPosicao = Rei(color).getChessPosition().toPosition();
			List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x ->((ChessPiece)x).getColor()== opponent(color)).collect(Collectors.toList());
			for(Piece p : opponentPieces) {
				boolean [][] mat = p.possibleMoves();
				if(mat[ReiPosicao.getLinha()][ReiPosicao.getColuna()]) {
					return true;
				}
			}
			return false;
		
		}
		
		
	
	
	private void placeNewPiece(char coluna, int linha, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(coluna, linha).toPosition());
		piecesOnTheBoard.add(piece);
	}
	
	private void initialSetup() {
		placeNewPiece('c', 1, new Torre(board, Color.WHITE));
        placeNewPiece('c', 2, new Torre(board, Color.WHITE));
        placeNewPiece('d', 2, new Torre(board, Color.WHITE));
        placeNewPiece('e', 2, new Torre(board, Color.WHITE));
        placeNewPiece('e', 1, new Torre(board, Color.WHITE));
        placeNewPiece('d', 1, new Rei(board, Color.WHITE));

        placeNewPiece('c', 7, new Torre(board, Color.BLACK));
        placeNewPiece('c', 8, new Torre(board, Color.BLACK));
        placeNewPiece('d', 7, new Torre(board, Color.BLACK));
        placeNewPiece('e', 7, new Torre(board, Color.BLACK));
        placeNewPiece('e', 8, new Torre(board, Color.BLACK));
        placeNewPiece('d', 8, new Rei(board, Color.BLACK));
	}
	
	
	
	
}
