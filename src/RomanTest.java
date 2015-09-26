import org.junit.Test;
import static org.junit.Assert.*;

public class RomanTest {

    private Roman roman;
/*
    @Before
    public void setUp() {
        roman = new Roman();
    }
*/
    @Test
    public void getRomanOf1ShouldReturnStringI() {
        roman = new Roman();
        assertEquals("I", roman.getRoman(1));
    }
    @Test
    public void getRomanOf2ShouldReturnStringII() {
        roman = new Roman();
        assertEquals("II", roman.getRoman(2));
    }
    @Test
    public void getRomanOf3ShouldReturnStringIII() {
        roman = new Roman();
        assertEquals("III", roman.getRoman(3));
    }
    @Test
    public void getRomanOf5ShouldReturnStringV() {
        roman = new Roman();
        assertEquals("V", roman.getRoman(5));
    }
    @Test
    public void getRomanOf10ShouldReturnStringX() {
        roman = new Roman();
        assertEquals("X", roman.getRoman(10));
    }
    @Test
    public void getRomanOf4ShouldReturnStringIV() {
        roman = new Roman();
        assertEquals("IV", roman.getRoman(4));
    }
    @Test
    public void getRomanOf12ShouldReturnStringXII() {
        roman = new Roman();
        assertEquals("XII", roman.getRoman(12));
    }
    @Test
    public void getRomanOf16ShouldReturnStringXVI() {
        roman = new Roman();
        assertEquals("XVI", roman.getRoman(16));
    }
    @Test
    public void getRomanOf9ShouldReturnStringIX() {
        roman = new Roman();
        assertEquals("IX", roman.getRoman(9));
    }
    @Test
    public void getRomanOf19ShouldReturnStringXIX() {
        roman = new Roman();
        assertEquals("XIX", roman.getRoman(19));
    }
    @Test
    public void getRomanOf100ShouldReturnStringC() {
        roman = new Roman();
        assertEquals("C", roman.getRoman(100));
    }
    @Test
    public void getRomanOf1000ShouldReturnStringM() {
        roman = new Roman();
        assertEquals("M", roman.getRoman(1000));
    }
}



