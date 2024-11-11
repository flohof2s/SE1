package U4.test;
import U4.src.control.ConcreteUserStory;
import U4.src.control.Container;
import U4.src.control.UserStory;
import U4.src.control.exceptions.ContainerException;
import U4.src.persistence.PersistenceStrategy;
import U4.src.persistence.PersistenceStrategyStream;
import U4.src.persistence.exceptions.PersistenceException;
import U4.src.view.UserStoryView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ConcreteUserStoryTest {
    public static UserStory us1;
    public static UserStoryView usv;
    public static Container con;
    public static PersistenceStrategy<UserStory> p;

    @BeforeAll
    public static void init(){
        usv = new UserStoryView();
        con = Container.getInstance();
        p = new PersistenceStrategyStream<>();
        con.setPersistenceStrategy(p);
        try {
            con.store();
        } catch (PersistenceException e) {
            fail(e.getMessage());
        }
    }
    @BeforeEach
    public void setup(){
        try {
            con.setPersistenceStrategy(p);
            con.load();
            con.getCurrentList().clear();
            con.store();
        } catch (PersistenceException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void noStrategySet(){
        con.setPersistenceStrategy(null);
        try{
            con.store();
            fail();
        }catch(PersistenceException e){
            assertEquals(e.getExceptionTypeType(), PersistenceException.ExceptionType.NoStrategyIsSet);
        }

    }

    @Test
    public void wrongFileLocation(){
        try {
            con.store();
        } catch (PersistenceException e) {
            fail();
        }

        PersistenceStrategyStream<UserStory> p2 = new PersistenceStrategyStream<UserStory>();
        p2.setLocation("/objects.ser");
        con.setPersistenceStrategy(p2);

        try{
            con.load();
            fail();
        }catch(PersistenceException e){
            assertEquals(e.getExceptionTypeType(), PersistenceException.ExceptionType.ConnectionNotAvailable);
        }
    }

    @Test
    public void roundTripTest(){
        assertEquals(con.size(),0);
        try{
            con.addUserStory(new ConcreteUserStory(1,"test1",1,2,3,5,"ok1",""));
            assertEquals(con.size(),1);
            con.addUserStory(new ConcreteUserStory(2,"test2",1,2,3,5,"ok2",""));
            assertEquals(con.size(),2);
            con.store();
            assertEquals(con.size(),2);
            con.deleteUserStory(1);
            assertEquals(con.size(),1);
            con.deleteUserStory(2);
            assertEquals(con.size(),0);
            con.load();
            assertEquals(con.size(),2);
        }catch(Exception e){
            fail(e.getMessage());
        }

    }
    @Test
    public void testAddUserStoryWithSameID(){
        try{
            con.addUserStory(new ConcreteUserStory(1,"test1",1,2,3,5,"ok1",""));
            con.addUserStory(new ConcreteUserStory(2,"test2",1,2,3,5,"ok2",""));

        }catch(Exception e){
            fail(e.getMessage());
        }

        ContainerException exception = assertThrows(
                ContainerException.class,
                ()->con.addUserStory(new ConcreteUserStory(2,"test2",1,2,3,5,"ok2",""))
        );

        assertEquals("Das Member-Object mit der ID 2 ist bereits vorhanden!",exception.getMessage());

    }

    @Test
    public void testNullUserStory(){
        assertThrows(
                Exception.class,
                ()->con.addUserStory(null)
        );
    }
}
