package U1.test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import U1.control.GermanTranslator;
import U1.control.Translator;
public class GermanTranslatorTest {

    GermanTranslator translator;
    @BeforeEach
    public void setUp(){
        translator = new GermanTranslator();
    }
    @Test
    public void gÄK1() {
        assertEquals("fünf" , translator.translateNumber(5));
    }

    @Test
    public void nÄK1() {
        try
        {
            translator.translateNumber(-2);
            fail("Method is supposed to throw an error");
        }
        catch(Exception e)
        {
            String message ="Übersetzung der Zahl \"-2\" nicht möglich " + Translator.version;
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public void nÄK2() {
        try
        {
            translator.translateNumber(12);
            fail("Method is supposed to throw an error");
        }
        catch(Exception e)
        {
            String message ="Übersetzung der Zahl \"12\" nicht möglich " + Translator.version;
            assertEquals(message, e.getMessage());
        }
    }

    @Test
    public void nÄK3() {
        try
        {
            translator.translateNumber(0);
            fail("Method is supposed to throw an error");
        }
        catch(Exception e)
        {
            String message ="Übersetzung der Zahl \"0\" nicht möglich " + Translator.version;
            assertEquals(message, e.getMessage());
        }
    }

}