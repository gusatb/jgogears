package jgogears;

import java.util.*;

import jgogears.Move;

public class RandomEngine extends SkeletonEngine implements GTPInterface {

	public Random random = new Random(0);

	public Move[] finalStatusList(String status, GTPState state) {

		// TODO
		return null;
	}

	public String getEngineName() {
		return "Mii / Barrotts Reef";
	}

	public String getEngineVersion() {
		return "00.001" + new Date();
	}

	public Move regGenMove(int colour, GTPState state) {
		// TODO make sure this is a _legal_ move
		short row;
		short column;
		do {
			row = (short) random.nextInt(state.boardsize);
			column = (short) random.nextInt(state.boardsize);
		} while (state.board.getColour(row, column) == BoardI.VERTEX_EMPTY);
		return new Move(row, column, colour);
	}

	public GTPScore score(GTPState state) {
		// TODO Auto-generated method stub
		return new GTPScore("?");
	}

	public GTPScore getFinalScore(GTPState state) {
		// TODO
		return new GTPScore("?");
	}

}
