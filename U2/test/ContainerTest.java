package U2.test;
import static org.junit.jupiter.api.Assertions.*;

import U2.control.exceptions.ContainerException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import U2.control.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ContainerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    Container con;

    @BeforeEach
    public void setup(){
        con = new Container();
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

        con.dump();
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
