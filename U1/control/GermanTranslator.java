package U1.control;

import U1.control.factory.TranslatorFactory;

public class GermanTranslator implements Translator {

	private static final String[] translateNumberArray = new String[]{
					"",
					"eins",
					"zwei",
					"drei",
					"vier",
					"fünf",
					"sechs",
					"sieben",
					"acht",
					"neun",
					"zehn"
	};
	public String date = "Okt/2024"; // Default-Wert

	/**
	 * Methode zur Übersetzung einer Zahl in eine String-Repraesentation
	 */
	public String translateNumber( int number ) throws IndexOutOfBoundsException{
		// [ihr Source Code aus Übung 1-2]
		if(number<1 || number >10){
			throw new IndexOutOfBoundsException("Übersetzung der Zahl \""+number+"\" nicht möglich "+ Translator.version);
		}

		return GermanTranslator.translateNumberArray[number];
	}

	/**
	 * Objektmethode der Klasse GermanTranslator zur Ausgabe einer Info.
	 */
	public void printInfo(){
		System.out.println( "GermanTranslator v1.9, erzeugt am " + this.date );
	}

	/**
	 * Setzen des Datums, wann der Uebersetzer erzeugt wurde (Format: Monat/Jahr (Beispiel: "Okt/2024"))
	 * Das Datum sollte system-intern durch eine Factory-Klasse gesetzt werden und nicht von externen View-Klassen
	 */
	public void setDate( String date ) {
		this.date = date;
	}

}
