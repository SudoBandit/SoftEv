package junit;

import static org.junit.Assert.*;

import org.junit.Test;
import parse.tokens.Literal;
import parse.tokens.Token;

public class LitTokenTest {

	@Test
	public void test() {
		
		String a = "a";
		Literal t1 = new Literal(a);
		Token i = new Token("a");
		assertTrue(t1.qualifies(i));

	}
}
