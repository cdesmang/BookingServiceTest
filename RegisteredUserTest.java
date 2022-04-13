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
    public void testRemoveFriendC1() { //Case 1: the user has no friends
    
        Users usersObject = new Users();
        RegisteredUser regUserObj;
        regUserObj = usersObject.searchUser("kathyb", "sp1ceg1rlL0ver"); // real user that has no friends in the json file
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
        regUserObj = usersObject.searchUser("jhud1338", "cut1eP1e!"); // real user that has friends in the json file
        //actually check
        ArrayList<Booking> friend_bookings = new ArrayList<Booking>();
        friend_bookings.add(new Booking("8c724731-ab3e-4848-a02e-5d2a33e52747", "1A"));
        Friend friend = Friend("firstName", "lastName", friend_bookings); // fake friend
        ArrayList<Friend> friends = regUserObj.getFriends();
        assertFalse(friends.contains(friend));
        return;
    }


    @Test
    public void testRemoveFriendC3()  //Case 3: person is a friend
    {
        Users usersObject = new Users();
        RegisteredUser regUserObj;
        regUserObj = usersObject.searchUser("jhud1338", "cut1eP1e!"); // real user that has friends in the json file
        //actually check

        ArrayList<Booking> friend_bookings = new ArrayList<Booking>();
        Booking a = new Booking("8c724731-ab3e-4848-a02e-5d2a33e52926", "4G");
        Booking b = new Booking("89953bc8-5655-41a9-a62f-b360ad1aa695", "3G");
        friend_bookings.add(a);
        friend_bookings.add(b);
        Friend friend = Friend("Alexis", "Jonson", friend_bookings); // real friend
        ArrayList<Friend> friends = regUserObj.getFriends();
        assertTrue(friends.contains(friend));
    }
}