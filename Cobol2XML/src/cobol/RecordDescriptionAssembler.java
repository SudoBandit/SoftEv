package cobol;
import parse.*;
import parse.tokens.*;
public class RecordDescriptionAssembler extends Assembler {
	/**
	 *
	 * @param Assembly the assembly to work on
	 */
	public void workOn(Assembly a) {
		System.out.println(a);
		
		Cobol c = new Cobol();
		
		Token t = (Token) a.pop();
		 c.setRecordDescriptionName(t.sval());
		 
		 t = (Token) a.pop();
		 Integer type = (int) t.nval();
		 c.setRecordDescriptionType(type) ;
		 
		 if(type == 1) {
			  t = (Token)  a.nextElement();
			  
			 //
			 		String symbol = t.toString();
			 		
			 		
			 		String description ="";
			 		if(symbol.equals("9.0")) {
			 			description = "Numeric";
			 			symbol ="9";
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
			 		c.setRecordDescriptionSymbol(symbol);
			 		//System.out.println("Symbol check" + symbol);
			 		c.setRecordDescriptionPicDesc( description); 
			 		a.nextElement();
			 		
		 }
		 else if(  type >= 2  && type <= 49 ) {
		 	 c.setRecordDescriptionSymbol(null);
			 c.setRecordDescriptionPicDesc( null);
		 }
		 else if(type == 77) {
		 	 c.setRecordDescriptionSymbol(null);
			 c.setRecordDescriptionPicDesc( null);
		 }
		  t = (Token) a.nextElement();
		 //System.out.println(t);
		 c.setRecordDescriptionLength((int) t.nval());
		a.setTarget(c);
		
	}
}

