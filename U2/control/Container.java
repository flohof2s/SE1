package U2.control;

import U2.control.exceptions.ContainerException;

import java.util.ArrayList;

public class Container {

    private ArrayList<Member> members;
    private ArrayList<Integer> memberIDs;

    public Container(){
        this.members = new ArrayList<Member>();
        this.memberIDs = new ArrayList<Integer>();
    }

    public void addMember(Member member) throws ContainerException {
        if(this.memberIDs.contains(member.getID())){
            throw new ContainerException(member.getID());
        }

        this.members.add(member);
        this.memberIDs.add(member.getID());
    }

    public String deleteMember(Integer id){
        if(!this.memberIDs.contains(id)){
            return "Das Member-Objekt mit der ID "+id+" ist nicht vorhanden und kann nicht gelöscht werden!";
        }

        for(Member e : this.members){
            if(e.getID().equals(id)){
                this.members.remove(e);
                break;
            }
        }
        this.memberIDs.remove(id);

        return "Das Member-Objekt mit der ID "+id+" wurde erfolgreich gelöscht!";
    }

    public void dump(){
        for(Member e : this.members){
            System.out.println(e);
        }
    }

    public int size(){
        return this.members.size();
    }
}
