package UserManagement;

import apicalls.usermanagement.LoginCall;
import dtos.RegisteredEmailUser;
import dtos.RegisteredPhoneUser;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class LoginTest {

    @Test()
    public void testLoginUsingEmail() {

        LoginCall loginCall = new LoginCall();
        String email = "simplymahmoud@gmail.com";
        String password = "Blah1234";

        RegisteredEmailUser registeredEmailUser = loginCall.loginWithEmail(email, password);

        assertEquals(registeredEmailUser.getEmail(), email);
        assertNotEquals(registeredEmailUser.getToken(), null);
        assertNotEquals(registeredEmailUser.getUserId(), null);
    }

    @Test()
    public void testLoginUsingEmailWrongPassword() {

        LoginCall loginCall = new LoginCall();
        String email = "simplymahmoud@gmail.com";
        String password = "WrongPassword";

        RegisteredEmailUser registeredEmailUser = loginCall.loginWithEmail(email, password);

        assertNotEquals(registeredEmailUser.getEmail(), email);
        assertEquals(registeredEmailUser.getToken(), null);
        assertEquals(registeredEmailUser.getUserId(), null);
    }

    @Test()
    public void testLoginUsingEmailWrongEmail() {

        LoginCall loginCall = new LoginCall();
        String email = "wrong@gmail.com";
        String password = "Blah1234";

        RegisteredEmailUser registeredEmailUser = loginCall.loginWithEmail(email, password);

        assertNotEquals(registeredEmailUser.getEmail(), email);
        assertEquals(registeredEmailUser.getToken(), null);
        assertEquals(registeredEmailUser.getUserId(), null);
    }

    @Test()
    public void testLoginUsingMobile() {

        LoginCall loginCall = new LoginCall();
        String phone = "+971588989160";
        String password = "Blah1234";

        RegisteredPhoneUser registeredPhoneUser = loginCall.loginWithPhoneNumber(phone, password);

        assertEquals(registeredPhoneUser.getPhoneNumber(), phone);
        assertNotEquals(registeredPhoneUser.getToken(), null);
        assertNotEquals(registeredPhoneUser.getUserId(), null);
    }

    @Test()
    public void testLoginUsingMobileWrongPassword() {

        LoginCall loginCall = new LoginCall();
        String phone = "+971588989160";
        String password = "WrongPassword";

        RegisteredPhoneUser registeredPhoneUser = loginCall.loginWithPhoneNumber(phone, password);

        assertNotEquals(registeredPhoneUser.getPhoneNumber(), phone);
        assertEquals(registeredPhoneUser.getToken(), null);
        assertEquals(registeredPhoneUser.getUserId(), null);
    }

    @Test()
    public void testLoginUsingMobileWrongMobile() {

        LoginCall loginCall = new LoginCall();
        String phone = "+9715000000000";
        String password = "Blah1234";

        RegisteredPhoneUser registeredPhoneUser = loginCall.loginWithPhoneNumber(phone, password);

        assertNotEquals(registeredPhoneUser.getPhoneNumber(), phone);
        assertEquals(registeredPhoneUser.getToken(), null);
        assertEquals(registeredPhoneUser.getUserId(), null);
    }


}
