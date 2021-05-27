package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import cobol.CobolParser;
import parse.Assembly;
import parse.Parser;
import parse.tokens.TokenAssembly;
import parse.tokens.Tokenizer;

public class RegressionTest {
	
	@Test
	public void test() {
		Tokenizer t = CobolParser.tokenizer();
		Tokenizer t1 = CobolParser.tokenizer();
		Tokenizer t2 = CobolParser.tokenizer();

		Parser p = CobolParser.start();
		t.setString("date-written.  07-mar-1995 - mb.");
		t1.setString("date-written mar-1995-07 - mb.");
		t2.setString("07-mar-1995");
		
		Assembly in = new TokenAssembly(t);
		Assembly out = p.bestMatch(in);
		
		Assembly in1 = new TokenAssembly(t1);
		Assembly out1 = p.bestMatch(in1);
		
		Assembly in2 = new TokenAssembly(t2);
		Assembly out2 = p.bestMatch(in2);
		
		Tokenizer t3 = CobolParser.tokenizer();
		Tokenizer t4 = CobolParser.tokenizer();
		
		t3.setString("program-id.  base.");
		t4.setString("program-id.");
		
		Assembly in3 = new TokenAssembly(t3);
		Assembly out3 = p.bestMatch(in3);
		Assembly in4 = new TokenAssembly(t4);
		Assembly out4 = p.bestMatch(in4);
		
		Tokenizer t5 = CobolParser.tokenizer();
		Tokenizer t6 = CobolParser.tokenizer();
		
		t5.setString("identification division.");
		t6.setString("identification");
		
		Assembly in5 = new TokenAssembly(t5);
		Assembly out5 = p.bestMatch(in5);
		Assembly in6 = new TokenAssembly(t6);
		Assembly out6 = p.bestMatch(in6);

		assertTrue( out.elementsConsumed() != 0 );	
		assertFalse( out1.elementsConsumed() != 0 );	
		assertFalse( out2.elementsConsumed() != 0 );	
	
		assertTrue( out3.elementsConsumed() != 0 );	
		assertFalse( out4.elementsConsumed() != 0 );	
		
		assertTrue( out5.elementsConsumed() != 0 );	
		assertFalse( out6.elementsConsumed() != 0 );	
	}
	}


