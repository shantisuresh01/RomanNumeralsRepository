import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

enum RomanNumeral {

    M(1000, 1), D(500, 5), C(100, 1), L(50, 5), X(10, 1), V(5, 5), I(1, 1);
    RomanNumeral(int value, int type) { // constructor
         this.value = value;
         this.type = type;
    }
     private int value;      // an instance variable
     private int type;      // an instance variable
     public int getValue() {
         return value;
    }
     public int getType() {
         return type;
    }
}

class ArabicResult{
    private int computedNumber;
    private String errorMsg;
    public ArabicResult(int computedNumber, String errorMsg) {
        this.computedNumber = computedNumber;
        this.errorMsg = errorMsg;
    }
    public void setComputedNumber(int computedNumber) {
        this.computedNumber = computedNumber;
    }
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    public int getComputedNumber() {
        return computedNumber;
    }
    public String getErrorMsg() {
        return errorMsg;
    }
}

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
   
    private void setNumeral(String s) {
        this.numeral = s;
    }
    public String getNumeral() {
        return(this.numeral);
    }
    private void setArabicNumber(int i) {
        this.arabicNumber = i;
    }
    public int getArabicNumber() {
        return this.arabicNumber;
    }

    private void setErrorMsg(String errorMsg) {
        this.errorMsg.setLength(0);
        this.errorMsg.append(errorMsg);
    }
    public String getErrorMsg() {
        return(this.errorMsg.toString());
    }
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
            String input_question = request.getParameter("question");
            Integer arabic = getInt(input_question);
            String output_result="";
            if (arabic != null) {
                // get Roman Numeral
                output_result = getRoman(arabic);
            }
            else {
                // get Roman Numeral
                output_result = String.valueOf(getArabic(input_question));
            }
            // populate response attributes so that JSP can use these values to create the response
            request.setAttribute("question", input_question);
            request.setAttribute("result", output_result);
            request.setAttribute("error_msg", getErrorMsg());
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
    public String getRomanNew(int arabic) {

         String numeral="";
        // initialize numeral to empty string
        setNumeral("");

        // Find the number of digits in the arabic number:
        if (arabic <= 0 || arabic >= 4000) {
            setNumeral("Oops!");
            setErrorMsg("Error: Integer out or range");
         }
         else {
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
                setErrorMsg("");
            }
        }
        return(getNumeral());
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

    private String computeRomanDigitNew(int arabicDigit, char oneSymbol, char fiveSymbol, char tenSymbol){

        String numeral = "";

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
       setNumeral(numeral);
       return(numeral);
    }

    private String computeRomanDigitNew2(int arabicDigit, char oneSymbol, char fiveSymbol, char tenSymbol){
        
        StringBuilder numeral= new StringBuilder("");

        if(arabicDigit >= 1 && arabicDigit <=3 ) {
            while(arabicDigit > 0) {
                arabicDigit-- ;
                numeral.append(String.valueOf(oneSymbol));
            }
        } 
        else if (arabicDigit == 4) {
                numeral.append(String.valueOf(oneSymbol));
                numeral.append(String.valueOf(fiveSymbol));
        }
        else if (arabicDigit >= 5 && arabicDigit <=8) { 
            numeral.append(String.valueOf(fiveSymbol));
            while(arabicDigit > 5) {
                arabicDigit-- ;
                numeral.append(String.valueOf(oneSymbol));
            }
        }  	   
        else if (arabicDigit == 9 ) { 
            numeral.append(String.valueOf(oneSymbol));
            numeral.append(String.valueOf(tenSymbol));

        }
        else if (arabicDigit == 0) {
            // do nothing
            ;
        }
        System.out.println("computeRomanDigit for: " + arabicDigit + numeral.toString());
        return(numeral.toString());
    }
   // -----------------------
   // -----------------------
   // ReverseRoman from this point on


    public int getArabic(String roman) {

        ArabicResult arabicResult = new ArabicResult(0, "");

        if (!containsRomanCharacters(roman)) {
            setErrorMsg("Error: Letters are not Roman Numerals");
            setArabicNumber(5000);
        }
        else if(fourLetterRepeatOfOnes(roman)) {
            setErrorMsg("Error: 'One' letters cannot be repeated four times");
            setArabicNumber(5000);
        }
        else if(repeatOfFives(roman)) {
            setErrorMsg("Error: 'Five' letters cannot occur more than once ");
            setArabicNumber(5000);
        }
        else if(incorrectSubtractivePattern(roman)) {
            setErrorMsg("Error: One symbols may be subtracted only from next two higher symbols");
            setArabicNumber(5000);
        }
        else {
            arabicResult = computeArabicNumber(roman);
            setErrorMsg(arabicResult.getErrorMsg());
            setArabicNumber(arabicResult.getComputedNumber());
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
        char[] fiveLetterArray = {'V', 'L', 'D'};
        for (char ch: fiveLetterArray) {
            int first_occurance = roman.indexOf(ch);
            // is there a second occurance?:
            if (roman.indexOf(ch, first_occurance+1) != -1) {
                return true;
            }
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

    public ArabicResult computeArabicNumber(String roman) {
        
        List<RomanNumeral> inputList = new ArrayList<RomanNumeral>();
        List<Integer> inputValues = new ArrayList<Integer>();
        int sum = 0;

        // initialize ArabicResult
        ArabicResult arabicResult = new ArabicResult(0, "");
 
        
        // form an inputList basded on the Roman input string
        for (int i = 0; i < roman.length(); i++){
            char c = roman.charAt(i);
            inputList.add(RomanNumeral.valueOf(String.valueOf(c)));
        }

        // check the size of the input string
        if (roman.length() == 1) {
            arabicResult.setComputedNumber(inputList.get(0).getValue());
            arabicResult.setErrorMsg("");
            return(arabicResult);
        }

        for (int i = 0; i < inputList.size(); i++){
            int valueAtI = inputList.get(i).getValue();
            int typeAtI = inputList.get(i).getType();
            if (i == inputList.size() - 1) {
            // last element
                 inputValues.add(valueAtI);
                 break;
            }
            else if (i == inputList.size()) {
            // subtraction operation - already accounts for last two elements
               break;
            }

            int valueAtIAnd1 = inputList.get(i+1).getValue();
            int typeAtIAnd1 = inputList.get(i+1).getType();

            if(valueAtI > valueAtIAnd1) {
                 inputValues.add(valueAtI);
                 continue;
            }
            else if (valueAtI < valueAtIAnd1) {
                // subtractive?
                if(typeAtI == 5) { // Five-types may not participate in subtraction
                    arabicResult.setErrorMsg("Invalid Roman Value: Five-type letters may not be subtracted");
                    arabicResult.setComputedNumber(5000);
                    return arabicResult;
                }
                else if ((valueAtIAnd1 == 5 * valueAtI) || (valueAtIAnd1 == 10 * valueAtI)) {
                     if(i >=1){
                         int subtractive_value = valueAtIAnd1 - valueAtI;
                         int previous_value = inputList.get(i-1).getValue();
                         if (subtractive_value > previous_value) {
                             arabicResult.setErrorMsg("Invalid Roman Value: double subtraction");
                             arabicResult.setComputedNumber(5000);
                             return arabicResult;
                         } 
                     }
                     inputValues.add(valueAtIAnd1 - valueAtI);
                     i++;  // advance the iterator since we have handled two places.
                     continue;
                 } 
                 else {
                    arabicResult.setErrorMsg("Invalid Roman Value");
                    arabicResult.setComputedNumber(5000);
                    return arabicResult;
                 }
            }
            else if ((valueAtI == valueAtIAnd1) && (typeAtI == typeAtIAnd1)) {
                if(typeAtI == 1) {
                    inputValues.add(valueAtI);
                    continue;
                }
            }
        }
        for (int i = 0; i < inputValues.size(); i++){
            sum += inputValues.get(i);
        }
        arabicResult.setErrorMsg("");
        arabicResult.setComputedNumber(sum);
        return arabicResult;
            
    }

    public static void main(String[] args) {
        // Find the number of digits in a number:
        int i = 10;
//        int j = (Integer.toBinaryString(i >> 1));
        int j = (int)(Math.log10(i)+1);
        System.out.println("j is : " + j );

        Roman roman = new Roman();
        System.out.println("Arabic of III = " + roman.getArabic("III"));

    }
}    

