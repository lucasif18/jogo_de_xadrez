package bordgame;

public class Piece {
	
	protected Posicao posicao;
	protected Board board;
	

	public Piece(Board board) {
		this.board = board;
		posicao =null;
	}





	protected Board getBoard() {
		return board;
	}


}
