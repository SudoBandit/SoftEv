package cobol;
import parse.*;
import parse.tokens.*;
public class recordDescriptionAssembler extends Assembler {
	/**
	 *
	 * @param Assembly the assembly to work on
	 */
	public void workOn(Assembly a) {
		System.out.println(a);
		Cobol c = new Cobol();
		Token t = (Token) a.pop();
		c.setRecordDescriptionLength((int) t.nval()) ;
		
		 t = (Token) a.pop();

		String tokenString = t.sval().trim();

		String symbol =( "" + tokenString.charAt(0));
		c.setRecordDescriptionSymbol(symbol);
		String description ="";
		if(symbol.equals("9")) {
			description = "Numeric";
		}
		else if(symbol.equals("a")){
			description = "Alphabetic";
		}
		else if(symbol.equals("x")){
			description = "Alphanumeric";
		}
		else if(symbol.equals("v")){
			description = "Implicit Decimal";
		}
		else if(symbol.equals("s")){
			description = "Sign";
		}
		else if(symbol.equals("p")){
			description = "Assumed Decimal";
		}
		c.setRecordDescriptionPicDesc( description); 
		
		 t = (Token) a.pop();
		 c.setRecordDescriptionName(t.sval());
		
		a.setTarget(c);
		
	}
}

