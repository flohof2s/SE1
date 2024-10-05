package U1.view;
import U1.control.*;
import U1.control.factory.TranslatorFactory;

public class Client {

		/**
		 * Methode zur Ausgabe einer Zahl auf der Console
		 * (auch bezeichnet als CLI, Terminal)
		 *
		 */
		 public void display( int aNumber ){
			// In dieser Methode soll die Methode translateNumber
			// mit dem übergegebenen Wert der Variable aNumber
			// aufgerufen werden.
			//
			// Strenge Implementierung (nur) gegen das Interface Translator gewuenscht!

			 Translator germanTranslator = TranslatorFactory.createGermanTranslator();
			 String result = germanTranslator.translateNumber(aNumber);

			 System.out.println("Das Ergebnis der Berechnung: " +result);

		 }
}





