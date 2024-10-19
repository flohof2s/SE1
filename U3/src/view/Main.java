package U3.src.view;

import U3.src.control.Container;
import U3.src.control.Member;
import U3.src.persistence.PersistenceStrategy;
import U3.src.persistence.PersistenceStrategyStream;

public class Main {
    public static void main(String[] args){

        Container con = Container.getInstance();
        PersistenceStrategy<Member> p = new PersistenceStrategyStream<Member>();
        con.setPersistenceStrategy(p);
        new Client(con);



    }
}