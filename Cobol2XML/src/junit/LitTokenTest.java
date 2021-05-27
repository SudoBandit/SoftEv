package junit;

import static org.junit.Assert.*;

import org.junit.Test;
import parse.tokens.Literal;
import parse.tokens.Token;

public class LitTokenTest {

	@Test
	public void test() {
		
		String a = "a";
		Literal t = new Literal(a);
		Token i = new Token("a");
		
		Integer b = 1;
		Literal t1 = new Literal(b);
		Token i1 = new Token(1);
		
		Double c = 1.05;
		Literal t2 = new Literal(c);
		Token i2 = new Token(1.05);
		
		String a1 = "a";
		Literal t3 = new Literal(a1);
		Token i3 = new Token("b");
		
		Integer b1 = 1;
		Literal t4 = new Literal(b1);
		Token i4 = new Token(12);
		
		Double c1 = 1.05;
		Literal t5 = new Literal(c1);
		Token i5 = new Token(1.12);
		
		assertTrue(t.qualifies(i));
		assertTrue(t1.qualifies(i1));
		assertTrue(t2.qualifies(i2));
		assertFalse(t3.qualifies(i3));
		assertFalse(t4.qualifies(i4));
		assertFalse(t5.qualifies(i5));


	}
}
