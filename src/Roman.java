public class Roman {
    private int arabic;
    private int numeral;

    public String getRoman(int arabic) {
        String output = "";
	 while(arabic > 0) {
 	    arabic -= 1;
	    output += "I";
        }
        return(output);
    }
}    
