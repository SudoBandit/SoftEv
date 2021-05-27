package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import cobol.CobolParser;
import parse.Assembly;
import parse.Parser;
import parse.tokens.TokenAssembly;
import parse.tokens.Tokenizer;

public class DataTypetest {

	@Test
	public void test() {
		Tokenizer t = CobolParser.tokenizer();
		Parser p = CobolParser.start();
		t.setString("01  entry_char   redefines entry_number pic x(16).");
		//t.setString("fghnbmhj && £@");
		Assembly in = new TokenAssembly(t);
		Assembly out = p.bestMatch(in);
		assertTrue( out.elementsConsumed() != 0 );	}
	}


