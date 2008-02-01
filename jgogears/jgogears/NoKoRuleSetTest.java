package jgogears;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Stack;
import java.util.TreeSet;

import junit.framework.TestCase;
import jgogears.*;
import jgogears.engine.Model;

public class NoKoRuleSetTest extends TestCase {
	
	static final boolean DEBUG= true;

	public void testinherits() {
		NoKoRuleSet rule = new NoKoRuleSet();
		assertNotNull(rule);
		RuleSet rule2 = rule;
		assertNotNull(rule2);
	}

	public void testStringEmptyBoard() {
		NoKoRuleSet rule = new NoKoRuleSet();
		short size = 7;
		Board board = new Board(size);

		for (short i = 0; i < size; i++)
			for (short j = 0; j < size; j++) {
				TreeSet<Vertex> string = rule.getString(i, j, board);
				assertNotNull(string);
				assertTrue("" + i + " / " + j + " / " + string.size() + " / "
						+ size * size + " (" + string + ")",
						string.size() == size * size);
			}
	}

	public void testStringOffBoard() {
		NoKoRuleSet rule = new NoKoRuleSet();
		short size = 7;
		Board board = new Board(size);

		TreeSet<Vertex> string = rule.getString((short) -1, (short) -1, board);
		assertNotNull(string);
		assertTrue(string.size() == 0);

		string = rule.getString((short) 0, (short) -1, board);
		assertNotNull(string);
		assertTrue(string.size() == 0);
		string = rule.getString((short) 15, (short) -1, board);
		assertNotNull(string);
		assertTrue(string.size() == 0);
		string = rule.getString((short) -15, (short) -15, board);
		assertNotNull(string);
		assertTrue(string.size() == 0);
	}

	public void testStringAlmostEmptyBoard() {
		NoKoRuleSet rule = new NoKoRuleSet();
		short size = 5;

		for (short i = 0; i < size; i++)
			for (short j = 0; j < size; j++)
				for (short k = 0; k < size; k++)
					for (short l = 0; l < size; l++) {
						Board board = new Board(size);
						assertNotNull(board);
						Move move = new Move(k, l, Board.VERTEX_BLACK);
						assertNotNull(move);
						assertTrue(move.getPlay());
						assertTrue(move.getRow() == k);
						assertTrue(move.getColour() == Board.VERTEX_BLACK);
						assertTrue(move.getColumn() == l);

						board = board.newBoard(move);
						assertNotNull(board);

						TreeSet<Vertex> string = rule.getString(i, j, board);
						assertNotNull(string);
						if (i != k || j != l)
							assertTrue("" + i + " / " + j + " / " + k + " / "
									+ l + " / " + string.size() + " / "
									+ (size * size - 1) + " (" + string + ")",
									string.size() == size * size - 1);
						else
							assertTrue("" + i + " / " + j + " / " + k + " / "
									+ l + " / " + string.size() + " / " + size
									* size + " (" + string + ")",
									string.size() == 1);

					}
	}

