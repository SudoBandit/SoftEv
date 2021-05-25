package cobol;
import parse.*;
import parse.tokens.*;
public class CommentLineAssembler extends Assembler {
	/**
	 * Pop a string, and set the target DataDivision to this
	 * string.
	 *
	 * @param Assembly the assembly to work on
	 */
	public void workOn(Assembly a) {
		//System.out.println("commentLineAssembler");
		StringBuilder comment = new StringBuilder();
		Cobol c = new Cobol();
		comment.append(a.pop());
		while (a.hasMoreElements()) {
			comment.append(" ");
			Token t = (Token) a.nextElement();
				comment.append(t);
		}
		String comments = comment.toString();
		c.setCommentLine(comments);
		a.setTarget(c);
	}
}

