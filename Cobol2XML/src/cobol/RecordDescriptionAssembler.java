package cobol;
import parse.*;
import parse.tokens.*;
public class RecordDescriptionAssembler extends Assembler {
	/**
	 *
	 * @param Assembly the assembly to work on
	 */
	public void workOn(Assembly a) {
		//System.out.println(a);
		Integer length=0;
		String symbol ="";
			String description ="";
		Cobol c = new Cobol();
		
		Token t = (Token) a.pop();
		 c.setRecordDescriptionName(t.sval());
		 
		 t = (Token) a.pop();
		 Integer type = (int) t.nval();
		 c.setRecordDescriptionType(type) ;
		 
		 if(type == 1 ||type == 77 || type ==5) {
			  t = (Token)  a.nextElement();
			  length=0;
			  
			  		if (t.isNumber()){
			  			symbol = Integer.toString((int) t.nval());	

			  			if(symbol.length()>1) {
			  				length = length+symbol.length();
			  				symbol=symbol.substring(0, 1);
			 			}

			 		}
			  		else {
			  			 symbol = t.sval();
			  			if(symbol.length()>1) {
			  				length = length+symbol.length();
			  				}
			  			symbol=symbol.substring(0, 1);
			  		}
			 				 		
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
			 		c.setRecordDescriptionSymbol(symbol);
			 		c.setRecordDescriptionPicDesc( description); 
			 		
		 			}
		 				else if(  type >= 2  && type <= 49 ) {
		 						c.setRecordDescriptionSymbol(null);
		 						c.setRecordDescriptionPicDesc( null);
		 			}
		 
		 if (a.hasMoreElements()) {
			 
			 switch (a.nextElement().toString()){
			 
			 case ".":
				 if(length==0) {
					 length=1;
				 }
				 
				 c.setRecordDescriptionLength(length);
				 break;
			 case "(":
				 t= (Token) a.nextElement();
				 //System.out.println("number that is value "+t);
				 
				 c.setRecordDescriptionLength(length+(int) t.nval());
				 break;
			 
			 default:
				 c.setRecordDescriptionLength(length);
				 break;
				 }
			 a.setTarget(c);
			 
		 }
		 else {
		 c.setRecordDescriptionLength(length);
		a.setTarget(c);
		 }
	}
}

