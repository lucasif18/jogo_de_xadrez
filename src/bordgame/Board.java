package bordgame;

public class Board {
	
	private int linhas;
	private int colunas;
	private Piece [][] pieces;
	
	public Board(int linhas, int colunas) {
		if (linhas <1 || colunas <1) {
		throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pieces = new Piece [linhas][colunas];
		
	}
	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}
	public Piece piece(int linha, int coluna) {
		if (!positionExists(linha, coluna)) {
			throw new BoardException("Position not on the board");
		
	}
		return pieces[linha][coluna];
	}
	
	public Piece piece (Posicao posicao) {
		if (!positionExists(posicao)) {
			throw new BoardException("Position not on the board");
		
	}
		return pieces[posicao.getLinha()][posicao.getColuna()];
	}
	
	
	public void placePiece (Piece piece, Posicao posicao) {
		if(thereIsAPiece(posicao)) {
			throw new BoardException("There is already a piece on position" + posicao);
		}
		pieces[posicao.getLinha()][posicao.getColuna()] = piece;
		piece.posicao = posicao;
	}
	
	public Piece removePiece(Posicao posicao) {
		if(!positionExists(posicao)) {
			throw new BoardException("Position not on the board");
		}
		if(piece(posicao) == null) {
			return null;
		}
		Piece aux = piece(posicao);
		aux.posicao = null;
		pieces[posicao.getLinha()][posicao.getColuna()] = null;
		return aux;
	}
	
	
	
	
	
	private boolean positionExists(int linha, int coluna) {
		return linha >=0 && linha < linhas && coluna >=0 &&coluna < colunas;
	}
	
	
	public boolean positionExists(Posicao posicao) {
		return positionExists(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean thereIsAPiece(Posicao posicao) {
		if (!positionExists(posicao)) {
			throw new BoardException("Position not on the board");
		
	}
		return piece(posicao) != null;
	}

	
	
}
