import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing of Users.java
 * Note: Code fails to compile, as it gives a NullPointerException.
 * @author Max Strickland
 */
public class UsersTest
{
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
    public void testSearchUserC1()  //Case 1: correct username, correct password
    {
        Users usersObject = new Users();
        //actually check
        RegisteredUser registeredUserObject;
        registeredUserObject = usersObject.searchUser("rfrost325", "snowL0ver432*");
        assertNotNull(registeredUserObject);
        return;
    }

    @Test
    public void testSearchUserC2()  //Case 2: incorrect username, correct password
    {
        Users usersObject = new Users();
        RegisteredUser registeredUserObject;
        registeredUserObject = usersObject.searchUser("rflame325", "snowL0ver432*");
        assertNull(registeredUserObject);
        return;
    }

    @Test
    public void testSearchUserC3()  //Case 3: incorrect username, incorrect password
    {
        Users usersObject = new Users();
        RegisteredUser registeredUserObject;
        registeredUserObject = usersObject.searchUser("rflame325", "snowH8ter432*");
        assertNull(registeredUserObject);
        return;
    }

    @Test
    public void testSearchUserC4()  //Case 4: correct username, incorrect password
    {
        Users usersObject = new Users();
        RegisteredUser registeredUserObject;
        registeredUserObject = usersObject.searchUser("rfrost325", "snowH8ter432*");
        assertNull(registeredUserObject);
        return;
    }

    @Test
    public void testAddUser()
    {
        Users usersObject = new Users();
        Date dobRobertFlame = new Date(7, 17, 1968);
        RegisteredUser registerUserRobertFlame = new RegisteredUser("Robert", "Flame", "rflame532", "snowH8ter234*", "rflame@aol.com", "1313 Wicked Lane, Hell, Michigan 48169", dobRobertFlame);
        usersObject.addUser(registerUserRobertFlame);

        assertEquals(registerUserRobertFlame, usersObject.searchUser("rflame532", "snowH8ter234*"));    //searchUser is used because the new user would be at an unknown index
    }

    @Test
    public void testDeleteUser()
    {
        Users usersObject = new Users();
        Date dobRobertFlame = new Date(7, 17, 1968);
        RegisteredUser registerUserRobertFlame = new RegisteredUser("Robert", "Flame", "rflame532", "snowH8ter234*", "rflame@aol.com", "1313 Wicked Lane, Hell, Michigan 48169", dobRobertFlame);
        usersObject.addUser(registerUserRobertFlame);

        usersObject.deleteUser(registerUserRobertFlame);

        assertNull(usersObject.searchUser("rflame532", "snowH8ter234*"));
    }
}