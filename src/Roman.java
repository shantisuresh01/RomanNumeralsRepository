public class Roman {
    private int arabic;
    private int numeral;

    public String getRoman(int arabic) {
        String output = "";
	switch (arabic) {
	case 1:
            output += "I";
	    break;
        default:
            break;
        }
        return(output);
    }
}    
