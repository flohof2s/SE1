package U3.test;
import static org.junit.jupiter.api.Assertions.*;

import U3.src.control.ConcreteMember;
import U3.src.control.Container;
import U3.src.control.Member;
import U3.src.control.exceptions.ContainerException;
import U3.src.persistence.PersistenceStrategy;
import U3.src.persistence.PersistenceStrategyMongoDB;
import U3.src.persistence.PersistenceStrategyStream;
import U3.src.persistence.exceptions.PersistenceException;
import U3.src.view.MemberView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ContainerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    static Container con;

    @BeforeAll
    public static void init(){
        con = Container.getInstance();
        PersistenceStrategy<Member> p = new PersistenceStrategyStream<>();
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
            con.load();
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
    public void useMongoDBasPersistenceStrategy(){
        con.setPersistenceStrategy(new PersistenceStrategyMongoDB<Member>());
        try{
            con.store();
            fail();
        }catch(PersistenceException e){
            assertEquals(e.getExceptionTypeType(), PersistenceException.ExceptionType.ImplementationNotAvailable);
        }
    }

    @Test
    public void wrongFileLocation(){
        try {
            con.store();
        } catch (PersistenceException e) {
            fail();
        }

        PersistenceStrategyStream<Member> p2 = new PersistenceStrategyStream<Member>();
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
            con.addMember(new ConcreteMember(1));
            assertEquals(con.size(),1);
            con.addMember(new ConcreteMember(2));
            assertEquals(con.size(),2);
            con.store();
            assertEquals(con.size(),2);
            con.deleteMember(1);
            assertEquals(con.size(),1);
            con.deleteMember(2);
            assertEquals(con.size(),0);
            con.load();
            assertEquals(con.size(),2);
        }catch(Exception e){
            fail(e.getMessage());
        }

    }

    @Test
    public void pÄK1() {
        assertEquals(con.size(),0);
    }

    @Test
    public void pÄK2(){
        try{
            con.addMember(new ConcreteMember(1));
        }catch(Exception e){
            fail(e.getMessage());
        }
        assertEquals(con.size(),1);
    }

    @Test
    public void pÄK3(){
        try{
            con.addMember(new ConcreteMember(1));
            con.addMember(new ConcreteMember(2));
        }catch(Exception e){
            fail(e.getMessage());
        }
        assertEquals(con.size(),2);
    }

    @Test
    public void pÄK4(){
        try{
            con.addMember(new ConcreteMember(1));
            con.addMember(new ConcreteMember(2));
        }catch(Exception e){
            fail(e.getMessage());
        }
        con.deleteMember(2);
        assertEquals(con.size(),1);
    }

    @Test
    public void pÄK5(){
        try{
            con.addMember(new ConcreteMember(1));
            con.addMember(new ConcreteMember(2));
        }catch(Exception e){
            fail(e.getMessage());
        }
        con.deleteMember(2);
        con.deleteMember(1);
        assertEquals(con.size(),0);
    }

    @Test
    public void pÄK6(){
        try{
            con.addMember(new ConcreteMember(1));
            con.addMember(new ConcreteMember(2));
        }catch(Exception e){
            fail(e.getMessage());
        }
        String message = con.deleteMember(2);
        assertEquals("Das Member-Objekt mit der ID 2 wurde erfolgreich gelöscht!",message);
    }

    @Test
    public void pÄK7(){
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        try{
            con.addMember(new ConcreteMember(1));
        }catch(Exception e){
            fail(e.getMessage());
        }

        new MemberView().dump(con.getCurrentList());
        String console = outContent.toString();

        System.setOut(originalOut);
        System.setErr(originalErr);

        assertEquals("Member (ID = 1)\r\n",console);
    }

    @Test
    public void nÄK1(){
        try{
            con.addMember(new ConcreteMember(1));
            con.addMember(new ConcreteMember(2));

        }catch(Exception e){
            fail(e.getMessage());
        }

        ContainerException exception = assertThrows(
                ContainerException.class,
                ()->con.addMember(new ConcreteMember(2))
        );

        assertEquals("Das Member-Object mit der ID 2 ist bereits vorhanden!",exception.getMessage());

    }

    @Test
    public void nÄK2(){
        try{
            con.addMember(new ConcreteMember(1));
            con.addMember(new ConcreteMember(2));

        }catch(Exception e){
            fail(e.getMessage());
        }

        String message = con.deleteMember(3);
        assertEquals("Das Member-Objekt mit der ID 3 ist nicht vorhanden und kann nicht gelöscht werden!",message);
    }

    @Test
    public void nÄK3(){
        assertThrows(
                Exception.class,
                ()->con.addMember(null)
        );
    }
}
