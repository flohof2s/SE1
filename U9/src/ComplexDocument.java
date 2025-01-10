package U9.src;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class ComplexDocument extends ConcreteDocument{

    private List<Document> docs;

    public ComplexDocument(){
        super();

        this.docs = new ArrayList<Document>();
    }

    public void addDoc(Document doc){
        this.docs.add(doc);
    }

    public void deleteDoc(int ID){
        for(Document doc:this.docs){
            if(doc.getID()==ID){
                this.docs.remove(doc);
                break;
            }
        }
    }

    @Override
    public int getSize() {
        int size = 0;

        for(Document doc:this.docs){
            size+=doc.getSize();
        }

        return size;
    }
}
