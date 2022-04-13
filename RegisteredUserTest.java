import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing of User.java
 * @author David Eta
 */
public class RegisteredUserTest
{
    @BeforeClass
    public static void oneTimeSetup() {
        return;
    }

    @AfterClass
    public static void oneTimeTearDown() {
        return;
    }

    @BeforeEach
    public void setup() {
        return;
    }

    @AfterEach
    public void tearDown() {
        return;
    }

    @Test
    public void testRemoveFriendC1() { //Case 1: list of friends empty
    
        Users usersObject = new Users();
        RegisteredUser regUserObj;
        regUserObj = usersObject.searchUser("kathyb", "sp1ceg1rlL0ver"); // real user
        //actually check
        ArrayList<Friend> friends = regUserObj.getFriends();
        assertTrue(friends.isEmpty());
        return;
    }

    @Test
    public void testRemoveFriendC2()  //Case 2: person is not a friend
    {
        Users usersObject = new Users();
        RegisteredUser regUserObj;
        regUserObj = usersObject.searchUser("cindywright", "catL@dy<3"); // real user
        //actually check
        Friend friend = Friend("firstName", "lastName", "username", "email", Date(9, 9, 1999), "address"); // fake friend
        ArrayList<Friend> friends = regUserObj.getFriends();
        assertFalse(friends.contains(friend));
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

    @Test
    public void testIfRegisteredC1()    //Case 1: The user exists.
    {
        Users usersObject = new Users();
        Date dobRobertFrost = new Date(7, 14, 1989);
        RegisteredUser registerUserRobertFrost = new RegisteredUser("Robert", "Frost", "rfrost325", "snowL0ver432*", "rfrost@yahoo.com", "1478 Pennelton Road, Dallas, Texas 75043", dobRobertFrost);

        assertEquals(true, usersObject.checkIfRegisterd(registerUserRobertFrost));
    }

    @Test
    public void testIfRegisteredC2()    //Case 2: The user is a guest.
    {
        Users usersObject = new Users();
        GuestUser guestUserObject = new GuestUser();

        assertEquals(false, usersObject.checkIfRegisterd(guestUserObject));
    }
}