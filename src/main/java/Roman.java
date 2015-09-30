import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/romanengine")
public class Roman extends HttpServlet {
    private String numeral;
    private int arabicNumber;
    private char[] romanSymbols;
    private StringBuilder errorMsg = new StringBuilder();
    {
       // instance initialization block
        numeral = "";
        arabicNumber = 0;
        errorMsg.setLength(0);
        romanSymbols = new char[] {'I', 'V', 'X', 'L', 'C', 'D', 'M', 'M', 'M'};
    }

    public Roman() {}
   
    private Integer getInt(String s) {
        try {
            Integer a = new Integer(s);
            return a;
        } catch (NumberFormatException e) {
            return null;
        }
    }
    public void serviceRequest(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException     {

            // harvest the arabic input string and convert to Integer
            String arabic_string = request.getParameter("number");
            Integer arabic = getInt(arabic_string);

            // get Roman Numeral
            String numeral = getRoman(arabic);

            // populate response attributes so that JSP can use these values to create the response
            request.setAttribute("number", arabic);
            request.setAttribute("numeral", numeral);
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/index.jsp");
            rd.forward(request, response);

    } 
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException     {

        serviceRequest(request, response);
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        serviceRequest(request, response);
    }

    public String getRoman(int arabic) {

        // initialize numeral to empty string
        numeral = "";

        // Find the number of digits in the arabic number:
        if (arabic <= 0 || arabic >= 4000) {
            numeral = "Oops!";
            return(numeral);
         }
        int numOfDigits = (int)(Math.log10(arabic) + 1);

        // Reduce the input number by it's thousands, hundreds, tens and ones.
	for (; numOfDigits >=1; numOfDigits--) {
            int powerOfTen = (int)Math.pow(10,(numOfDigits - 1));
            int arabicDigit =  arabic / powerOfTen;  //  ignore the decimal part
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
   // -----------------------
   // -----------------------
   // ReverseRoman from this point on

    private int getArabicNumber() {
        return this.arabicNumber;
    }

    private void setArabicNumber(int i) {
        this.arabicNumber = i;
    }
    private void setErrorMsg(String errorMsg) {
        this.errorMsg.setLength(0);
        this.errorMsg.append(errorMsg);
    }
    public int getArabic(String roman) {
        if (!containsRomanCharacters(roman)) {
            setErrorMsg("Error: Letters are not Roman Numerals");
            setArabicNumber(5000);
        }
        else if(fourLetterRepeatOfOnes(roman)) {
            setErrorMsg("Error: 'One' letters cannot be repeated four times");
            setArabicNumber(5000);
        }
        else if(repeatOfFives(roman)) {
            setErrorMsg("Error: 'Five' letters cannot more than once ");
            setArabicNumber(5000);
        }
        else if(incorrectSubtractivePattern(roman)) {
            setErrorMsg("Error: One symbols may be subtracted only from next two higher symbols");
            setArabicNumber(5000);
        }
        else {
          setArabicNumber(3);
        }
        return(getArabicNumber());
    }


    private boolean containsRomanCharacters(String roman) {
        // test if characters are Roman numeral characters
        return (roman == null) ? false : roman.matches("[IVXLCM]*");
    }
    private boolean fourLetterRepeatOfOnes(String roman) {
        // Ensure that Roman Letters are not repeated four times.
        if (roman.indexOf("IIII") != -1) {
            return true;
        }
        if (roman.indexOf("XXXX") != -1) {
            return true;
        }
        if (roman.indexOf("CCCC") != -1) {
            return true;
        }
        if (roman.indexOf("MMMM") != -1) {
            return true;
        }
        return false;
    }

    private boolean repeatOfFives(String roman) {
        if (roman.indexOf('V', roman.indexOf("V")) != -1) {
            return true;
        }
        if (roman.indexOf('L', roman.indexOf("L")) != -1) {
            return true;
        }
        if (roman.indexOf('D', roman.indexOf("D")) != -1) {
            return true;
        }
        return false;
    } 

    private boolean incorrectSubtractivePattern(String roman) {
        
        if (roman.matches("I[LCD]")) {
            return true;
        }
        else if(roman.matches("X[DM]")){
            return true;
        }
        else {
            return false;
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
