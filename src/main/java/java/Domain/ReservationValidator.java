package java.Domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ReservationValidator implements IValidator<Reservation> {


    public void validate(Reservation reservation) {

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            format.parse(reservation.getDate());
        } catch (ParseException pe) {
            throw new RuntimeException("The date must be in dd.MM.yyyy format ");
        }

        SimpleDateFormat timeformat = new SimpleDateFormat("hh.mm");
        try {
            timeformat.parse(reservation.getTime());
        } catch (ParseException pe) {
            throw new RuntimeException("The time must be in hh.mm format");
        }


    }



}
