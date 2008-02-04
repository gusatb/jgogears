package jgogears;

import java.util.*;

/**
 * various utilities to help parsing GTP output
 * 
 * @author syeates
 * 
 */
public class GTPParserUtils {
	/**
	 * Takes a string returned from the GTP protocol and returns either an Error
	 * describing the error in the string or a null if this is not an error
	 * 
	 * @return the error represented by this string
	 */
	static public Error getError(String s) {
		if (s == null)
			return null;
		if (s.length() < 1)
			return null;
		if (s.charAt(0) == ' ')
			return getError(s.substring(1));
		if (s.charAt(0) == '?') {
			int i;
			for (i = 1; s.length() > i && Character.isDigit(s.charAt(i)); i++)
				;
			return new Error(s.substring(i));
		}
		return null;
	}

	// TODO make this mildy efficient by not creating a billion temp orary
	// strings.
	// probably use a stringbuffer
	static String stripIntro(String s) {
		if (s == null)
			return null;
		if (s.length() == 0)
			return s;
		// strip leading whitespace
		while (s.length() != 0 && s.charAt(0) == ' ') {
			s = s.substring(1);
		}
		// strip the equal sign
		while (s.length() != 0 && s.charAt(0) == '=') {
			s = s.substring(1);
		}
		// strip the number
		while (s.length() != 0 && Character.isDigit(s.charAt(0))) {
			s = s.substring(1);
		}
		// strip leading whitespace
		while (s.length() != 0 && s.length() != 0 && s.charAt(0) == ' ') {
			s = s.substring(1);
		}
		if (s.length() == 0)
			return s;
		if (s.charAt(0) == '?') {
			throw getError(s);
		}

		return s;
	}

	static TreeSet<Vertex> parseVertexList(String s) {
		s = stripIntro(s);
		TreeSet<Vertex> vert = new TreeSet<Vertex>();
		if (s != null && s.length() != 0) {

			do {
				// System.err.println("parseVertexList(\"" + s + "\")");
				Vertex v = null;
				short row = -1;
				short column = -1;
				while (s.indexOf(' ') == 1 || s.indexOf(' ') == 0) {
					s = s.substring(1);
				}
				if (s.indexOf(' ') != -1) {
					v = new Vertex(s.substring(0, s.indexOf(' ')));
					s = s.substring(s.indexOf(' '));
				} else {
					v = new Vertex(s);
					s = "";
				}

				vert.add(v);
			} while (s.length() > 0);
		}
		return vert;
	}

}