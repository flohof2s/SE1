package U3.src.control;

import U3.src.control.exceptions.ContainerException;
import U3.src.persistence.PersistenceStrategy;
import U3.src.persistence.exceptions.PersistenceException;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class Container implements Serializable {

    private static final Container instance = new Container();
    private List<Member> members;
    private PersistenceStrategy<Member> p;

    private Container(){
        this.members = new ArrayList<Member>();
    }

    public static Container getInstance(){
        return instance;
    }

    public void addMember(Member member) throws ContainerException,NullPointerException {
        if(member==null){
            throw new NullPointerException();
        }
        for(Member m : this.members){
            if(member.getID().equals(m.getID())){
                throw new ContainerException(member.getID());
            }
        }

        this.members.add(member);
    }

    public String deleteMember(Integer id){

        for(Member e : this.members){
            if(e.getID().equals(id)){
                this.members.remove(e);
                return "Das Member-Objekt mit der ID "+id+" wurde erfolgreich gelöscht!";
            }
        }
        return "Das Member-Objekt mit der ID "+id+" ist nicht vorhanden und kann nicht gelöscht werden!";
    }



    public List<Member> getCurrentList(){
        return this.members;
    }

    public int size(){
        return this.members.size();
    }

    public void store() throws PersistenceException {
        try{
            this.p.save(this.members);
        }catch(UnsupportedOperationException uoe){
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable,"Es ist keine Implementation vorhanden");
        }catch(NullPointerException npe ){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,"Es wurde keine Persistence Strategie gesetzt");
        }catch(Exception e){
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable,e.getMessage());
        }

    }

    public void load() throws PersistenceException {
        try{
            this.members = this.p.load();
        }catch(UnsupportedOperationException uoe){
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable,"Es ist keine Implementation vorhanden");
        }catch(NullPointerException npe ){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,"Es wurde keine Persistence Strategie gesetzt");
        }catch(Exception e){
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable,e.getMessage());
        }

    }

    public void setPersistenceStrategy(PersistenceStrategy<Member> p){
        this.p = p;
    }
}

