package U4.src.view;

import U4.src.control.Container;
import U4.src.persistence.PersistenceStrategyStream;

public class Main {
    public static void main(String[] args){
        Container con = Container.getInstance();
        con.setPersistenceStrategy(new PersistenceStrategyStream<>());
        new Client(con);
    }
}
