package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import cobol.CobolParser;
import parse.Assembly;
import parse.Parser;
import parse.tokens.TokenAssembly;
import parse.tokens.Tokenizer;

public class CommentTest {

	@Test
	public void test() {
		Tokenizer t = CobolParser.tokenizer();
		Tokenizer t1 = CobolParser.tokenizer();
		Tokenizer t2 = CobolParser.tokenizer();

		Parser p = CobolParser.start();
		t.setString("***---  convert from base to dicimal system");
		t1.setString("***---");
		t2.setString("**-- sdfghjk hgjkl");
		
		Assembly in = new TokenAssembly(t);
		Assembly out = p.bestMatch(in);
		
		Assembly in1 = new TokenAssembly(t1);
		Assembly out1 = p.bestMatch(in1);
		
		Assembly in2 = new TokenAssembly(t2);
		Assembly out2 = p.bestMatch(in2);
		
		

		assertTrue( out.elementsConsumed() != 0 );	
		assertFalse( out1.elementsConsumed() != 0 );	
		assertFalse( out2.elementsConsumed() != 0 );	
	
	
	}
	}


