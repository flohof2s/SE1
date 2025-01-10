package U9.src;

public abstract class ConcreteDocument implements Document {
    private int ID;

    public ConcreteDocument(){

    }

    public int getID(){
        return this.ID;
    }

    public void setID(int ID){
        this.ID=ID;
    }
}
