package junit;

import static org.junit.Assert.*;

import org.junit.Test;
import parse.tokens.Literal;

public class LitTokenTest {

	@Test
	public void test() {
		String i = "a";
		String a = "a";
		Literal t1 = new Literal(i);
		System.out.println(t1.qualifies(a));
	}

}
