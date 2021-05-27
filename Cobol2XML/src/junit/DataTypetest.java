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
		Tokenizer t1 = CobolParser.tokenizer();
		Parser p = CobolParser.start();
		t.setString("01  entry_char   redefines entry_number pic x(16).");
		t1.setString("fghnbmhj && £@");
		Assembly in = new TokenAssembly(t);
		Assembly out = p.bestMatch(in);
		
		Assembly in1 = new TokenAssembly(t1);
		Assembly out1 = p.bestMatch(in1);
		
		assertTrue( out.elementsConsumed() != 0 );	
		assertFalse( out1.elementsConsumed() != 0 );	}

	}


