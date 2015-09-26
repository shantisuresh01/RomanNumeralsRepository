public class Roman {
    private int arabic;
    private int numeral;

    public String getRoman(int arabic) {
        String output = "";
         while(arabic >= 10) {
            arabic -= 10 ;
            output += "X";
        }
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
    public static void main(String[] args) {
        // Find the number of digits in a number:
        int i = 233;
//        int j = (Integer.toBinaryString(i >> 1));
        int j = (int)(Math.log10(i)+1);
        System.out.println("j is : " + j );
    }
}    
