package jgogears;

import java.io.IOException;

/**
 * An incomplete clone of the TwoGTP program included in the GnuGo distribution. It runs a go game between a pair of GTP-capiable players
 * 
 * TODO finish this implementation
 * 
 * @author syeates
 */

public class TwoGTP {
	

/** The black player. */
	private GTPInterface black = null;

/** The white player. */
	private GTPInterface white = null;
	
	private GTPState state = new GTPState();
	
	public static final boolean DEBUG = true;
	
	/**
	 * Run the game. Assumes that the black and white players have already been set up.
	 * 
	 * @return true, if play out game
	 */

	public GTPState playOutGame() {
		int passes = 0;
		boolean blackNext = true;
		if (black == null)
			throw new Error();
		if (white == null)
			throw new Error();
		

		while (passes < 4) {
			Move move = null;
			if (blackNext) {
				move = this.black.genMove(BoardI.VERTEX_BLACK, this.state);
				assert (move != null);
				if (move.getPass())
					passes++;
				else
					passes = 0;
				blackNext = false;
			} else {
				move = this.white.genMove(BoardI.VERTEX_WHITE, this.state);
				assert (move != null);
				if (move.getPass())
					passes++;
				else
					passes = 0;
				blackNext = true;
			}
			if (DEBUG)
				System.err.println("TwoGTP: played " + move);
		this.state.playMove(move);
		if (DEBUG)
			System.err.println("TwoGTP: played " + move);
		if (DEBUG)
			System.err.println(state.getBoard());
		}
		if (DEBUG)
		System.err.println(this.black.getFinalScore(state));
		if (DEBUG)
		System.err.println(this.white.getFinalScore(state));

		return state;
	}

	/**
	 * get the black
	 * @return the black
	 */
	public final GTPInterface getBlack() {
		return black;
	}

	/**
	 * set the black
	 * @param black the black to set
	 */
	public final void setBlack(GTPInterface black) {
		this.black = black;
	}

	/**
	 * get the white
	 * @return the white
	 */
	public final GTPInterface getWhite() {
		return white;
	}

	/**
	 * set the white
	 * @param white the white to set
	 */
	public final void setWhite(GTPInterface white) {
		this.white = white;
	}

	/**
	 * get the currentBoard
	 * @return the currentBoard
	 */
	public final BoardI getCurrentBoard() {
		return this.state.getBoard();
	}

}
