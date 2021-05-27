package cobol;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

public class RemarksAssembler extends Assembler {

	@Override
	public void workOn(Assembly a) {
		Cobol c = new Cobol();
		StringBuilder builder = new StringBuilder();
		while (a.hasMoreElements()) {
				Token t = (Token) a.nextElement();
				builder.append(t);
		}
		c.setRemarks(builder.toString());
		a.setTarget(c);
	}

}
	
