package U4.test;
import U4.src.control.ConcreteUserStory;
import U4.src.control.UserStory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConcreteUserStoryTest {
    public static UserStory us1;
    @BeforeEach
    public void setup(){
        us1 = new ConcreteUserStory(1,"test1",1,5,2,1,"haha","sag");
    }

    @Test
    public void testPrioCalc(){
        assertEquals(2.0,us1.getPrio());
    }
}
