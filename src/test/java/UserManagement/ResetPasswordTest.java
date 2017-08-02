package UserManagement;

import apicalls.usermanagement.LoginCall;
import apicalls.usermanagement.ResetPasswordCall;

import dtos.RegisteredEmailUser;
import dtos.ResetPasswordRepsonse;
import dtos.Token;
import dtos.Uuid;

import jsonobjects.Email;
import jsonobjects.UserPasswordResetObject;
import jsonobjects.UserVerificationObject;

import objects.User;

import services.OTPExtractionService;
import services.UserCreationService;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ResetPasswordTest {

    @Test()
    public void testRestPasswordUsingEmail(){

        UserCreationService ucs = new UserCreationService();
        User user = new User();
        RegisteredEmailUser reu = ucs.createUser(user);
        assertNotEquals(reu.getEmail(), null);
        assertNotEquals(reu.getToken(), null);
        assertNotEquals(reu.getUserId(), null);

        ResetPasswordCall rpc = new ResetPasswordCall();
        Email email = new Email(reu.getEmail());
        Uuid uuid = rpc.requestPassword(email);

        OTPExtractionService otpes = new OTPExtractionService();
        String verficationCode = otpes.getVerificationCodeForgotPassword();
        assertNotEquals(verficationCode, null);
        UserVerificationObject userVerificationObjecto = new UserVerificationObject(uuid.getUuid(), verficationCode);
        Token token = rpc.verifyPassword(userVerificationObjecto);
        assertNotEquals(uuid.getUuid(), null);
        assertNotEquals(token.getToken(), null);
        assertEquals(uuid.getUuid(), reu.getUserId());

        String password = "Blah1234";
        UserPasswordResetObject upro = new UserPasswordResetObject(uuid.getUuid(), token.getToken(),password);
        ResetPasswordRepsonse resetPasswordRepsonse = rpc.resetPassword(upro);
        assertEquals(resetPasswordRepsonse.getMessage(), "password updated");


        LoginCall loginCall = new LoginCall();
        RegisteredEmailUser registeredEmailUser = loginCall.loginWithEmail(reu.getEmail(), password);

        assertEquals(registeredEmailUser.getEmail(), reu.getEmail());
        assertNotEquals(registeredEmailUser.getToken(), null);
        assertNotEquals(registeredEmailUser.getUserId(), null);
    }
}
