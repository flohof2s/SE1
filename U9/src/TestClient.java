package U9.src;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Text;

public class TestClient {
    public static void main(String[] args){
        ComplexDocument codoc = new ComplexDocument();
        codoc.setID(1);
        System.out.println(codoc.getID());
        System.out.println(codoc.getSize());

        GraficDocument grafdoc = new GraficDocument("https://google.com");
        grafdoc.setID(2);
        System.out.println(grafdoc.getID());
        System.out.println(grafdoc.getSize());

        TextDocument textdoc = new TextDocument("Software Engineering I ist eine Vorlesung in den Studiengaengen BIS und BCS", TextDocument.Encoding.UTF8);
        textdoc.setID(3);
        System.out.println(textdoc.getID());
        System.out.println(textdoc.getSize());

        codoc.addDoc(grafdoc);
        codoc.addDoc(textdoc);

        System.out.println(codoc.getSize());

    }
}


