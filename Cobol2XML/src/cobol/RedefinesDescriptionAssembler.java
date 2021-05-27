package cobol;
import parse.*;
import parse.tokens.*;
public class RedefinesDescriptionAssembler extends Assembler {
	/**
	 *
	 * @param Assembly the assembly to work on
	 */
	public void workOn(Assembly a) {
		System.out.println(a);
		int length = 0;
		
		Cobol c = new Cobol();
		
		Token t = (Token) a.pop();
		 c.setDefinedName(t.sval());
		 t = (Token) a.pop();
		 c.setRedefinedName(t.sval());
		 String description="";
		 
		 if(a.hasMoreElements()) {			 
			  t = (Token)  a.nextElement();
			  	if(t.toString().equals(".")) {
		 			description=null; 	
			 		c.setRecordDescriptionPicDesc(description);
			 		c.setRecordDescriptionLength(0);
			 		a.setTarget(c);
			 		return;
			  		
			  	} 
			  	else 
			  	{
			  		t = (Token)  a.nextElement();  		
			  		String symbol = t.toString();
			 		if(symbol.length()>1) {
			 			length = length+symbol.length();
			 			symbol=symbol.substring(0, 1);
			 		}
			 		
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
			 		t = (Token)  a.nextElement();
			 		c.setRecordDescriptionPicDesc(description);			 		
			 		t = (Token) a.nextElement();
			 		c.setRecordDescriptionSymbol(symbol);
			 		c.setRecordDescriptionLength(length+(int) t.nval());

			 		

		 		
		 }
		// else if(  (type >= 2  && type <= 49)||type==77 ) {
		// 	 c.setRecordDescriptionSymbol(null);
		//	 c.setRecordDescriptionPicDesc(null);
		 
		 
		  //t = (Token) a.nextElement();
		 //System.out.println(t);
		 c.setRecordDescriptionLength((int) t.nval());
		 //System.out.println(c);
		a.setTarget(c);
		
	}
	}
	}

