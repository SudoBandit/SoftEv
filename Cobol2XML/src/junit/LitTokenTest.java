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
<<<<<<< Updated upstream
		Token i = new Token("a");
		assertTrue(t1.qualifies(i));
=======
		Token i = new Token(a);
		System.out.println(t1.qualifies(i));
>>>>>>> Stashed changes
	}

}
