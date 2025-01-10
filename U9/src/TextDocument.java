package U9.src;

import java.io.UnsupportedEncodingException;

public class TextDocument extends CoreDocument{

    public enum Encoding {
        UTF8,
        UTF16,
        UTF32
    }

    private Encoding encoding;

    public TextDocument(String inhalt, Encoding encoding) {
        super(inhalt);
        this.encoding = encoding;
    }

    @Override
    public int getSize() {
        String encString="";
        switch (this.encoding){
            case UTF8:
                encString = "UTF-8";
                break;
            case UTF16:
                encString = "UTF-16";
                break;
            case UTF32:
                encString = "UTF-32";
                break;
        }
        try{
            return super.inhalt.getBytes(encString).length;
        }catch(UnsupportedEncodingException e){
            return 0;
        }

    }
}
