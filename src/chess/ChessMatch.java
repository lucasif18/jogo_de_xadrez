package chess;

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
		nextTurn();
		return (ChessPiece)capturedPiece;
	}
	
	private Piece makeMove(Posicao source, Posicao target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		return capturedPiece;
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
	
	
	private void placeNewPiece(char coluna, int linha, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(coluna, linha).toPosition());
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
