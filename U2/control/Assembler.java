package U2.control;

import U2.control.exceptions.ContainerException;

public class Assembler {
    public static void main(String[] args){
        Container con = new Container();

        try{
            con.addMember(new ConcreteMember(1));
            con.addMember(new ConcreteMember(2));
            con.addMember(new ConcreteMember(3));
            con.addMember(new ConcreteMember(3));
        }
        catch(ContainerException e){
            System.out.println(e.getMessage());
        }

        con.dump();
        System.out.println(con.size());

        con.deleteMember(3);

        con.dump();
        System.out.println(con.size());

    }
}
