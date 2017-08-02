package Checkout;


import apicalls.cart.CartCall;
import apicalls.cart.CheckoutCall;
import apicalls.cart.PlaceOrderCall;
import apicalls.consumerprofile.AddressCall;

import dtos.CartResponse;
import dtos.CheckoutResponse;
import dtos.RegisteredEmailUser;

import jsonobjects.CartProduct;
import jsonobjects.CheckoutProduct;
import jsonobjects.PlaceOrderProduct;

import objects.User;
import org.junit.Test;
import services.ProductService;
import services.UserCreationService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CheckoutTest {

    @Test()
    public void testAddCartCheckoutPlaceOrder() {

        UserCreationService ucs = new UserCreationService();
        User user = new User();
        RegisteredEmailUser reu = ucs.createUser(user);

        jsonobjects.Address address = new jsonobjects.Address("home", "", "Noontest", "", "", "+971588989160", "1", "1", "Emaar Square Bldg 3", "Sheik Zayed Road", "Downtown Dubai",
                "", "Dubai", "AE", "", "25.233758", "55.362766", false, true  );

        AddressCall addressCall = new AddressCall();
        addressCall.addAddress(address, reu.getToken());


        CartCall cartCall = new CartCall();
        ProductService productService = new ProductService();
        CartProduct cartProduct = new CartProduct(productService.getRandomProduct().getSellingOfferId(),1);
        //CartProduct cartProduct = new CartProduct("3693707437_5702015346849_0",1);
        CartResponse cartResponse = cartCall.addProduct(cartProduct, reu.getToken());
        List someList = new ArrayList();
        assertEquals(cartResponse.getErrors(), someList);

        CheckoutCall checkoutCall = new CheckoutCall();
        CheckoutProduct checkoutProduct = new CheckoutProduct("cod", "");
        CheckoutResponse checkoutResponse = checkoutCall.performCheckoutPatch(checkoutProduct, reu.getToken());
        assertEquals(checkoutResponse.getErrors(), someList);

        CheckoutCall checkoutCallPost = new CheckoutCall();
        PlaceOrderProduct placeOrderProduct = checkoutCallPost.performCheckoutPOST(checkoutResponse, reu.getToken());
        assertNotEquals(placeOrderProduct.getCheckout_id(), null);

        PlaceOrderCall placeOrderCall = new PlaceOrderCall();
        String response = placeOrderCall.placeOrder(placeOrderProduct, reu.getToken());
        assertEquals(response, "HTTP/1.1 200 OK");

    }


}
