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
    {
       // instance initialization block
        numeral = "";
        arabicNumber = 0;
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
   // ReverseRoman from this point on
   // Create an Exception for Four consecutive one-letters in input
    private class FourLetterException extends Exception { }
    private class NonRepeatableException extends Exception { }

    public int getArabic(String roman) {
        // Find the Arabic number given the Roman Numeral
       try{
            sanitizeRoman(roman);
            arabicNumber = 3;
       }
       catch(FourLetterException e) {
            System.out.println("Four Letter Exception");
            return(5000);
       }            
       catch(NonRepeatableException e) {
            System.out.println("Non Repeatable Exception");
            return(5000);
       }            
      return(arabicNumber);
    }

    private void sanitizeRoman(String roman) throws FourLetterException, NonRepeatableException {
        // Ensure that Letter I is not repeated four times.
        if (roman.indexOf("IIII") != -1) {
            throw new FourLetterException();
        }
        if (roman.indexOf("XXXX") != -1) {
            throw new FourLetterException();
        }
        if (roman.indexOf("CCCC") != -1) {
            throw new FourLetterException();
        }
        if (roman.indexOf("MMMM") != -1) {
            throw new FourLetterException();
        }
        if (roman.indexOf('V', roman.indexOf("V")) != -1) {
            throw new NonRepeatableException();
        }
        if (roman.indexOf('L', roman.indexOf("L")) != -1) {
            throw new NonRepeatableException();
        }
        if (roman.indexOf('D', roman.indexOf("D")) != -1) {
            throw new NonRepeatableException();
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
