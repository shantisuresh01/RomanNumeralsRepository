public class Roman {
    private String numeral;
    private char[] romanSymbols;
    {
       // instance initialization block
        numeral = "";
        romanSymbols = new char[] {'I', 'V', 'X', 'L', 'C', 'D', 'M', 'M', 'M'};
    }

    public String getRoman(int arabic) {

        // Find the number of digits in the arabic number:
        if (arabic <= 0 || arabic >= 4000) {
            numeral = "Oops!";
            return(numeral);
         }
        int numOfDigits = (int)(Math.log10(arabic) + 1);

	for (; numOfDigits >=1; numOfDigits--) {
            int powerOfTen = (int)Math.pow(10,(numOfDigits - 1));
            int arabicDigit =  arabic / powerOfTen;
            arabic = arabic - (arabicDigit * powerOfTen);

            // find the indices for the one, five and ten symbols - fiveIndex = (n*2) -1 
            int fiveIndex = (numOfDigits * 2 ) -1;
            int tenIndex = fiveIndex + 1;
            int oneIndex = fiveIndex - 1;
            computeRomanDigit(arabicDigit, this.romanSymbols[oneIndex],this.romanSymbols[fiveIndex], this.romanSymbols[tenIndex]);
        }
        return(numeral);
        
    }
    private void computeRomanDigit(int arabicDigit, char oneSymbol, char fiveSymbol, char tenSymbol){

        if(arabicDigit >= 1 && arabicDigit <=3 ) {
            while(arabicDigit > 0) {
                arabicDigit-- ;
                numeral += oneSymbol;
            }
        } 
        else if (arabicDigit == 4) {
                numeral += oneSymbol;
                numeral += fiveSymbol;
        }
        else if (arabicDigit >= 5 && arabicDigit <=8) { 
            numeral += fiveSymbol;
            while(arabicDigit > 5) {
                arabicDigit-- ;
                numeral += oneSymbol;
            }
        }  	   
        else if (arabicDigit == 9 ) { 
            numeral += oneSymbol;
            numeral += tenSymbol;

        }
        else if (arabicDigit == 0) {
            // do nothing
            ;
        }
    }

    public static void main(String[] args) {
        // Find the number of digits in a number:
        int i = 10;
//        int j = (Integer.toBinaryString(i >> 1));
        int j = (int)(Math.log10(i)+1);
        System.out.println("j is : " + j );
    }
}    
