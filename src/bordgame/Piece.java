package bordgame;

public abstract class Piece {
	
	protected Posicao posicao;
	protected Board board;
	

	public Piece(Board board) {
		this.board = board;
		posicao =null;
	}

	protected Board getBoard() {
		return board;
	}
	
	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMoves(Posicao posicao) {
		return possibleMoves()[posicao.getLinha()][posicao.getColuna()];
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean [][] mat = possibleMoves();
		for (int i=0; i<mat.length; i++) {
			for (int j=0; j<mat.length; j++) {
				if (mat[i][j]) {
					return true;
				}
				
			}
		}
		return false;
	}


}
