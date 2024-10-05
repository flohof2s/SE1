package U1.control.factory;

import U1.control.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TranslatorFactory {
    public static Translator createGermanTranslator(){
        GermanTranslator translator = new GermanTranslator();
        translator.setDate(new SimpleDateFormat("MM/yyyy").format(Calendar.getInstance().getTime()));
        return translator;
    }
}