import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing of Date.java
 * @author Max Strickland
 */
public class DateTest
{
    Date rFrostDOB = new Date(7, 14, 1989);
    Date maxDOB = new Date(2, 2, 2003);
    
    @BeforeClass
    public static void oneTimeSetup()
    {
        return;
    }

    @AfterClass
    public static void oneTimeTearDown()
    {
        return;
    }

    @BeforeEach
    public void setup()
    {
        return;
    }

    @AfterEach
    public void tearDown()
    {
        return;
    }

    @Test
    public void toStringRobert()
    {
        //Case: day > 10
        assertEquals("7/14/1989", rFrostDOB.toString());
    }

    @Test
    public void toStringMax()
    {
        //Case: day < 10
        assertEquals("2/02/2003", maxDOB.toString());
    }

    @Test
    public void dateMatchTestC1()
    {
        //Case: the dates match
        assertEquals(true, maxDOB.dateMatch(maxDOB));
    }

    @Test
    public void dateMatchTestC2()
    {
        //Case: the dates don't match
        assertEquals(false, maxDOB.dateMatch(rFrostDOB));
    }
}