	public void testStringpair() {
		NoKoRuleSet rule = new NoKoRuleSet();
		short size = 7;

		Board board = new Board(size);
		assertNotNull(board);
		Move move = new Move(1, 1, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(1, 2, Board.VERTEX_WHITE);
		board = board.newBoard(move);

		TreeSet<Vertex> string = rule.getString(1, 1, board);
		assertTrue(string.size() == 1);
		string = rule.getString(1, 2, board);
		assertTrue(string.size() == 1);
		string = rule.getString(0, 0, board);
		assertTrue(string.size() == size * size - 2);

	}

	public void testStringpairsame() {
		NoKoRuleSet rule = new NoKoRuleSet();
		short size = 7;

		Board board = new Board(size);
		assertNotNull(board);
		Move move = new Move(1, 1, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(2, 2, Board.VERTEX_BLACK);
		board = board.newBoard(move);

		TreeSet<Vertex> string = rule.getString(1, 1, board);
		assertTrue(string.size() == 1);
		string = rule.getString(2, 2, board);
		assertTrue(string.size() == 1);
		string = rule.getString(0, 0, board);
		assertTrue(string.size() == size * size - 2);

	}

	public void testStringpairnext() {
		NoKoRuleSet rule = new NoKoRuleSet();
		short size = 7;

		Board board = new Board(size);
		assertNotNull(board);
		Move move = new Move(1, 1, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(1, 2, Board.VERTEX_BLACK);
		board = board.newBoard(move);

		TreeSet<Vertex> string = rule.getString(1, 1, board);
		assertTrue(string.size() == 2);
		string = rule.getString(1, 2, board);
		assertTrue(string.size() == 2);
		string = rule.getString(0, 0, board);
		assertTrue(string.size() == size * size - 2);

	}

	public void testStringrow() {
		NoKoRuleSet rule = new NoKoRuleSet();
		short size = 7;

		Board board = new Board(size);
		assertNotNull(board);
		Move move = new Move(1, 0, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(1, 1, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(1, 2, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(1, 3, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(1, 4, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(1, 5, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(1, 6, Board.VERTEX_BLACK);
		board = board.newBoard(move);

		TreeSet<Vertex> string = rule.getString(1, 1, board);
		assertTrue(string.size() == 7);
		string = rule.getString(1, 0, board);
		assertTrue(string.size() == 7);
		string = rule.getString(0, 0, board);
		assertTrue(string.size() == 7);
		string = rule.getString(6, 6, board);
		assertTrue(string.size() == size * (size - 2));

	}

	public void testLibertiesAlmostEmptyBoard() {
		NoKoRuleSet rule = new NoKoRuleSet();
		short size = 13;

		for (short k = 0; k < size; k++)
			for (short l = 0; l < size; l++) {
				Board board = new Board(size);
				assertNotNull(board);
				Move move = new Move(k, l, Board.VERTEX_BLACK);
				assertNotNull(move);
				assertTrue(move.getPlay());
				assertTrue(move.getRow() == k);
				assertTrue(move.getColour() == Board.VERTEX_BLACK);
				assertTrue(move.getColumn() == l);

				board = board.newBoard(move);
				assertNotNull(board);

				TreeSet<Vertex> string = rule.getString(k, l, board);
				assertNotNull(string);
				assertTrue(string.size() == 1);
				TreeSet<Vertex> liberties = rule.getLiberties(k, l, board);
				assertNotNull(liberties);
				short count = rule.countLiberties(k, l, board);
				assertNotNull(count == liberties.size());
				if (k == 0 || k == size - 1)
					if (l == 0 || l == size - 1) {
						assertTrue(liberties.size() == 2);
					} else {
						assertTrue(liberties.size() == 3);
					}
				else if (l == 0 || l == size - 1) {
					assertTrue(liberties.size() == 3);
				} else {
					assertTrue(liberties.size() == 4);
				}

			}
	}

	public void testLibertiesrow() {
		NoKoRuleSet rule = new NoKoRuleSet();
		short size = 7;

		Board board = new Board(size);
		assertNotNull(board);
		Move move = new Move(1, 0, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(1, 1, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(1, 2, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(1, 3, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(1, 4, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(1, 5, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(1, 6, Board.VERTEX_BLACK);
		board = board.newBoard(move);

		TreeSet<Vertex> libs = rule.getLiberties(1, 1, board);
		assertTrue(libs.size() == 14);
		libs = rule.getLiberties(1, 0, board);
		assertTrue(libs.size() == 14);
	}

	public void testLibertiesCross() {
		NoKoRuleSet rule = new NoKoRuleSet();
		short size = 7;

		Board board = new Board(size);
		assertNotNull(board);
		Move move = new Move(3, 1, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(3, 2, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(3, 4, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(3, 5, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(1, 3, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(2, 3, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(4, 3, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(5, 3, Board.VERTEX_BLACK);
		board = board.newBoard(move);

		TreeSet<Vertex> libs = rule.getString(3, 3, board);
		assertTrue(libs.size() == 1);
		libs = rule.getLiberties(4, 3, board);
		assertTrue(libs.size() == 6);
		libs = rule.getLiberties(3, 4, board);
		assertTrue(libs.size() == 6);
	}
	public void testCornerPlays() {
		//System.err.println();
		//System.err.println();
		
		NoKoRuleSet rule = new NoKoRuleSet();
		short size = 7;
		Game game = new Game(19);
		Board board = new Board(size);
		Move move = new Move(0, 1, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(1, 1, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		
		move = new Move(0, 0, Board.VERTEX_WHITE);
		assertTrue(rule.moveIsLegal(game, board, move));
		assertTrue(rule.captures(game, board, move).size()== 0);
		board = board.newBoard(move);

		move = new Move(1, 0, Board.VERTEX_BLACK);
		assertTrue(rule.moveIsLegal(game, board, move));
		assertTrue(rule.countLiberties(0, 0, board)==1);
		assertTrue("" + rule.captures(game, board, move).size() + "/" + 1, rule.captures(game, board, move).size()== 1);
		board = board.newBoard(move);
		
		//System.err.println(board);

		move = new Move(2, 0, Board.VERTEX_WHITE);
		assertTrue(rule.moveIsLegal(game, board, move));
		assertTrue(rule.captures(game, board, move).size()== 0);
		board = board.newBoard(move);

		move = new Move(2, 1, Board.VERTEX_WHITE);
		assertTrue(rule.moveIsLegal(game, board, move));
		assertTrue(rule.captures(game, board, move).size()== 0);
		board = board.newBoard(move);

		move = new Move(0, 2, Board.VERTEX_WHITE);
		assertTrue(rule.moveIsLegal(game, board, move));
		assertTrue(rule.captures(game, board, move).size()== 0);
		board = board.newBoard(move);

		move = new Move(1, 2, Board.VERTEX_WHITE);
		assertTrue(rule.moveIsLegal(game, board, move));
		assertTrue(rule.captures(game, board, move).size()== 0);
		board = board.newBoard(move);

		//System.err.println(board);

		move = new Move(0, 0, Board.VERTEX_WHITE);
		assertTrue(rule.moveIsLegal(game, board, move));
		assertTrue(rule.captures(game, board, move).size()== 3);
		board = board.newBoard(move);
	}
	public void testCenterPlays() {
		
		NoKoRuleSet rule = new NoKoRuleSet();
		short size = 7;
		Board board = new Board(size);
		Move move = new Move(0, 1, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(1, 0, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(1, 2, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(2, 1, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		
		//System.err.println(board);
		move = new Move(1, 1, Board.VERTEX_WHITE);
		assertFalse(rule.moveIsLegal(null, board, move));

	}
	public void testCenterPlaysII() {
		
		NoKoRuleSet rule = new NoKoRuleSet();
		short size = 7;
		Board board = new Board(size);
		Move move = new Move(1, 2, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(2, 1, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(2, 3, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(3, 2, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		
		//System.err.println(board);
		move = new Move(2, 2, Board.VERTEX_WHITE);
		assertFalse(rule.moveIsLegal(null, board, move));

	}
	public void testCenterPlaysIII() {
		
		NoKoRuleSet rule = new NoKoRuleSet();
		short size = 7;
		Board board = new Board(size);
		Move move = new Move(1, 2, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(2, 1, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(2, 3, Board.VERTEX_BLACK);
		board = board.newBoard(move);
		move = new Move(2, 2, Board.VERTEX_WHITE);
		//System.err.println(board);
		assertTrue(rule.moveIsLegal(null, board, move));
		board = board.newBoard(move);
		
		move = new Move(3, 2, Board.VERTEX_BLACK);
		assertTrue(rule.moveIsLegal(null, board, move));
		assertTrue(rule.captures(null, board, move).size()== 1);
		board = board.newBoard(move);

		move = new Move(2, 2, Board.VERTEX_WHITE);
		assertFalse(rule.moveIsLegal(null, board, move));
		move = new Move(2, 2, Board.VERTEX_BLACK);
		assertTrue(rule.moveIsLegal(null, board, move));

		//System.err.println(board);
	}
	
	public void testCenterPlaysIIII() {
		
		NoKoRuleSet rule = new NoKoRuleSet();
		short size = 7;
		Board board = new Board(size);
		
		Move move = new Move(3, 3, Board.VERTEX_BLACK);
		assertTrue(rule.moveIsLegal(null, board, move));
		assertTrue(rule.captures(null, board, move).size()== 0);
		board = board.newBoard(move);
		assertTrue("" + rule.countLiberties(3, 3, board), rule.countLiberties(3, 3, board) == 4);
		
		move = new Move(2, 3, Board.VERTEX_BLACK);
		assertTrue(rule.moveIsLegal(null, board, move));
		assertTrue(rule.captures(null, board, move).size()== 0);
		board = board.newBoard(move);
		assertTrue("" + rule.countLiberties(2, 3, board), rule.countLiberties(3, 3, board) == 6);
		
		move = new Move(4, 3, Board.VERTEX_BLACK);
		assertTrue(rule.moveIsLegal(null, board, move));
		assertTrue(rule.captures(null, board, move).size()== 0);
		board = board.newBoard(move);
		assertTrue("" + rule.countLiberties(4, 3, board), rule.countLiberties(3, 3, board) == 8);
		
		move = new Move(3, 2, Board.VERTEX_BLACK);
		assertTrue(rule.moveIsLegal(null, board, move));
		assertTrue(rule.captures(null, board, move).size()== 0);
		board = board.newBoard(move);
		assertTrue("" + rule.countLiberties(3, 2, board), rule.countLiberties(3, 3, board) == 8);
		
		move = new Move(3, 4, Board.VERTEX_BLACK);
		assertTrue(rule.moveIsLegal(null, board, move));
		assertTrue(rule.captures(null, board, move).size()== 0);
		board = board.newBoard(move);
		assertTrue("" + rule.countLiberties(3, 4, board), rule.countLiberties(3, 3, board) == 8);
		
	}
	

	public void testLoadAllSGFfiles() throws IOException {
		Stack<String> files = new Stack<String>();
		files.push("sgf/2004-12");
		assertNotNull(files);
		Date before = new Date();
		assertNotNull(before);
		int filecount = 0;
		Model model = new Model();
		assertNotNull(model);
		RuleSet rule = new NoKoRuleSet();

		while (files.size() > 0 && filecount < 10) {
			String filename = files.pop();
			File file = new File(filename);
			if (file.exists()) {
				if (!file.isDirectory()) {
					// System.err.println("\"" + filename + "\" is not a
					// directory, parsing as an SGF file");
					filecount++;

					Game game = Game.loadFromFile(file);
					Board board = new Board(game.getSize());
					System.err.println(filename);

					Iterator<Move> i = game.getMovelist().iterator();
					while (i.hasNext()){
						Move move = i.next();
						if (!rule.moveIsLegal(game, board, move)){
							System.err.println(move);
							System.err.println(board);
							assertTrue(false);
						}
						board = board.newBoard(move);
					}
					

				} else {
					String[] children = file.list();
					for (int i = 0; i < children.length; i++) {
						files.push(filename + "/" + children[i]);
					}
				}
			}
		}
	}
}