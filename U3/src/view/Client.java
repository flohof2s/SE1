package U3.src.view;

import U3.src.control.ConcreteMember;
import U3.src.control.Container;
import U3.src.control.exceptions.ContainerException;
import U3.src.persistence.exceptions.PersistenceException;

public class Client {
    Container con;
    public Client(Container con){
        this.con = con;
        MemberView memberView = new MemberView();
        try{
            this.con.addMember(new ConcreteMember(1));
            this.con.addMember(new ConcreteMember(2));
            this.con.addMember(new ConcreteMember(3));
        }
        catch(ContainerException e){
            System.out.println(e.getMessage());
        }

        memberView.dump(this.con.getCurrentList());
    }
}
