package OfferQuantityReservation;


import apicalls.reservation.OfferQuantityReservationCall;

import dtos.reservation.ReservationQuantityResponse;

import org.junit.Test;
import static org.junit.Assert.assertNotEquals;

public class OfferQuantityReservationTest {

    @Test()
    public void testGetOfferQuantityReservation (){

        OfferQuantityReservationCall offerQuantityReservationCall = new OfferQuantityReservationCall();
        ReservationQuantityResponse reservationQuantityResponse;
        String sellingOfferId = "2387456571_3145891236309_0";
        reservationQuantityResponse = offerQuantityReservationCall.getAvailability(sellingOfferId);

        assertNotEquals(reservationQuantityResponse.getAvailable(), null);
        assertNotEquals(reservationQuantityResponse.getReserved(), null);

    }

}
