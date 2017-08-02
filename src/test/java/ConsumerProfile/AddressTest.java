package ConsumerProfile;

import apicalls.consumerprofile.AddressCall;

import dtos.RegisteredEmailUser;
import dtos.Address;

import objects.User;

import org.junit.Test;
import services.UserCreationService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class AddressTest {

    @Test()
    public void testAddAddress() {

        UserCreationService ucs = new UserCreationService();
        User user = new User();
        RegisteredEmailUser reu = ucs.createUser(user);
        assertNotEquals(reu.getEmail(), null);
        assertNotEquals(reu.getToken(), null);
        assertNotEquals(reu.getUserId(), null);

        jsonobjects.Address address = new jsonobjects.Address("home", "", "Noontest", "", "", "+971588989160", "1", "1", "Emaar Square Bldg 3", "Sheik Zayed Road", "Downtown Dubai",
                "", "Dubai", "AE", "", "25.233758", "55.362766", false, true  );

        AddressCall addressCall = new AddressCall();
        Address addressResponse = addressCall.addAddress(address, reu.getToken());
        assertNotEquals(addressResponse.getId(), null);
    }

    @Test()
    public void testAddWrongAddress() {

        UserCreationService ucs = new UserCreationService();
        User user = new User();
        RegisteredEmailUser reu = ucs.createUser(user);
        assertNotEquals(reu.getEmail(), null);
        assertNotEquals(reu.getToken(), null);
        assertNotEquals(reu.getUserId(), null);

        jsonobjects.Address address = new jsonobjects.Address("", "", "", "", "", "", "1", "1", "", "Sheik Zayed Road", "Downtown Dubai",
                "", "Dubai", "AE", "", "", "", false, true  );

        AddressCall addressCall = new AddressCall();
        Address addressResponse = addressCall.addAddress(address, reu.getToken());
        assertEquals(addressResponse.getId(), null);
    }

}





