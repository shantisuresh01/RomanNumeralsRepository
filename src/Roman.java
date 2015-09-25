public class Roman {
    private int arabic;
    private int numeral;

    public String getRoman(int arabic) {
        String output = "";
         while(arabic >= 5) {
            arabic -= 5 ;
            output += "V";
        }
	 while(arabic >= 1) {
 	    arabic-- ;
	    output += "I";
        }
        return(output);
    }
}    
