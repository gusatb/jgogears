package jgogears.engine;

import java.io.StringReader;
import java.util.Iterator;

import jgogears.*;
import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
/**
 * The Class VertexLineariserTest.
 */
public class VertexLineariserTest extends TestCase {

	/*
	 * Test method for 'jgogears.SGFNode.columnFromMoveString(String)'
	 */
	/**
	 * Test everything.
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public void testEverything() throws Exception {

		String examples[] = {
		// SGFParser.EXAMPLEONE,
				SGFParser.EXAMPLETWO, SGFParser.EXAMPLETHREE, SGFParser.EXAMPLEFOUR, SGFParser.EXAMPLEFIVE };

		for (int i = 0; i < examples.length; i++) {
			String example = examples[i];

			StringReader reader = new StringReader(example);
			jgogears.SGF.SGF parser = new jgogears.SGF.SGF(reader);
			SGFGameTree tree = parser.gameTree();

			assertTrue(parser != null);
			assertTrue(parser.toString() != null);
			assertTrue(parser.toString().length() > 0);

			assertTrue(tree != null);
			assertTrue(tree.toString() != null);
			assertTrue(tree.toString().length() > 0);

			Game game = new Game(tree);
			assertTrue(game != null);

			Iterator<BoardI> iterator = game.getBoards();
			assertTrue(iterator != null);

			BoardI board = null;
			while (iterator.hasNext()) {
				board = iterator.next();
				assertTrue(board != null);
				for (short j = 0; j < 8; j++) {
					Iterator<Short> linear = new VertexLineariser(board, (short) 2, (short) 2, j, false);
					assertTrue(linear != null);
					while (linear.hasNext()) {
						Short s = linear.next();
						assertTrue(s != null);
						// System.out.print(" " + s + ", ");
					}
				}
				// System.out.println();
			}

		}

	}

}
