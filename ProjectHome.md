jgogears is a set of utility classes for the Asian strategy game [Go (also called igo, baduk, and weiqi)](http://senseis.xmp.net/). Go is a little like chess, only the rules are somewhat simpler but played on a larger board. While chess is a single-target game (to capture the opponents king before they capture yours) Go is a territory game, with every move trading off control of territory in one part of the board for territory in another.

jgogears has classes for [SGF file](http://www.red-bean.com/sgf/) parsing; [Zobrist hashing](http://computer-go.org/pipermail/computer-go/2005-November/004129.html); interfacing with [GnuGo](http://www.gnu.org/software/gnugo/) via [Go Text Protocol GTP](http://www.lysator.liu.se/~gunnar/gtp/) ; etc. There are unit tests for many of the classes and functions.

jgogears currently has (and there are no plans to add) a GUI or any [Monte-Carlo / UCT-style](http://senseis.xmp.net/?UCT) statistical computer-go players. There are many other open source projects attempting these. For discussion of computer-go and computer-go players, I use and recommend the [computer-go mailing list](http://computer-go.org/mailman/listinfo/computer-go) it's a robust community of people working in this area.

If you came here looking for a computer-go player to play, I recommend [GnuGo](http://www.gnu.org/software/gnugo/download.html). For improving your go, playing real people is usually considered better than playing a computer-go player (since computer-go players tend to have a single style and very clear and specific weaknesses) if  there is no go club near you, try a [go server](http://www.britgo.org/gopcres/play.html).


---


Right now jgogears is not very far along in it's development. There are two components which are reasonably mature:

  * An SGF parser written in [JavaCC](https://javacc.dev.java.net/) (a Java compiler-compiler a little like lex and yacc) which parses SGF files very quickly.
  * A Board representation using bit manipulation (java.util.BitSet).

There are a number of unit tests, and coverage is particularly comprehensive for the board representation.

There is still some code reorganization to be done as part of the uploading to google code.

There is a [TODO file](http://jgogears.googlecode.com/svn/trunk/jgogears/doc/TODO) in svn listing the current state of play