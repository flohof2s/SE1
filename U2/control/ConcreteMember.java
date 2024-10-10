package U2.control;

public class ConcreteMember implements Member{

    private Integer id;

    public ConcreteMember(int id){
        this.id = new Integer(id);
    }

    @Override
    public Integer getID() {
        return this.id;
    }

    @Override
    public String toString(){
        return "Member (ID = "+this.getID()+")";
    }
}
