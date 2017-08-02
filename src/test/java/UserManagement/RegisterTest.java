package UserManagement;

import apicalls.usermanagement.LoginCall;
import services.UserCreationService;
import objects.User;
import dtos.RegisteredEmailUser;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class RegisterTest {

    @Test()
    public void testRegisterUsingEmailService() {
        UserCreationService ucs = new UserCreationService();
        User user = new User();
        RegisteredEmailUser reu = ucs.createUser(user);
        assertNotEquals(reu.getEmail(), null);
        assertNotEquals(reu.getToken(), null);
        assertNotEquals(reu.getUserId(), null);

        LoginCall loginCall = new LoginCall();
        RegisteredEmailUser registeredEmailUser = loginCall.loginWithEmail(user.getEmail(), user.getPassword());

        assertEquals(registeredEmailUser.getEmail(), reu.getEmail());
        assertNotEquals(registeredEmailUser.getToken(), null);
        assertNotEquals(registeredEmailUser.getUserId(), null);

    }

}
