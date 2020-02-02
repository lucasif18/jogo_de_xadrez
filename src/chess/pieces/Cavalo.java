package chess.pieces;

import bordgame.Board;
import bordgame.Posicao;
import chess.ChessPiece;
import chess.Color;

public class Cavalo extends ChessPiece {

	public Cavalo(Board board, Color color) {
		super(board, color);

	}

	@Override
	public String toString() {
		return "C";
	}

	private boolean canMove(Posicao posicao) {
		ChessPiece p = (ChessPiece) getBoard().piece(posicao);
		return p == null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getLinhas()][getBoard().getColunas()];

		Posicao p = new Posicao(0, 0);

		
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() - 2);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		p.setValues(posicao.getLinha() - 2, posicao.getColuna() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		p.setValues(posicao.getLinha() - 2, posicao.getColuna() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() + 2);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() + 2);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		p.setValues(posicao.getLinha() + 2, posicao.getColuna() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		p.setValues(posicao.getLinha() + 2, posicao.getColuna() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		
		p.setValues(posicao.getLinha() + 2, posicao.getColuna() - 2);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		return mat;
	}

}